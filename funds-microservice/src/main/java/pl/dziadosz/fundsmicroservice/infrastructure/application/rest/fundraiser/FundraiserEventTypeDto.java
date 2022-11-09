package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import java.util.Arrays;

public enum FundraiserEventTypeDto {
    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");

    private final String symbol;

    FundraiserEventTypeDto(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static FundraiserEventTypeDto fromModel(String name) {
        return Arrays.stream(FundraiserEventTypeDto.values())
                .filter(value -> value.symbol.equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
