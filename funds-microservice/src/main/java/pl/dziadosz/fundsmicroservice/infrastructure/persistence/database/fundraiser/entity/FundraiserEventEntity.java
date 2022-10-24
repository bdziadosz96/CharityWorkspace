package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class FundraiserEventEntity {
    @Id
    private String uuid;
    private Long fundraiserId;
    private Long accountId;
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private FundraiserEventType eventType;
}
