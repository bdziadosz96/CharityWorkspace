package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEventType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class FundraiserEventEntity {
    @Id
    private String uuid;
    private Long fundraiserId;
    private Long accountId;
    private BigDecimal amount;
    private FundraiserEventType eventType;
}
