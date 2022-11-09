package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;

public record FundraiserModel(Long id, Long accountId, String name, BigDecimal balance) {
}
