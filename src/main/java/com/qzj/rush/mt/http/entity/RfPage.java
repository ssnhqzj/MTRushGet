package com.qzj.rush.mt.http.entity;

import lombok.Data;

import java.util.List;

@Data
public class RfPage<T> {
    private Integer pageNo;
    private Integer pageSize;
    private Integer count;
    private List<T> list;
}
