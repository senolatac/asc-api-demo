package com.example.ascapidemo.request;

import com.example.ascapidemo.util.TokenUtil;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author sa
 * @date 31.01.2022
 * @time 22:40
 */
@Configuration
public class FeignConfiguration
{
    @Value("${asc.issuer-id}")
    private String ISSUER_ID;

    @Value("${asc.key-id}")
    private String KEY_ID;

    @Value("${asc.key-file}")
    private String KEY_PATH;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", getJWT());
        };
    }

    protected String getJWT()
    {
        try
        {
            return TokenUtil.generateToken(ISSUER_ID, KEY_ID, KEY_PATH);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }
}
