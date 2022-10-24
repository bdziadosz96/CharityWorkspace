package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;

public record FundraiserInformationModel(BigDecimal income, BigDecimal outcome) {
}
