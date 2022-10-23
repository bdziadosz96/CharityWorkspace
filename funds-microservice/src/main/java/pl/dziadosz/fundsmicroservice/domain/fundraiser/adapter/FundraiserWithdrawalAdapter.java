package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserPrivilegeService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserWebService;

@RequiredArgsConstructor
@Slf4j
public class FundraiserWithdrawalAdapter implements FundraiserWithdrawalPort {
    private final FundraiserWebService withdrawalService;
    private final FundraiserService fundraiserService;
    private final FundraiserPrivilegeService privilegeService;

    @Override
    public FundraiserEvent withdraw(final FundraiserWithdrawal fundraiserWithdrawal) {
        Fundraiser fundraiser = privilegeService.checkAuthority(fundraiserWithdrawal);
        FundraiserEvent fundraiserEvent = withdrawalService.create(fundraiserWithdrawal);

        return fundraiserService.saveFundraiserEvent(fundraiserEvent);
    }
}
