package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import java.math.BigDecimal;

public record FundraiserWithdrawalDto(Long fundraiserId, Long accountId, String name, BigDecimal balance) {
}
