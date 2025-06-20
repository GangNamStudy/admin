package com.parking.admin.adapter.out.payment.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ExternalListPaymentResponse {
    private List<ExternalPaymentResponse> payments;
    private PageInfo pageInfo;
}
