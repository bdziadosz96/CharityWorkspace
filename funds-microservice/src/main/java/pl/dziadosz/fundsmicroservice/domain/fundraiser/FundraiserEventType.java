package pl.dziadosz.fundsmicroservice.domain.fundraiser;

import java.util.Arrays;

public enum FundraiserEventType {
    WITHDRAWAL("withdrawal"),
    DEPOSIT("deposit");

    private final String symbol;

    FundraiserEventType(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static FundraiserEventType fromDto(String name) {
        return Arrays.stream(FundraiserEventType.values())
                .filter(value -> value.symbol.equals(name))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }
}
