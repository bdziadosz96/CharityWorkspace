package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserInformationPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;

@RestController
@RequestMapping("fundraise")
@RequiredArgsConstructor
public class FundraiserController {
    private final FundraiserWithdrawalPort withdrawalPort;
    private final FundraiserInformationPort informationPort;

    @PostMapping
    public FundraiserEventDto withdraw(@RequestBody FundraiserWithdrawalDto fundraiserWithdrawalDto) {
        FundraiserWithdrawal fundraiserWithdrawal = FundraiseRestMapper.fundraiseToModel(fundraiserWithdrawalDto);
        FundraiserEventModel withdraw = withdrawalPort.withdraw(fundraiserWithdrawal);
        return FundraiseRestMapper.eventToDto(withdraw);
    }

    @GetMapping("/information/{fundraiserId}")
    public FundraiserInformationModel information(@PathVariable final Long fundraiserId) {
        return informationPort.findTransactions(fundraiserId);
    }

}
