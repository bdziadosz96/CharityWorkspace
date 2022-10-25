package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import org.springframework.stereotype.Component;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEventType;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

@Component
public class FundraiserRestMapper {

    FundraiserWithdrawalDto withdrawalModelToDto(FundraiserWithdrawal model) {
        return new FundraiserWithdrawalDto(model.fundraiserId(), model.accountId(), model.name(), model.amount());
    }

    FundraiserWithdrawal withdrawalDtoToModel(FundraiserWithdrawalDto dto) {
        return new FundraiserWithdrawal(dto.fundraiserId(), dto.accountId(), dto.name(), dto.balance());
    }

    FundraiserDeposit depositDtoModel(FundraiserDepositDto dto) {
        return new FundraiserDeposit(dto.fundraiserId(), dto.amount());
    }

    FundraiserDepositDto depositModelToDto(FundraiserDeposit model) {
        return new FundraiserDepositDto(model.fundraiserId(), model.amount());
    }

    FundraiserEventDto eventModelToDto(FundraiserEventModel model) {
        return new FundraiserEventDto(model.uuid(), model.fundraiserId(),
                model.accountId(), model.amount(), FundraiserEventTypeDto.fromModel(model.eventType().getSymbol()));
    }

    FundraiserEventModel eventDtoToModel(FundraiserEventDto dto) {
        return new FundraiserEventModel(dto.uuid(), dto.fundraiserId(), dto.accountId(),
                dto.amount(), FundraiserEventType.fromDto(dto.eventTypeDto().getSymbol()));
    }
}
