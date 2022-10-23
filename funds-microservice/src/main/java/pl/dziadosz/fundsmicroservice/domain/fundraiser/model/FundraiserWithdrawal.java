package pl.dziadosz.fundsmicroservice.domain.fundraiser.model;

import java.math.BigDecimal;

public record FundraiserWithdrawal(Long fundraiserId,Long accountId, String name, BigDecimal balance) {
}
