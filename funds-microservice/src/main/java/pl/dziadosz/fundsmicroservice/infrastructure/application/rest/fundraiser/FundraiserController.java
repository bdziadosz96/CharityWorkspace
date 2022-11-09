package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserDepositPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserInformationPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;

@RestController
@RequestMapping("fundraise")
@RequiredArgsConstructor
public class FundraiserController {
    private final FundraiserRestMapper mapper;
    private final FundraiserDepositPort depositPort;
    private final FundraiserWithdrawalPort withdrawalPort;
    private final FundraiserInformationPort informationPort;

    @PostMapping("withdraw")
    public FundraiserEventDto withdraw(@RequestBody FundraiserWithdrawalDto fundraiserWithdrawalDto) {
        FundraiserWithdrawal fundraiserWithdrawal = mapper.withdrawalDtoToModel(fundraiserWithdrawalDto);
        FundraiserEventModel withdraw = withdrawalPort.withdraw(fundraiserWithdrawal);
        return mapper.eventModelToDto(withdraw);
    }

    @PostMapping("deposit")
    public FundraiserEventDto deposit(@RequestBody FundraiserDepositDto fundraiserDepositDto) {
        FundraiserEventModel deposit = depositPort.deposit(mapper.depositDtoModel(fundraiserDepositDto));
        return mapper.eventModelToDto(deposit);
    }

    @GetMapping("information/{fundraiserId}")
    public FundraiserInformationModel information(@PathVariable final Long fundraiserId) {
        return informationPort.findTransactions(fundraiserId);
    }

}
