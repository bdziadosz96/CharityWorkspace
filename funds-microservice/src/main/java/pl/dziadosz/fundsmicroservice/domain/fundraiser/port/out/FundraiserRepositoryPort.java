package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out;

import java.math.BigDecimal;
import java.util.Optional;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;

public interface FundraiserRepositoryPort {
    Optional<Fundraiser> findFundraiserWithAccountId(Long fundraiserId, Long accountId);
    BigDecimal findWithdrawalSummary(Long fundraiserId);
    BigDecimal findDepositSummary(Long fundraiserId);
    Fundraiser save(Fundraiser fundraiser);
    FundraiserEvent save(FundraiserEvent fundraiserEvent);
}
