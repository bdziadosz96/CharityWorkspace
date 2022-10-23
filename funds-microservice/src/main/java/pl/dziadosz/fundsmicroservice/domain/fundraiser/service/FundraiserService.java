package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;

@RequiredArgsConstructor
public class FundraiserService {
    private final FundraiserRepositoryPort fundraiserRepositoryPort;

    public List<Fundraiser> getFundraisers() {
        return fundraiserRepositoryPort.findAll();
    }

    public Fundraiser saveFundraiser(Fundraiser fundraiser) {
        return fundraiserRepositoryPort.save(fundraiser);
    }

    public Optional<Fundraiser> findFundraiserWithAccountId(Long fundraiserId, Long accountId) {
        return fundraiserRepositoryPort.findFundraiserWithAccountId(fundraiserId, accountId);
    }

    public FundraiserEvent saveFundraiserEvent(FundraiserEvent fundraiserEvent) {
        return fundraiserRepositoryPort.save(fundraiserEvent);
    }
}
