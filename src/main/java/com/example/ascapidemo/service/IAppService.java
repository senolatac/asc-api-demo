package com.example.ascapidemo.service;

import com.example.ascapidemo.dto.App;

import java.util.List;

/**
 * @author sa
 * @date 31.01.2022
 * @time 17:04
 */
public interface IAppService
{
    List<App> getAppList();

    List<Object> getAppVersions(Long id);

    Object getAppVersion(String id);
}
