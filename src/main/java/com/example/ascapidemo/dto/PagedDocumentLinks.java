package com.example.ascapidemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author sa
 * @date 31.01.2022
 * @time 17:01
 */
@Getter
@NoArgsConstructor
public class PagedDocumentLinks
{
    private String first;

    private String next;

    private String self;

}
