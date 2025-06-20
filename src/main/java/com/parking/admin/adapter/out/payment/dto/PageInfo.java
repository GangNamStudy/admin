package com.parking.admin.adapter.out.payment.dto;

import lombok.Getter;

@Getter
public class PageInfo {
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;
    private boolean hasPrevious;
}