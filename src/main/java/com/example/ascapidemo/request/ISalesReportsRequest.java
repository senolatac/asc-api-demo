package com.example.ascapidemo.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author sa
 * @date 2.02.2022
 * @time 10:34
 */
@FeignClient(name = "salesReportsRequest", url = "${asc.api.url}", path = "salesReports",
        configuration = FeignConfiguration.class)
public interface ISalesReportsRequest
{
    @GetMapping(produces = "application/a-gzip")
    byte[] downloadReports(@RequestParam("filter[frequency]") List<String> frequencyList, //Possible values: DAILY, WEEKLY, MONTHLY, YEARLY
                           @RequestParam("filter[reportType]") List<String> filterReportType,
                           @RequestParam("filter[reportDate]") String filterReportDate,
                           @RequestParam("filter[reportSubType]") List<String> filterReportSubType,
                           @RequestParam("filter[vendorNumber]") List<String> filterVendorNumber,
                           @RequestParam("filter[version]") List<String> filterVersion);
}
