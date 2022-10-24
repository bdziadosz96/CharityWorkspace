package pl.dziadosz.fundsmicroservice.domain.fundraiser;

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
}
