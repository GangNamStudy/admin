package com.parking.admin.application.command;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreatePaymentCommand {

    @NonNull
    private Long paymentId;
    @NonNull
    private String identifier;
    @NonNull
    private Long amount;
    @NonNull
    private LocalDateTime paymentDate;
    @NonNull
    private String status;
    @NonNull
    private String orderName;
    @NonNull
    private String paymentMethod;
    @NonNull
    private String receiptUrl;
}
