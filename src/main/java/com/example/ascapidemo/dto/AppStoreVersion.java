package com.example.ascapidemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author sa
 * @date 2.02.2022
 * @time 11:38
 */
@Getter
@NoArgsConstructor
public class AppStoreVersion
{
    private Relationships relationships;

    @Getter
    public static class Relationships
    {
        private AppStoreVersionLocalizations appStoreVersionLocalizations;
    }

    @Getter
    public static class AppStoreVersionLocalizations
    {
        private LocalizationData data;
    }

    @Getter
    public static class LocalizationData
    {
        private String id;
        private String type;
        private String keywords;
    }
}
