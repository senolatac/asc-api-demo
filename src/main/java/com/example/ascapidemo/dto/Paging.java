package com.example.ascapidemo.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author sa
 * @date 31.01.2022
 * @time 17:00
 */
@Getter
@NoArgsConstructor
public class Paging
{
    private Integer total;

    private Integer limit;
}
