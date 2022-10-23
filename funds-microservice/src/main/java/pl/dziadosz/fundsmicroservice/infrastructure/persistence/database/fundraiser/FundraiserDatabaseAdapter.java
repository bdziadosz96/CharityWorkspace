package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.Fundraiser;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.model.FundraiserEvent;
import pl.dziadosz.fundsmicroservice.domain.fundraiser.port.out.FundraiserRepositoryPort;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEventEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

@RequiredArgsConstructor
public class FundraiserDatabaseAdapter implements FundraiserRepositoryPort {
    private final FundraiserRepository fundraiserRepository;
    private final FundraiserEventRepository fundraiserEventRepository;

    @Override
    public List<Fundraiser> findAll() {
        return fundraiserRepository.findAll()
                .stream()
                .map(entity -> new Fundraiser(
                        entity.getId(),
                        entity.getAccountId(),
                        entity.getName(),
                        entity.getBalance()
                )).toList();
    }

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
