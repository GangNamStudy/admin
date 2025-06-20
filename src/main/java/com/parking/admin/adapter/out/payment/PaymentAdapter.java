package com.parking.admin.adapter.out.payment;

import com.parking.admin.adapter.exception.InfraErrorCode;
import com.parking.admin.adapter.exception.InfraException;
import com.parking.admin.adapter.out.payment.dto.ExternalListPaymentResponse;
import com.parking.admin.adapter.out.payment.dto.ExternalPaymentResponse;
import com.parking.admin.application.port.in.PaymentUseCase;
import com.parking.admin.application.port.out.PaymentPort;
import com.parking.admin.common.ExternalAdapter;
import com.parking.admin.domain.payment.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExternalAdapter
@RequiredArgsConstructor
public class PaymentAdapter implements PaymentPort {

    private final WebClient paymentWebClient;
    private final ExternalPaymentMapper mapper;

    @Value("${api.payment.read.id}")
    private String getUrl;

    @Value("${api.payment.read.history}")
    private String getHistoryUrl;

    @Override
    public PaymentEntity load(Long id) {

        ExternalPaymentResponse response = paymentWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getUrl)
                        .build(id))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_PAYMENT_HISTORY)))
                .bodyToMono(ExternalPaymentResponse.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(response));
    }

    @Override
    public List<PaymentEntity> loadByDate(PaymentUseCase.searchPaymentCommand command) {
        int page = 0;
        boolean hasNext = true;
        List<PaymentEntity> allEntities = new ArrayList<>();

        LocalDateTime startDate = command.getStartDate();
        LocalDateTime endDate = command.getEndDate();
        while (hasNext) {
            int finalPage = page; //람다 표현식(queryParam 으로 변수 넣을때) 에선 final 변수만 쓸 수 있음
            ExternalListPaymentResponse response = paymentWebClient
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(getHistoryUrl)
                            .queryParam("startDate", startDate)
                            .queryParam("endDate", endDate)
                            .queryParam("page", finalPage)
                            .queryParam("pageSize", 500)
                            .build())
                    .retrieve()
                    .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                            Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_PAYMENT_HISTORY)))
                    .bodyToMono(ExternalListPaymentResponse.class)
                    .block();

            if (response == null || response.getPayments() == null) {
                break; // 응답이 없으면 종료
            }

            for (ExternalPaymentResponse dto : response.getPayments()) {
                allEntities.add(mapper.toEntity(dto));
            }

            hasNext = response.getPageInfo() != null && response.getPageInfo().isHasNext();
            page++;
        }

        return allEntities;
    }
}
