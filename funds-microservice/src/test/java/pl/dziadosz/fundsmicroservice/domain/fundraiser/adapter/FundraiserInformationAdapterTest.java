package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserInformationService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;

import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class FundraiserInformationAdapterTest {
    private FundraiserRepositoryPort repositoryPort = Mockito.mock(FundraiserRepositoryPort.class);
    private FundraiserInformationService informationService = new FundraiserInformationService(repositoryPort);
    private FundraiserInformationAdapter informationAdapter = new FundraiserInformationAdapter(informationService);


    @Test
    void shouldReturnProperAmount() {
        Mockito.when(repositoryPort.findDepositSummary(anyLong())).thenReturn(BigDecimal.TEN);
        Mockito.when(repositoryPort.findWithdrawalSummary(anyLong())).thenReturn(BigDecimal.TEN);

        FundraiserInformationModel transactions = informationAdapter.findTransactions(1L);

        Assertions.assertEquals(BigDecimal.TEN, transactions.income());
        Assertions.assertEquals(BigDecimal.TEN, transactions.outcome());
    }

}