package pl.dziadosz.fundsmicroservice.domain.fundraiser.model;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Fundraiser {
    private Long id;
    private Long accountId;
    private String name;
    private BigDecimal balance;
}
