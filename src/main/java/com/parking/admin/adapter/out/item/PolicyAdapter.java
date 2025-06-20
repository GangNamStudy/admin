package com.parking.admin.adapter.out.item;

import com.parking.admin.adapter.exception.InfraErrorCode;
import com.parking.admin.adapter.exception.InfraException;
import com.parking.admin.adapter.out.item.dto.ExternalReadPolicyResponse;
import com.parking.admin.adapter.out.item.dto.ExternalUpdatePolicyRequest;
import com.parking.admin.application.port.out.PolicyPort;
import com.parking.admin.common.ExternalAdapter;
import com.parking.admin.domain.policy.PolicyEntity;
import com.parking.admin.domain.policy.PolicyInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@ExternalAdapter
@RequiredArgsConstructor
public class PolicyAdapter implements PolicyPort {

    private final ExternalPolicyMapper mapper;
    private final WebClient itemManagementWebClient;

    @Value("${api.item-management.policy.read-policy}")
    private String getUrl;

    @Value("${api.item-management.policy.update-policy}")
    private String putUrl;

    @Override
    public PolicyEntity load() {

        ExternalReadPolicyResponse dto = itemManagementWebClient
                .get()
                .uri(getUrl)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_CREATE_POLICY)))
                .bodyToMono(ExternalReadPolicyResponse.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(dto));
    }

    @Override
    public PolicyInfo save(PolicyEntity entity) {
        ExternalUpdatePolicyRequest request = mapper.toUpdateRequest(entity);

        ExternalReadPolicyResponse response =  itemManagementWebClient
                .put()
                .uri(putUrl)
                .bodyValue(request)
                .retrieve()
                .bodyToMono(ExternalReadPolicyResponse.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(response)).toInfo();
    }
}
