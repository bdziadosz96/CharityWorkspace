package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import java.math.BigDecimal;

public record FundraiserEventDto(String uuid, Long fundraiserId,
                                 Long accountId, BigDecimal amount, FundraiserEventTypeDto eventTypeDto) {
}
