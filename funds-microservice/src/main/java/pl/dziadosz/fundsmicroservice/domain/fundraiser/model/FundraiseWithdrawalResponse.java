package pl.dziadosz.fundsmicroservice.domain.fundraiser.model;

import java.math.BigDecimal;

public record FundraiseWithdrawalResponse(Long fundraiseId, Long accountId, BigDecimal amount) {
}
