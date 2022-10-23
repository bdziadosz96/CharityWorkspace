package pl.dziadosz.fundsmicroservice.domain.fundraiser.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class FundraiserEvent {
    private String uuid;
    private Long fundraiserId;
    private Long accountId;
    private BigDecimal amount;
    private FundraiserEventType eventType;

    public static FundraiserEvent fromDeposit(Long fundraiserId, Long accountId, BigDecimal amount) {
        return new FundraiserEvent(UUID.randomUUID().toString(), fundraiserId, accountId, amount, FundraiserEventType.DEPOSIT);
    }

    public static FundraiserEvent fromWithdrawal(Long fundraiserId, Long accountId, BigDecimal amount) {
        return new FundraiserEvent(UUID.randomUUID().toString(), fundraiserId, accountId, amount, FundraiserEventType.WITHDRAWAL);
    }

}
