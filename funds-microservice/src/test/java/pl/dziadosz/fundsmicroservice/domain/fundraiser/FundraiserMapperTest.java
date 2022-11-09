package pl.dziadosz.fundsmicroservice.domain.fundraiser;

import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FundraiserMapperTest {

    @Test
    void shouldConvertModelToDto() {
        FundraiserMapper fundraiserMapper = new FundraiserMapper();
        FundraiserEvent event = givenFundraiserEvent();

        FundraiserEventModel result = fundraiserMapper.toModel(event);

        assertEquals(event.getUuid(), result.uuid());
        assertEquals(event.getFundraiserId(), result.fundraiserId());
        assertEquals(event.getAccountId(), result.accountId());
        assertEquals(event.getAmount(), result.amount());
        assertEquals(event.getEventType(), result.eventType());
    }

    private FundraiserEvent givenFundraiserEvent() {
        return new FundraiserEvent("123ABC", 1L, 1L, BigDecimal.TEN, FundraiserEventType.DEPOSIT);
    }
}