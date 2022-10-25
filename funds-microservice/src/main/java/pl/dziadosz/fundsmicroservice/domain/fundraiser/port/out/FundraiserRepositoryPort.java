package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out;

import java.math.BigDecimal;
import java.util.Optional;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;

public interface FundraiserRepositoryPort {
    Optional<Fundraiser> findFundraiserWithAccountId(Long fundraiserId, Long accountId);
    Optional<FundraiserModel> findFundraiserById(Long fundraiserId);
    BigDecimal findWithdrawalSummary(Long fundraiserId);
    BigDecimal findDepositSummary(Long fundraiserId);
    Fundraiser save(Fundraiser fundraiser);
    FundraiserEvent save(FundraiserEvent fundraiserEvent);
}
