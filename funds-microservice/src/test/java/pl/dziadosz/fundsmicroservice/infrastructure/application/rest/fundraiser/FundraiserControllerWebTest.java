package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.Optional;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.dziadosz.fundsmicroservice.domain.exception.FundraiserNotFoundException;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserDepositPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({FundraiserController.class})
@ContextConfiguration(classes = WebPersistenceConfiguration.class)
class FundraiserControllerWebTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    FundraiserRepository fundraiserRepository;

    @MockBean
    FundraiserEventRepository eventRepository;

    @MockBean
    FundraiserDepositPort depositPort;

    @MockBean
    FundraiserWithdrawalPort withdrawalPort;

    @Test
    void shouldReturnProperIncomesAndOutcomes() throws Exception {
        Mockito.when(eventRepository.findDepositSummaryFor(1L))
                        .thenReturn(Optional.of(BigDecimal.TEN));

        Mockito.when(eventRepository.findWithdrawalSummaryFor(1L))
                .thenReturn(Optional.of(BigDecimal.valueOf(20)));


        mockMvc.perform(MockMvcRequestBuilders.get("/fundraise/information/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.income", Matchers.is(10)))
                .andExpect(jsonPath("$.outcome", Matchers.is(20)));
    }

    @Test
    void shouldReturnZeroWhenFundraiseNotFound() throws Exception {
        Mockito.when(eventRepository.findDepositSummaryFor(1L))
                .thenReturn(Optional.of(BigDecimal.TEN));

        Mockito.when(eventRepository.findWithdrawalSummaryFor(1L))
                .thenReturn(Optional.of(BigDecimal.valueOf(20)));


        mockMvc.perform(MockMvcRequestBuilders.get("/fundraise/information/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.income", Matchers.is(0)))
                .andExpect(jsonPath("$.outcome", Matchers.is(0)));
    }

    @Test
    void shouldReturnBadRequestWhenDepositFundraiseNotFound() throws Exception {
        Mockito.when(depositPort.deposit(any(FundraiserDeposit.class))).thenThrow(FundraiserNotFoundException.class);

        ObjectMapper objectMapper = new ObjectMapper();
        FundraiserWithdrawalDto fundraiserWithdrawalDto = new FundraiserWithdrawalDto(1L, 1L, "test", BigDecimal.TEN);
        String jsonRequest = objectMapper.writeValueAsString(fundraiserWithdrawalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/fundraise/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnSuccessWhenDepositComplete() throws Exception {
        Mockito.when(depositPort.deposit(any(FundraiserDeposit.class))).thenReturn(
                new FundraiserEventModel("test",1L, 1L, BigDecimal.TEN, FundraiserEventType.DEPOSIT)
        );

        ObjectMapper objectMapper = new ObjectMapper();
        FundraiserDepositDto fundraiserDepositDto = new FundraiserDepositDto(1L, BigDecimal.TEN);
        String jsonRequest = objectMapper.writeValueAsString(fundraiserDepositDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/fundraise/deposit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    void shouldReturnBadRequestWhenWithdrawFundraiseNotFound() throws Exception {
        Mockito.when(withdrawalPort.withdraw(any(FundraiserWithdrawal.class)))
                .thenThrow(FundraiserNotFoundException.class);

        ObjectMapper objectMapper = new ObjectMapper();
        FundraiserWithdrawalDto fundraiserWithdrawalDto = new FundraiserWithdrawalDto(1L, 1L, "test", BigDecimal.TEN);
        String jsonRequest = objectMapper.writeValueAsString(fundraiserWithdrawalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/fundraise/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturnSuccessWhenWithdrawalComplete() throws Exception {
        Mockito.when(withdrawalPort.withdraw(any(FundraiserWithdrawal.class)))
                .thenReturn(new FundraiserEventModel("testuuid",1L, 1L, BigDecimal.TEN, FundraiserEventType.WITHDRAWAL));

        ObjectMapper objectMapper = new ObjectMapper();
        FundraiserWithdrawalDto fundraiserWithdrawalDto = new FundraiserWithdrawalDto(1L, 1L, "test", BigDecimal.TEN);
        String jsonRequest = objectMapper.writeValueAsString(fundraiserWithdrawalDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/fundraise/withdraw")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.uuid",Matchers.is("testuuid")));
    }

}