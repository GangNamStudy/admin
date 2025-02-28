package com.parking.admin.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PolicyListResponse {
    private List<PolicyResponse> policies;
}
