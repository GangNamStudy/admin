package com.parking.admin.adapter.out.parking;

import com.parking.admin.adapter.exception.InfraErrorCode;
import com.parking.admin.adapter.exception.InfraException;
import com.parking.admin.adapter.out.parking.dto.ExternalEntryAndExitRequest;
import com.parking.admin.adapter.out.parking.dto.ExternalEntryAndExitResponse;
import com.parking.admin.adapter.out.parking.dto.ExternalReadCarInfoRequest;
import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.application.port.out.ParkingPort;
import com.parking.admin.common.ExternalAdapter;
import com.parking.admin.domain.parking.ParkingInfo;
import com.parking.admin.domain.parking.ParkingSessionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@ExternalAdapter
@RequiredArgsConstructor
public class ParkingAdapter implements ParkingPort {

    private final ExternalParkingMapper mapper;
    private final WebClient webClient;

    @Value("${api.parking-management.parking.entry-car}")
    private String entryUrl;

    @Value("${api.parking-management.parking.exit-car}")
    private String exitUrl;

    @Value("${api.parking-management.parking.load-car.id}")
    private String getIdUrl;

    @Value("${api.parking-management.parking.load-car.plate}")
    private String getPlateUrl;

    @Override
    public ParkingInfo entry(ParkingUseCase.ParkingCommand parkingCommand) {
        ExternalEntryAndExitRequest dto = mapper.toEntryRequest(parkingCommand);

        ExternalEntryAndExitResponse response = webClient
                .post()
                .uri(entryUrl)
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_ENTRY_CAR)))
                .bodyToMono(ExternalEntryAndExitResponse.class)
                .block();

        if (!(Objects.equals(Objects.requireNonNull(response).getStatus(), "success")))
            throw new InfraException(InfraErrorCode.FAILURE_TO_ENTRY_CAR);

        return mapper.entryToInfo(Objects.requireNonNull(response));
    }

    @Override
    public ParkingInfo exit(ParkingSessionEntity entity) {
        ExternalEntryAndExitRequest dto = mapper.toEntryRequest(entity);
        String plate = dto.getPlate();

        ExternalEntryAndExitResponse response = webClient
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(exitUrl)
                        .build(plate))
                .bodyValue(dto.getTime())
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_EXIT_CAR)))
                .bodyToMono(ExternalEntryAndExitResponse.class)
                .block();

        if (!(Objects.equals(Objects.requireNonNull(response).getStatus(), "success")))
                throw new InfraException(InfraErrorCode.FAILURE_TO_EXIT_CAR);

        return mapper.exitToInfo(Objects.requireNonNull(response), entity.getExitTime());
    }

    @Override
    public ParkingSessionEntity loadById(Long id) {
        ExternalReadCarInfoRequest response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getIdUrl)
                        .build(id))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_CAR_INFO)))
                .bodyToMono(ExternalReadCarInfoRequest.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(response));
    }

    @Override
    public ParkingSessionEntity loadByPlate(String plate) {
        ExternalReadCarInfoRequest response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getPlateUrl)
                        .build(plate))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_CAR_INFO)))
                .bodyToMono(ExternalReadCarInfoRequest.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(response));
    }
}
