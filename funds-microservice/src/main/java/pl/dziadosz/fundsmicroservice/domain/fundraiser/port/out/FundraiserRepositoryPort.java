package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out;

import java.util.List;
import java.util.Optional;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;

public interface FundraiserRepositoryPort {
    List<Fundraiser> findAll();
    Optional<Fundraiser> findFundraiserWithAccountId(Long fundraiserId, Long accountId);
    Fundraiser save(Fundraiser fundraiser);
    FundraiserEvent save(FundraiserEvent fundraiserEvent);
}
