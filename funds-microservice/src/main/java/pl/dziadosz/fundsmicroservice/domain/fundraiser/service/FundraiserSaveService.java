package pl.dziadosz.fundsmicroservice.domain.fundraiser.service;

import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;

@RequiredArgsConstructor
public class FundraiserSaveService {
    //TODO: wydzielić save/read repository
    private final FundraiserRepositoryPort fundraiserRepositoryPort;

    public Fundraiser saveFundraiser(Fundraiser fundraiser) {
        return fundraiserRepositoryPort.save(fundraiser);
    }

    public FundraiserEvent saveFundraiserEvent(FundraiserEvent fundraiserEvent) {
        return fundraiserRepositoryPort.save(fundraiserEvent);
    }

    public FundraiserEvent combineTransaction(Fundraiser fundraiser, FundraiserEvent fundraiserEvent) {
        fundraiserRepositoryPort.save(fundraiser);
        return fundraiserRepositoryPort.save(fundraiserEvent);
    }
}
