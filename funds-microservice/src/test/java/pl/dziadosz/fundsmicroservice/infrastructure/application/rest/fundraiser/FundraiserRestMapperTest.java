package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import java.math.BigDecimal;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserDeposit;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventModel;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserWithdrawal;

class FundraiserRestMapperTest {
    private final FundraiserRestMapper fundraiserRestMapper = new FundraiserRestMapper();

    @Test
    void withdrawalMappingTest() {
        FundraiserWithdrawalDto fundraiserWithdrawalDto = getFundraiserWithdrawalDto();
        FundraiserWithdrawal withdrawal = fundraiserRestMapper.withdrawalDtoToModel(fundraiserWithdrawalDto);

        Assertions.assertEquals(fundraiserWithdrawalDto.fundraiserId(), withdrawal.fundraiserId());
        Assertions.assertEquals(fundraiserWithdrawalDto.accountId(), withdrawal.accountId());
        Assertions.assertEquals(fundraiserWithdrawalDto.name(), withdrawal.name());
        Assertions.assertEquals(fundraiserWithdrawalDto.balance(), withdrawal.amount());
    }

    @Test
    void depositMappingTest() {
        FundraiserDepositDto fundraiserDepositDto = getFundraiserDepositDto();
        FundraiserDeposit deposit = fundraiserRestMapper.depositDtoModel(fundraiserDepositDto);

        Assertions.assertEquals(fundraiserDepositDto.fundraiserId(), deposit.fundraiserId());
        Assertions.assertEquals(fundraiserDepositDto.amount(), deposit.amount());
    }

    @Test
    void fundraiserEventMappingTest() {
        FundraiserEventDto fundraiserEventDto = getFundraiserEventDto();
        FundraiserEventModel deposit = fundraiserRestMapper.eventDtoToModel(fundraiserEventDto);

        Assertions.assertEquals(fundraiserEventDto.fundraiserId(), deposit.fundraiserId());
        Assertions.assertEquals(fundraiserEventDto.amount(), deposit.amount());
        Assertions.assertEquals(fundraiserEventDto.accountId(), deposit.accountId());
    }

    private FundraiserEventDto getFundraiserEventDto() {
        return new FundraiserEventDto("123test", 1L, 1L, BigDecimal.TEN, FundraiserEventTypeDto.WITHDRAWAL);
    }

    private FundraiserDepositDto getFundraiserDepositDto() {
        return new FundraiserDepositDto(1L, BigDecimal.TEN);
    }

    @NotNull
    private static FundraiserWithdrawalDto getFundraiserWithdrawalDto() {
        return new FundraiserWithdrawalDto(1L, 1L, "testOne", BigDecimal.TEN);
    }
}