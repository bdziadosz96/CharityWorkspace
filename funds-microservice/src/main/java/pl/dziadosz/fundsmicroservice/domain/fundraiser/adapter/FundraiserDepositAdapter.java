package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserMapper;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserDepositPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserCashService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSaveService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSearchService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;

public class FundraiserDepositAdapter implements FundraiserDepositPort {
    private final FundraiserCashService cashService;
    private final FundraiserSaveService fundraiserSaveService;
    private final FundraiserSearchService searchService;

    public FundraiserDepositAdapter(final FundraiserCashService cashService,
                                    final FundraiserSaveService fundraiserSaveService,
                                    final FundraiserSearchService searchService) {
        this.fundraiserSaveService = fundraiserSaveService;
        this.cashService = cashService;
        this.searchService = searchService;
    }

    @Override
    public FundraiserEventModel deposit(final FundraiserDeposit fundraiserDeposit) {
        Fundraiser fundraiser = searchService.findFundraiserForDeposit(fundraiserDeposit);
        Fundraiser fundraiserAfterDeposit = cashService.makeDeposit(fundraiser, fundraiserDeposit);
        FundraiserEvent fundraiserEventModel = FundraiserEvent.fromDeposit(fundraiserAfterDeposit, fundraiserDeposit.amount());
        FundraiserEvent fundraiserEvent = fundraiserSaveService.combineTransaction(fundraiserAfterDeposit, fundraiserEventModel);
        return FundraiserMapper.toModel(fundraiserEvent);
    }
}
