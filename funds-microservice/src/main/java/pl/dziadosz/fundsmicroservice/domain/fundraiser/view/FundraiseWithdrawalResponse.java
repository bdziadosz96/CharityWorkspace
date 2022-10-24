package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;

public record FundraiseWithdrawalResponse(Long fundraiseId, Long accountId, BigDecimal amount) {
}
