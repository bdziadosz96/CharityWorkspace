package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FundraiserSaveServiceTest {
    @InjectMocks
    FundraiserSaveService saveService;
    @Mock
    FundraiserRepositoryPort repositoryPort;

    @Test
    void shouldSaveFundraiser() {
        Fundraiser fundraiser = givenFundraiser();
        when(repositoryPort.save(any(Fundraiser.class)))
                .then(invocation -> new Fundraiser(1L, fundraiser.getAccountId(), fundraiser.getName(), fundraiser.getBalance()));

        Fundraiser result = saveService.saveFundraiser(fundraiser);

        Assertions.assertNotEquals(result, fundraiser);
    }

    @Test
    void shouldSaveFundraiserEvent() {
        FundraiserEvent fundraiserEvent = givenFundraiserEvent();
        when(repositoryPort.save(any(FundraiserEvent.class)))
                .then(invocation -> new FundraiserEvent(UUID.randomUUID().toString(),
                        fundraiserEvent.getFundraiserId(), fundraiserEvent.getAccountId(),
                        fundraiserEvent.getAmount(), fundraiserEvent.getEventType()));

        FundraiserEvent result = saveService.saveFundraiserEvent(fundraiserEvent);

        Assertions.assertNotEquals(result, fundraiserEvent);
    }

    @Test
    void shouldSaveFundraiserAndFundraiserEvent() {
        Fundraiser fundraiser = givenFundraiser();
        FundraiserEvent fundraiserEvent = givenFundraiserEvent();
        when(repositoryPort.save(any(Fundraiser.class))).thenReturn(fundraiser);
        when(repositoryPort.save(any(FundraiserEvent.class))).thenReturn(fundraiserEvent);

        saveService.combineTransaction(fundraiser, fundraiserEvent);
        verify(repositoryPort, times(1)).save(any(Fundraiser.class));
        verify(repositoryPort, times(1)).save(any(FundraiserEvent.class));
    }

    private FundraiserEvent givenFundraiserEvent() {
        return new FundraiserEvent(null, 1L, 1L, BigDecimal.TEN, FundraiserEventType.WITHDRAWAL);
    }

    private Fundraiser givenFundraiser() {
        return new Fundraiser(1L, 1L, "Test", BigDecimal.TEN);
    }
}