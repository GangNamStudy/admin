package com.parking.admin.adapter.out.parking;

import com.parking.admin.adapter.exception.InfraErrorCode;
import com.parking.admin.adapter.exception.InfraException;
import com.parking.admin.adapter.out.parking.dto.ExternalEntryAndExitRequest;
import com.parking.admin.adapter.out.parking.dto.ExternalEntryAndExitResponse;
import com.parking.admin.adapter.out.parking.dto.ExternalReadCarInfoResponse;
import com.parking.admin.adapter.out.parking.dto.ExternalVehicleExitRequest;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ExternalAdapter
@RequiredArgsConstructor
public class ParkingAdapter implements ParkingPort {

    private final ExternalParkingMapper mapper;
    private final WebClient parkingManagementWebClient;

    @Value("${api.parking-management.parking.entry-car}")
    private String entryUrl;

    @Value("${api.parking-management.parking.exit-car}")
    private String exitUrl;

    @Value("${api.parking-management.parking.load-car.id}")
    private String getIdUrl;

    @Value("${api.parking-management.parking.load-car.plate}")
    private String getPlateUrl;

    @Value("${api.parking-management.parking.load-car.history}")
    private String getHistoryUrl;

    @Override
    public ParkingInfo entry(ParkingUseCase.ParkingCommand parkingCommand) {
        ExternalEntryAndExitRequest dto = mapper.toEntryRequest(parkingCommand);

        ExternalEntryAndExitResponse response = parkingManagementWebClient
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

        ExternalEntryAndExitResponse response = parkingManagementWebClient
                .put()
                .uri(uriBuilder -> uriBuilder
                        .path(exitUrl)
                        .build(plate))
                .bodyValue(new ExternalVehicleExitRequest(dto.getTime())) //이거 객체로 감싸서 안보내고 따로따로 풀어서 보내면 작동안함. 값 하나여도 dto로 감싸서 보내야됨
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_EXIT_CAR)))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_EXIT_CAR)))
                .bodyToMono(ExternalEntryAndExitResponse.class)
                .block();

        if (!"success".equals(Objects.toString(response != null ? response.getStatus() : null, ""))) {
            throw new InfraException(InfraErrorCode.FAILURE_TO_EXIT_CAR);
        }

        return mapper.exitToInfo(Objects.requireNonNull(response), entity.getExitTime());
    }

    @Override
    public ParkingSessionEntity loadById(Long id) {
        ExternalReadCarInfoResponse response = parkingManagementWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getIdUrl)
                        .build(id))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_CAR_INFO)))
                .bodyToMono(ExternalReadCarInfoResponse.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(response));
    }

    @Override
    public ParkingSessionEntity loadByPlate(String plate) {
        ExternalReadCarInfoResponse response = parkingManagementWebClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path(getPlateUrl)
                        .build(plate))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_CAR_INFO)))
                .bodyToMono(ExternalReadCarInfoResponse.class)
                .block();

        return mapper.toEntity(Objects.requireNonNull(response));
    }

    @Override
    public List<ParkingInfo> loadByCommand(ParkingUseCase.searchHistoryCommand command) {

        List<ExternalReadCarInfoResponse> responses = parkingManagementWebClient
                .get()
                .uri(uriBuilder -> {
                        uriBuilder.path(getHistoryUrl);
                        if (command.getStartDate() != null)
                            uriBuilder.queryParam("startDate", command.getStartDate());

                        if (command.getEndDate() != null)
                            uriBuilder.queryParam("endDate", command.getEndDate());

                        if (command.getPlate() != null && !command.getPlate().isEmpty())
                            uriBuilder.queryParam("plate", command.getPlate());

                        if (command.getIsParked() != null)
                            uriBuilder.queryParam("isParked", command.getIsParked());

                        return uriBuilder.build();
                })
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> Mono.error(new InfraException(InfraErrorCode.FAILURE_TO_READ_CAR_INFO)))
                .bodyToFlux(ExternalReadCarInfoResponse.class)
                .collectList()
                .block();

        List<ParkingInfo> infos = new ArrayList<>();

        for (ExternalReadCarInfoResponse response : Objects.requireNonNull(responses)) {
            infos.add(mapper.toInfo(response));
        }

        return infos;
    }
}
