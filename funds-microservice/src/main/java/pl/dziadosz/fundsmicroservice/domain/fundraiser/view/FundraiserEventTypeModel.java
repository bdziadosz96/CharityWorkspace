package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.util.Arrays;
import pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser.FundraiserEventTypeDto;

public enum FundraiserEventTypeModel {
    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");

    private final String symbol;

    FundraiserEventTypeModel(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static FundraiserEventTypeModel fromDto(String name) {
        return Arrays.stream(FundraiserEventTypeModel.values())
                .filter(value -> value.symbol.equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
