package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository;

import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEventEntity;

public interface FundraiserEventRepository extends JpaRepository<FundraiserEventEntity, String> {
    @Query("select sum(f.amount) from FundraiserEventEntity f where f.accountId = ?1 and " +
            "f.eventType = pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventTypeModel.WITHDRAWAL")
    Optional<BigDecimal> findWithdrawalSummaryFor(Long id);

    @Query("select sum(f.amount) from FundraiserEventEntity f where f.accountId = ?1 and " +
            "f.eventType = pl.dziadosz.fundsmicroservice.domain.fundraiser.view.FundraiserEventTypeModel.DEPOSIT")
    Optional<BigDecimal> findDepositSummaryFor(Long id);

    @Query("select f from FundraiserEventEntity f where f.accountId = ?1")
    Optional<FundraiserEventEntity> findByAccountId(Long id);
}
