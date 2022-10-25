package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserDepositPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserCashService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSaveService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSearchService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;

public class FundraiserDepositAdapter implements FundraiserDepositPort {
    private final FundraiserSaveService fundraiserSaveService;
    private final FundraiserCashService cashService;
    private final FundraiserSearchService searchService;

    public FundraiserDepositAdapter(final FundraiserSaveService fundraiserSaveService, final FundraiserCashService cashService, final FundraiserSearchService searchService) {
        this.fundraiserSaveService = fundraiserSaveService;
        this.cashService = cashService;
        this.searchService = searchService;
    }

    @Override
    public FundraiserEventModel deposit(final FundraiserDeposit fundraiserDeposit) {
        FundraiserModel fundraiser = searchService.findFundraiserForDeposit(fundraiserDeposit);
        FundraiserModel fundraiserAfterDeposit = cashService.makeDeposit(fundraiser, fundraiserDeposit);
        FundraiserEventModel fundraiserEventModel = FundraiserEventModel.fromDeposit(fundraiserAfterDeposit, fundraiserDeposit.amount());
        return fundraiserSaveService.combineTransaction(fundraiserAfterDeposit, fundraiserEventModel);
    }
}
