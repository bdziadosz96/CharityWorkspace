package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser;

import java.math.BigDecimal;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserModel;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEventEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

@RequiredArgsConstructor
public class FundraiserDatabaseAdapter implements FundraiserRepositoryPort {
    private final FundraiserRepository fundraiserRepository;
    private final FundraiserEventRepository fundraiserEventRepository;

    @Override
    public Optional<Fundraiser> findFundraiserWithAccountId(final Long fundraiserId, final Long accountId) {
        return fundraiserRepository
                .findByIdAndAccountId(fundraiserId, accountId)
                .map(entity -> new Fundraiser(entity.getId(),
                        entity.getAccountId(),
                        entity.getName(),
                        entity.getBalance()));
    }

    @Override
    public Optional<FundraiserModel> findFundraiserById(final Long fundraiserId) {
        return fundraiserRepository.findById(fundraiserId)
                .map(entity -> new FundraiserModel(entity.getId(),
                        entity.getAccountId(),
                        entity.getName(),
                        entity.getBalance()));
    }

    @Override
    public BigDecimal findWithdrawalSummary(final Long fundraiserId) {
        return fundraiserEventRepository.findWithdrawalSummaryFor(fundraiserId)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal findDepositSummary(final Long fundraiserId) {
        return fundraiserEventRepository.findDepositSummaryFor(fundraiserId)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public Fundraiser save(final Fundraiser fundraiser) {
        FundraiserEntity save = fundraiserRepository.save(new FundraiserEntity(
                fundraiser.getId(),
                fundraiser.getAccountId(),
                fundraiser.getName(),
                fundraiser.getBalance()
        ));
        return new Fundraiser(save.getId(), save.getAccountId(), save.getName(), save.getBalance());
    }

    @Override
    public FundraiserEvent save(final FundraiserEvent fundraiserEvent) {
        FundraiserEventEntity save = fundraiserEventRepository.save(new FundraiserEventEntity(
                fundraiserEvent.getUuid(),
                fundraiserEvent.getFundraiserId(),
                fundraiserEvent.getAccountId(),
                fundraiserEvent.getAmount(),
                fundraiserEvent.getEventType()
        ));
        return new FundraiserEvent(save.getUuid(), save.getFundraiserId(), save.getAccountId(), save.getAmount(), save.getEventType());
    }
}
