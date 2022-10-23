package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserWithdrawal;

@RequiredArgsConstructor
public class FundraiserPrivilegeService {
    private final FundraiserService service;

    public Fundraiser checkAuthority(FundraiserWithdrawal withdrawal) {
        return service.findFundraiserWithAccountId(withdrawal.fundraiserId(), withdrawal.accountId())
                .map(fundraiser1 -> new Fundraiser(fundraiser1.getId(), fundraiser1.getAccountId(), fundraiser1.getName(), fundraiser1.getBalance()))
                .orElseThrow(EntityNotFoundException::new);
    }
}
