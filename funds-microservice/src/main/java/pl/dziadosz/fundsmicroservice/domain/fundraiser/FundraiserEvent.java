package pl.dziadosz.fundsmicroservice.domain.fundraiser;

import java.math.BigDecimal;
import java.util.UUID;

public class FundraiserEvent {
    private String uuid;
    private Long fundraiserId;
    private Long accountId;
    private BigDecimal amount;
    private FundraiserEventType eventType;

    public FundraiserEvent(final String uuid, final Long fundraiserId, final Long accountId, final BigDecimal amount, final FundraiserEventType eventType) {
        this.uuid = uuid;
        this.fundraiserId = fundraiserId;
        this.accountId = accountId;
        this.amount = amount;
        this.eventType = eventType;
    }

    public String getUuid() {
        return uuid;
    }

    public Long getFundraiserId() {
        return fundraiserId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public FundraiserEventType getEventType() {
        return eventType;
    }

    public static FundraiserEvent fromDeposit(Long fundraiserId, Long accountId, BigDecimal amount) {
        return new FundraiserEvent(UUID.randomUUID().toString(), fundraiserId, accountId, amount, FundraiserEventType.DEPOSIT);
    }

    public static FundraiserEvent fromWithdrawal(Long fundraiserId, Long accountId, BigDecimal amount) {
        return new FundraiserEvent(UUID.randomUUID().toString(), fundraiserId, accountId, amount, FundraiserEventType.WITHDRAWAL);
    }

}
