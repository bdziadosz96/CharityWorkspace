package pl.dziadosz.fundsmicroservice.infrastructure.web.rest.fundraiser;

import java.io.File;
import java.math.BigDecimal;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.dziadosz.fundsmicroservice.domain.exception.ExternalWithdrawalProcessException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiseWithdrawalResponse;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.infrastructure.web.rest.config.FundraiserRestErrorHandler;

@Testcontainers
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class FundraiserWebAdapterTest {
    private static String dockerComposePATH = "/Users/bartekmac/Desktop/Projekty/charity-application/withdrawal-microservice/docker-compose.yml";
    private RestTemplate restTemplate = new RestTemplateBuilder()
            .errorHandler(new FundraiserRestErrorHandler())
            .build();
    private FundraiserWebAdapter webAdapter = new FundraiserWebAdapter(restTemplate);


//    @Container
//    private static final GenericContainer withdrawalService = new GenericContainer<>(
//            new ImageFromDockerfile().withDockerfile(Path.of("/Users/bartekmac/Desktop/Projekty/charity-application/withdrawal-microservice/Dockerfile"))
//    ).withExposedPorts(8081);
//
//    @BeforeAll
//    static void startup() {
//        withdrawalService.start();
//    }
//
//    @BeforeEach
//    void startupContainer() {
//        withdrawalService.start();
//    }

    @Container
    private static DockerComposeContainer withdrawalService = new DockerComposeContainer(new File(dockerComposePATH));

    @BeforeAll
    static void startup() {
        withdrawalService.start();
    }

    @Test
    @Order(1)
    void shouldReturnResponseWithIdWhenSuccess() {
        FundraiserWithdrawal request = givenRequest();

        FundraiseWithdrawalResponse fundraiseWithdrawalResponse = webAdapter.makeWithdrawCall(request);

        Assertions.assertEquals(1L, fundraiseWithdrawalResponse.fundraiseId());
    }

    @Test
    @Order(2)
    void shouldReturnBadGatewayWhenWithdrawalServiceNotResponding() {
        withdrawalService.stop();
        FundraiserWithdrawal request = givenRequest();

        Assertions.assertThrows(ExternalWithdrawalProcessException.class, () -> webAdapter.makeWithdrawCall(request));
    }

    @NotNull
    private FundraiserWithdrawal givenRequest() {
        return new FundraiserWithdrawal(1L, 1L, "test", BigDecimal.ONE);
    }
}