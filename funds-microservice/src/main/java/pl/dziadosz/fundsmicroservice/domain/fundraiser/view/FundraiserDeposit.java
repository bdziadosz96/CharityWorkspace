package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;

public record FundraiserDeposit(Long fundraiserId, BigDecimal amount) {
}
