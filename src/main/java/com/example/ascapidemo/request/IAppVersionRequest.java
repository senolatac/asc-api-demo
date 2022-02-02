package com.example.ascapidemo.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author sa
 * @date 2.02.2022
 * @time 11:48
 */
@FeignClient(name = "appVersionRequest", url = "${asc.api.url}", path = "appStoreVersions",
        configuration = FeignConfiguration.class)
public interface IAppVersionRequest
{
    @GetMapping("{id}")
    Object getVersion(@PathVariable("id") String id,
                      @RequestParam("include") String include,
                      @RequestParam("fields[appStoreVersionLocalizations]") String includeFieldsInLocalizations);
}
