package com.example.xblog.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class PagedQuery {
    private static final int PAGE_SIZE=10;
    private static final int PAGE_NUM=1;

    private int pageSize = PAGE_SIZE;
    private int pageNum = PAGE_NUM;
    private HashMap<String, Object> queryParams = new HashMap<>();
}
