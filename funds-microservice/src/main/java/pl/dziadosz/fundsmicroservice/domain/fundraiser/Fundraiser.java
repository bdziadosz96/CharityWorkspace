package pl.dziadosz.fundsmicroservice.domain.fundraiser;

import java.math.BigDecimal;

public class Fundraiser {
    private Long id;
    private Long accountId;
    private String name;
    private BigDecimal balance;

    public Fundraiser(final Long id, final Long accountId, final String name, final BigDecimal balance) {
        this.id = id;
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
