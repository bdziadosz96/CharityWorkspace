package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;

public record FundraiserWithdrawal(Long fundraiserId,Long accountId, String name, BigDecimal balance) {
}
