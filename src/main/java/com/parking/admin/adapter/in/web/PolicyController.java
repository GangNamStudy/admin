package com.parking.admin.adapter.in.web;

import com.parking.admin.adapter.in.web.dto.request.PolicyStatusRequest;
import com.parking.admin.adapter.in.web.dto.request.UpdatePolicyRequest;
import com.parking.admin.adapter.in.web.dto.response.PolicyListResponse;
import com.parking.admin.adapter.in.web.dto.response.PolicyResponse;
import com.parking.admin.adapter.in.web.dto.response.PolicyStatusResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/policy")
public class PolicyController {

    @PostMapping
    public ResponseEntity<PolicyResponse> updatePolicy(@RequestBody UpdatePolicyRequest dto) {
        throw new UnsupportedOperationException(); }

    @GetMapping("/check-all")
    public ResponseEntity<PolicyListResponse> readAllPolicy() {
        throw new UnsupportedOperationException();}

    @PatchMapping("/{policy-id}/activate")
    public ResponseEntity<PolicyStatusResponse> activatePolicy(@PathVariable("policy-id") String policyId, PolicyStatusRequest dto) {
        throw new UnsupportedOperationException();}

    @PatchMapping("/{policy-id}/deactivate")
    public ResponseEntity<PolicyStatusResponse> deactivatePolicy(@PathVariable("policy-id") String policyId, PolicyStatusRequest dto) {
        throw new UnsupportedOperationException();}
}
