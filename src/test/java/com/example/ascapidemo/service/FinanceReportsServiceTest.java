package com.example.ascapidemo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 1.02.2022
 * @time 17:45
 */
@SpringBootTest
class FinanceReportsServiceTest
{
    @Autowired
    private IFinanceReportsService financeReportsService;

    @Test
    void downloadReport()
    {
        financeReportsService.downloadReport();
    }
}
