package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

public class FundraiseRestMapper {

    static FundraiserWithdrawalDto toDto(FundraiserWithdrawal fundraiserWithdrawal) {
        return new FundraiserWithdrawalDto(
                fundraiserWithdrawal.fundraiserId(), fundraiserWithdrawal.accountId(),
                fundraiserWithdrawal.name(), fundraiserWithdrawal.balance());
    }

    static FundraiserWithdrawal fundraiseToModel(FundraiserWithdrawalDto fundraiserWithdrawalDto) {
        return new FundraiserWithdrawal(
                fundraiserWithdrawalDto.fundraiserId(), fundraiserWithdrawalDto.accountId(),
                fundraiserWithdrawalDto.name(), fundraiserWithdrawalDto.balance());
    }

    static FundraiserEventDto eventToDto(final FundraiserEventModel fundraiserEventModel) {
        return new FundraiserEventDto(fundraiserEventModel.uuid(), fundraiserEventModel.fundraiserId(),
                fundraiserEventModel.accountId(), fundraiserEventModel.amount(), eventTypeToDto(fundraiserEventModel.eventType()));
    }

    private static FundraiserEventTypeDto eventTypeToDto(FundraiserEventType fundraiserEventType) {
        String symbol = fundraiserEventType.getSymbol();
        return FundraiserEventTypeDto.valueOf(symbol.toUpperCase());

    }
}
