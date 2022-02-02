package com.example.ascapidemo.service;

import com.example.ascapidemo.request.ISalesReportsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author sa
 * @date 2.02.2022
 * @time 10:39
 */
@Service
@RequiredArgsConstructor
public class SalesReportsService implements ISalesReportsService
{
    private final ISalesReportsRequest salesReportsRequest;

    @Value("${asc.vendor-no}")
    private String VENDOR_NO;

    @Override
    public void downloadReport()
    {
        //For weekly reports, please specify the date of the Sunday ending the desired week.
        byte[] response = salesReportsRequest.downloadReports(List.of("WEEKLY"), List.of("SALES"), "2021-12-26", List.of("SUMMARY"), List.of(VENDOR_NO), List.of("1_0"));

        File file = new File("test.gzip");

        try
        {
            Files.write(file.toPath(), response);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
