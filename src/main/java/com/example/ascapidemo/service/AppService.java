package com.example.ascapidemo.service;

import com.example.ascapidemo.dto.App;
import com.example.ascapidemo.request.IAppRequest;
import com.example.ascapidemo.request.IAppVersionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sa
 * @date 31.01.2022
 * @time 17:04
 */
@Service
@RequiredArgsConstructor
public class AppService implements IAppService
{
    private final IAppRequest appRequest;
    private final IAppVersionRequest appVersionRequest;

    @Override
    public List<App> getAppList()
    {
        return appRequest.getApps().getData();
    }

    @Override
    public List<Object> getAppVersions(Long id)
    {
        return appRequest.getVersions(id, List.of("keywords")).getData();
    }

    @Override
    public Object getAppVersion(String id)
    {
        return appVersionRequest.getVersion(id, "appStoreVersionLocalizations", "description,keywords,whatsNew");
    }
}
