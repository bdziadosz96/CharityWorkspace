package pl.dziadosz.fundsmicroservice.domain.fundraiser.view;

import java.math.BigDecimal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;

public record FundraiserEventModel(String uuid, Long fundraiserId, Long accountId, BigDecimal amount,
                                   FundraiserEventType eventType) {
}