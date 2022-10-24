package pl.dziadosz.fundsmicroservice.domain.fundraiser;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;

public class FundraiseMapper {
    public static FundraiserEventModel toModel(FundraiserEvent event) {
        return new FundraiserEventModel(event.getUuid(), event.getFundraiserId(), event.getAccountId()
                ,event.getAmount(), event.getEventType());
    }
}
