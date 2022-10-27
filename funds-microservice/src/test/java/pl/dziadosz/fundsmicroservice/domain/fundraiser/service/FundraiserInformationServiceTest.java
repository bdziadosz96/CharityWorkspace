package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundraiserInformationServiceTest {
    @InjectMocks
    FundraiserInformationService informationService;
    @Mock
    FundraiserRepositoryPort repositoryPort;

    @Test
    void shouldReturnCorrectValues() {
        when(repositoryPort.findWithdrawalSummary(anyLong())).thenReturn(BigDecimal.TEN);
        when(repositoryPort.findDepositSummary(anyLong())).thenReturn(BigDecimal.TEN);

        FundraiserInformationModel result = informationService.findInformation(1L);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.income(), BigDecimal.TEN);
        Assertions.assertEquals(result.outcome(), BigDecimal.TEN);
    }

}