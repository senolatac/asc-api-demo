package com.example.ascapidemo.service;

import com.example.ascapidemo.dto.App;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author sa
 * @date 31.01.2022
 * @time 17:21
 */
@SpringBootTest
class AppServiceTest
{
    @Autowired
    private IAppService appService;

    @Test
    void getAppList()
    {
        List<App> apps = appService.getAppList();

        assertThat(apps).isNotEmpty();
        assertThat(apps.get(0).getAttributes().getName()).isEqualTo("App Name");
    }

    @Test
    void getAppVersions()
    {
        List<Object> versions = appService.getAppVersions(1L);

        assertThat(versions).isNotEmpty();
        System.out.println(versions.size());
        System.out.println(versions);
    }

    @Test
    void getAppVersion()
    {
        Object version = appService.getAppVersion("xxxxx");

        assertThat(version).isNotNull();
        System.out.println(version);
    }
}
