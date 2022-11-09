package pl.dziadosz.fundsmicroservice.domain.fundraiser;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;

@Component
@AllArgsConstructor
public class FundraiserMapper {

    public FundraiserEventModel toModel(FundraiserEvent event) {
        return new FundraiserEventModel(event.getUuid(), event.getFundraiserId(), event.getAccountId()
                ,event.getAmount(), event.getEventType());
    }
}
