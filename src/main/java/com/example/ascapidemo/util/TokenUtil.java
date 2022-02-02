package com.example.ascapidemo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.ECPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author sa
 * @date 31.01.2022
 * @time 11:59
 * origin => https://github.com/TheGeekPharaoh/asc4j/blob/master/src/main/java/net/odyssi/asc4j/util/TokenUtil.java
 */
@Slf4j
public class TokenUtil
{
    private static final String AUDIENCE = "appstoreconnect-v1";

    /**
     * Decodes the PEM-encoded string
     *
     * @param pem The PEM-encoded string
     * @return The decoded string
     * @throws IOException
     */
    protected static byte[] decodePEM(String pem) throws IOException {
        Pattern parse = Pattern.compile("(?m)(?s)^---*BEGIN.*---*$(.*)^---*END.*---*$.*");
        String encoded = parse.matcher(pem).replaceFirst("$1");

        byte[] decoded = Base64.getMimeDecoder().decode(encoded);

        return decoded;
    }

    /**
     * Loads and returns the {@link PrivateKey} from the PKCS#8-encoded key data
     *
     * @param keyData The key data
     * @return The private key
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    protected static PrivateKey generatePrivateKey(byte[] keyData)
            throws NoSuchAlgorithmException, InvalidKeySpecException, UnsupportedEncodingException, IOException {
        if (log.isDebugEnabled()) {
            log.debug(
                    "generatePrivateKey(byte[]) - Generating PrivateKey object from key data... - keyData.length={}", //$NON-NLS-1$
                    keyData.length);
        }

        byte[] decodedPEM = decodePEM(new String(keyData, "UTF-8"));
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedPEM);
        KeyFactory kf = KeyFactory.getInstance("EC");

        PrivateKey privateKey = kf.generatePrivate(keySpec);

        return privateKey;
    }

    /**
     * Generates an authorization token as a String
     *
     * @param issuerID       The issuer ID
     * @param keyID          The key ID
     * @param privateKeyData The private key data
     * @return The token string
     */
    public static final String generateToken(String issuerID, String keyID, byte[] privateKeyData) throws IOException {

        PrivateKey privateKey = null;
        try {
            privateKey = generatePrivateKey(privateKeyData);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error("generateToken(String, String, byte[])", e); //$NON-NLS-1$

            throw new IOException("Unable to generate PrivateKey object", e);
        }

        String token = generateToken(issuerID, keyID, privateKey);

        return token;
    }

    /**
     * Generates an authorization token as a String
     *
     * @param issuerID       The issuer ID
     * @param keyID          The key ID
     * @param privateKeyFile The private key file
     * @return The token string
     */
    public static final String generateToken(String issuerID, String keyID, File privateKeyFile) throws IOException {

        FileInputStream fInStream = new FileInputStream(privateKeyFile);
        byte[] buffer = new byte[(int) privateKeyFile.length()];

        fInStream.read(buffer);
        fInStream.close();

        String token = generateToken(issuerID, keyID, buffer);

        return token;
    }

    /**
     * Generates an authorization token as a String
     *
     * @param issuerID The issuer ID
     * @param keyID    The key ID
     * @param inStream The input stream for the private key
     * @return The token string
     */
    public static final String generateToken(String issuerID, String keyID, InputStream inStream) throws IOException {

        byte[] keyData = inStream.readAllBytes();

        String token = generateToken(issuerID, keyID, keyData);

        return token;
    }

    /**
     * Generates an authorization token as a String
     *
     * @param issuerID   The issuer ID
     * @param keyID      The key ID
     * @param privateKey The private key
     * @return The token string
     */
    public static final String generateToken(String issuerID, String keyID, PrivateKey privateKey) throws IOException {

        ECPrivateKey ecKey = (ECPrivateKey) privateKey;
        Algorithm alg = Algorithm.ECDSA256(null, ecKey);

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, 20);

        Date expiration = cal.getTime();

        String token = JWT.create().withExpiresAt(expiration).withIssuer(issuerID).withAudience(AUDIENCE)
                .withKeyId(keyID).sign(alg);
        return token;
    }

    /**
     * Generates an authorization token as a String
     *
     * @param issuerID           The issuer ID
     * @param keyID              The key ID
     * @param privateKeyFilePath The path to the private key file
     * @return The token string
     */
    public static final String generateToken(String issuerID, String keyID, String privateKeyFilePath)
            throws IOException {

        File keyFile = new File(privateKeyFilePath);
        String token = generateToken(issuerID, keyID, keyFile);

        return token;
    }
}
