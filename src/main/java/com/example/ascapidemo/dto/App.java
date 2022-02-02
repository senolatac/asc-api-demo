package com.example.ascapidemo.dto;

import lombok.Getter;

/**
 * @author sa
 * @date 31.01.2022
 * @time 16:54
 */
@Getter
public class App
{
    private String id;

    private String type;

    private Attributes attributes;

    @Getter
    public static class Attributes
    {
        private String bundleId;

        private String name;

        private String primaryLocale;

        private String sku;
    }
}
