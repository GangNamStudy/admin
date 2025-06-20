package com.parking.admin.adapter.in.web.controller;

import com.parking.admin.adapter.in.web.dto.request.UpdatePolicyRequest;
import com.parking.admin.adapter.in.web.dto.response.PolicyResponse;
import com.parking.admin.adapter.in.web.mapper.PolicyWebMapper;
import com.parking.admin.application.port.in.PolicyUseCase;
import com.parking.admin.domain.policy.PolicyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/policy")
public class PolicyController {
    private final PolicyUseCase policyUseCase;
    private final PolicyWebMapper policyWebMapper;

    @PatchMapping
    public ResponseEntity<PolicyResponse> updatePolicy(@RequestBody UpdatePolicyRequest dto) {

        PolicyUseCase.UpdatePolicyCommand command = policyWebMapper.toCommand(dto); // 1. request dto->command 변환
        PolicyInfo info = policyUseCase.updatePolicy(command); // 2. command->info 변환
        PolicyResponse response = policyWebMapper.toResponse(info); // 3. info->response dto 변환

        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PolicyResponse> readPolicy() {
        PolicyInfo info = policyUseCase.getPolicy();
        PolicyResponse response = policyWebMapper.toResponse(info);

        return ResponseEntity.ok(response);
    }

}