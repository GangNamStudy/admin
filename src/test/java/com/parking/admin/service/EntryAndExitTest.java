package com.parking.admin.service;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.parking.admin.application.port.in.ParkingUseCase;
import com.parking.admin.domain.parking.ParkingInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource
        (properties = //값 설정할때 주소는 따옴표 때고 쓸것 "\".."\" 이런식 말고 아래처럼
                {"api.parking-management.parking.entry-car=/api/vehicles/",
                        "api.parking-management.parking.exit-car=/api/vehicles/exit/{plate}",
                        "api.parking-management.parking.load-car.id=/api/vehicles/id/{id}",
                        "api.parking-management.parking.laod-car.plate=/api/vehicles/plate/{numberplate}"
                }
        )
class EntryAndExitTest {

    @Autowired
    ParkingUseCase parkingUseCase;

    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig().dynamicPort()) // 동적 포트 사용
            .build();

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("api.base-url", wireMockServer::baseUrl);
    }

    @Test
    @DisplayName("차량 입차 테스트")
    void entryCarTest(){
        String entryCarJson = """
                {
                  "status": "success",
                  "plate": "ABC123",
                  "entryTime": "2025-02-15T14:00:00"
                }""";

        wireMockServer.stubFor(post(urlPathEqualTo("/api/vehicles/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(entryCarJson)));

        ParkingUseCase.ParkingCommand command = new ParkingUseCase.ParkingCommand("ABC123");

        ParkingInfo info= parkingUseCase.entryCar(command);

        assertThat(info.getEntryTime()).isEqualTo("2025-02-15T14:00:00");
        assertThat(info.getExitTime()).isNull();
        assertThat(info.getPlate()).isEqualTo("ABC123");
        assertThat(info.isParked()).isEqualTo(true);

    }

    @Test
    @DisplayName("차량 출차 테스트")
    void exitCarTest(){
        String getCarJson = """
                {
                  "id": 123,
                  "plate": "ABC123",
                  "entryTime": "2025-02-15T14:00:00",
                  "exitTime": "2025-02-15T18:00:00",
                  "isParked": true
                }""";

        String exitCarJson = """
                {
                  "status": "success",
                  "plate": "ABC123",
                  "entryTime": "2025-02-15T14:00:00"
                }""";

        wireMockServer.stubFor(WireMock.get(urlPathMatching("/api/vehicles/plate/[^/]+"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(getCarJson)));

        wireMockServer.stubFor(WireMock.put(urlPathMatching("/api/vehicles/exit/[^/]+"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(exitCarJson)));

        ParkingUseCase.ParkingCommand command = new ParkingUseCase.ParkingCommand("ABC123");

        ParkingInfo info= parkingUseCase.exitCar(command);

        assertThat(info.getEntryTime()).isEqualTo("2025-02-15T14:00:00");
        assertThat(info.getExitTime()).isInstanceOf(LocalDateTime.class);
        assertThat(info.getPlate()).isEqualTo("ABC123");
        assertThat(info.isParked()).isEqualTo(false);
        wireMockServer.verify(putRequestedFor(urlEqualTo("/api/vehicles/exit/ABC123"))); //패스 파라미터 검증
    }

}
