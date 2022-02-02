package com.example.ascapidemo.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author sa
 * @date 1.02.2022
 * @time 17:18
 */
@FeignClient(name = "financeReportsRequest", url = "${asc.api.url}", path = "financeReports",
        configuration = FeignConfiguration.class)
public interface IFinanceReportsRequest
{
    @GetMapping(produces = "application/a-gzip")
    byte[] downloadReports(@RequestParam("filter[regionCode]") List<String> filterRegionCode,
                           @RequestParam("filter[reportDate]") String filterReportDate,
                           @RequestParam("filter[reportType]") List<String> filterReportType,
                           @RequestParam("filter[vendorNumber]") List<String> filterVendorNumber);
}
