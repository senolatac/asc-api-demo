package com.example.ascapidemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 2.02.2022
 * @time 10:45
 */
@SpringBootTest
class SalesReportsServiceTest
{
    @Autowired
    private ISalesReportsService salesReportsService;

    @Test
    void downloadReport()
    {
        salesReportsService.downloadReport();
    }
}
