package pl.dziadosz.fundsmicroservice.domain.fundraiser.adapter;

import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiseMapper;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.in.FundraiserWithdrawalPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserCashService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSaveService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserSearchService;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.service.FundraiserWebService;

public class FundraiserWithdrawalAdapter implements FundraiserWithdrawalPort {
    private final FundraiserCashService cashService;
    private final FundraiserWebService withdrawalService;
    private final FundraiserSaveService fundraiserSaveService;
    private final FundraiserSearchService searchService;

    public FundraiserWithdrawalAdapter(final FundraiserCashService cashService, final FundraiserWebService withdrawalService,
                                final FundraiserSaveService fundraiserSaveService, final FundraiserSearchService searchService) {
        this.cashService = cashService;
        this.withdrawalService = withdrawalService;
        this.fundraiserSaveService = fundraiserSaveService;
        this.searchService = searchService;
    }

    @Override
    public FundraiserEventModel withdraw(final FundraiserWithdrawal fundraiserWithdrawal) {
        Fundraiser fundraiser = searchService.findFundraiserForWithdrawal(fundraiserWithdrawal);
        Fundraiser actualFundraiser = cashService.makeWithdrawal(fundraiser, fundraiserWithdrawal);
        FundraiserEvent fundraiserEvent = withdrawalService.create(fundraiserWithdrawal);
        fundraiserSaveService.saveFundraiser(actualFundraiser);
        return FundraiseMapper.toModel(fundraiserSaveService.saveFundraiserEvent(fundraiserEvent));
    }
}
