package com.example.ascapidemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author sa
 * @date 31.01.2022
 * @time 16:58
 */
@Getter
@NoArgsConstructor
public class ApiResponse<T>
{
    private List<T> data;

    private PagedDocumentLinks links;

    private PagingInformation meta;
}
