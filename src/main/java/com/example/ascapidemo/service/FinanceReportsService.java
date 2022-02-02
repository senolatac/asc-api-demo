package com.example.ascapidemo.service;

import com.example.ascapidemo.request.IFinanceReportsRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * @author sa
 * @date 1.02.2022
 * @time 17:32
 */
@Service
@RequiredArgsConstructor
public class FinanceReportsService implements IFinanceReportsService
{
    private final IFinanceReportsRequest financeReportsRequest;

    @Value("${asc.vendor-no}")
    private String VENDOR_NO;

    @Override
    public void downloadReport()
    {
        byte[] response = financeReportsRequest.downloadReports(List.of("US"), "2021-12", List.of("FINANCIAL"), List.of(VENDOR_NO));

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
