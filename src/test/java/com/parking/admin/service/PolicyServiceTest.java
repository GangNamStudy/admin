package com.parking.admin.service;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockExtension;
import com.parking.admin.application.port.in.PolicyUseCase;
import com.parking.admin.domain.common.Money;
import com.parking.admin.domain.policy.DurationTime;
import com.parking.admin.domain.policy.PolicyInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource
        (properties = //값 설정할때 주소는 따옴표 때고 쓸것 "\".."\" 이런식 말고 아래처럼
                {"api.item-management.policy.read-policy = /api/parking-fees/policy",
                        "api.item-management.policy.update-policy=/api/parking-fees"
                }
        )
class PolicyServiceTest {

    @Autowired
    PolicyUseCase policyUseCase;

    // WireMock 서버를 JUnit 5 Extension으로 등록
    @RegisterExtension
    static WireMockExtension wireMockServer = WireMockExtension.newInstance()
            .options(com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig().dynamicPort()) // 동적 포트 사용
            .build();

    // 테스트 실행 시 동적으로 프로퍼티 값을 변경
    // 여기서는 외부 API의 base-url을 WireMock 서버의 URL로 변경
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("api.base-url", wireMockServer::baseUrl);
    }

    @Test
    @DisplayName("가격 정책 업데이트 서비스 테스트")
    void UpdatePolicyTest() {
        // given: WireMock으로 외부 API의 예상 응답 설정
        String currentPolicyJson =
                "{\"baseFee\": 1000,\"freeTime\": 30, \"additionalFee\": 500,\"additionalTime\": 10}";

        String updatedPolicyJson
                = "{\"baseFee\": 1000,\"freeTime\": 30, \"additionalFee\": 1000,\"additionalTime\": 10}";

        wireMockServer.stubFor(WireMock.get("/api/parking-fees/policy")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(currentPolicyJson)));

        wireMockServer
                .stubFor(WireMock.patch("/api/parking-fees") // 외부 API의 예상 경로 및 파라미터
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                                .withBody(updatedPolicyJson)));

        PolicyUseCase.UpdatePolicyCommand command= new PolicyUseCase.UpdatePolicyCommand(
                null,
                null,
                Money.of(1000L),
                null
        );

        PolicyInfo info = policyUseCase.updatePolicy(command);

        // when & then: MockMvc를 사용하여 컨트롤러에 HTTP GET 요청을 보내고 응답 검증

        assertThat(info.getBaseFee()).isEqualTo(Money.of(1000L));
        assertThat(info.getFreeTime()).isEqualTo(DurationTime.of(30L));
        assertThat(info.getAdditionalFee()).isEqualTo(Money.of(1000L));
        assertThat(info.getAdditionalTime()).isEqualTo(DurationTime.of(10L));

    }

    @Test
    @DisplayName("가격 정책 불러오기 테스트")
    void ReadPolicyTest(){
        String currentPolicyJson =
                "{\"baseFee\": 1000,\"freeTime\": 30, \"additionalFee\": 500,\"additionalTime\": 10}";

        wireMockServer.stubFor(WireMock.get("/api/parking-fees/policy")
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(currentPolicyJson)));

        PolicyInfo info = policyUseCase.getPolicy();

        assertThat(info.getBaseFee()).isEqualTo(Money.of(1000L));
        assertThat(info.getFreeTime()).isEqualTo(DurationTime.of(30L));
        assertThat(info.getAdditionalFee()).isEqualTo(Money.of(500L));
        assertThat(info.getAdditionalTime()).isEqualTo(DurationTime.of(10L));
    }
}
