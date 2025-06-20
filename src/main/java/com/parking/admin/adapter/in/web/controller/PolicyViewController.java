package com.parking.admin.adapter.in.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PolicyViewController {
    @GetMapping("/")
    public String policyView() {
        return "index";
    }

    @GetMapping("/update-policy")
    public String updatePolicyView() {
        return "updatePolicy.html";
    }

    @GetMapping("/entrance")
    public String entranceView() {
        return "entryCar";
    }

    @GetMapping("/exit")
    public String exitView() {
        return "exitCar";
    }

    @GetMapping("/parking-info")
    public String parkingInfoView() {
        return "readParkingInfo.html";
    }

    @GetMapping("/revenue")
    public String revenueView() {
        return "totalRevenue";
    }
}
