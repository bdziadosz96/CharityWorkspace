package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import java.util.List;
import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserServicePort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserService;

@RequiredArgsConstructor
public class FundraiserServiceAdapter implements FundraiserServicePort {
    private final FundraiserService fundraiserService;

    @Override
    public Fundraiser save(final Fundraiser fundraiser) {
        return fundraiserService.saveFundraiser(fundraiser);
    }

    public List<Fundraiser> getAll() {
        return fundraiserService.getFundraisers();
    }
}
