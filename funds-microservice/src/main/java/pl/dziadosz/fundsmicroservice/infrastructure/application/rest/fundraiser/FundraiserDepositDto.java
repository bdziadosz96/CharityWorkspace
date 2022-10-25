package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import java.math.BigDecimal;

public record FundraiserDepositDto(Long fundraiserId, BigDecimal amount) {
}
