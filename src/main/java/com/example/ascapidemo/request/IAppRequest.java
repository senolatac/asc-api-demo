package com.example.ascapidemo.request;

import com.example.ascapidemo.dto.ApiResponse;
import com.example.ascapidemo.dto.App;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author sa
 * @date 31.01.2022
 * @time 16:48
 */
@FeignClient(name = "appRequest", url = "${asc.api.url}", path = "apps",
        configuration = FeignConfiguration.class)
public interface IAppRequest
{
    @GetMapping
    ApiResponse<App> getApps();

    @GetMapping("{id}/appStoreVersions")
    ApiResponse<Object> getVersions(@PathVariable("id") Long id,
                                    @RequestParam("fields[appStoreVersionLocalizations]") List<String> includeFieldsInLocalizations);
}
