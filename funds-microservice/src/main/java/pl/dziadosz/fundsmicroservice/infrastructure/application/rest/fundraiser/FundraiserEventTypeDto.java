package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

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
}
