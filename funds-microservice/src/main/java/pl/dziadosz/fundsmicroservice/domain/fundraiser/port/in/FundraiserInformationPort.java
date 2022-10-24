package pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserInformationModel;

public interface FundraiserInformationPort {
    FundraiserInformationModel findTransactions(Long fundraiserId);
}
