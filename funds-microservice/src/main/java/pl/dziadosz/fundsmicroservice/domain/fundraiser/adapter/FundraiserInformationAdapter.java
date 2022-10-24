package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserInformationPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserInformationService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;

public class FundraiserInformationAdapter implements FundraiserInformationPort {
    private final FundraiserInformationService informationService;

    public FundraiserInformationAdapter(final FundraiserInformationService informationService) {
        this.informationService = informationService;
    }

    @Override
    public FundraiserInformationModel findTransactions(final Long fundraiserId) {
        return informationService.findInformation(fundraiserId);
    }
}
