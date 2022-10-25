package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;
import java.util.UUID;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;

public record FundraiserEventModel(String uuid, Long fundraiserId, Long accountId, BigDecimal amount,
                                   FundraiserEventType eventType) {
    public static FundraiserEventModel fromDeposit(FundraiserModel fundraiserModel, BigDecimal amount) {
        return new FundraiserEventModel(UUID.randomUUID().toString(), fundraiserModel.id()
                , fundraiserModel.accountId(), amount, FundraiserEventType.DEPOSIT);
    }
}