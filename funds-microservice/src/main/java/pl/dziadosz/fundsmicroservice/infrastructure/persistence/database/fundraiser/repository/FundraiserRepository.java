package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEntity;

public interface FundraiserRepository extends JpaRepository<FundraiserEntity, Long> {
    Optional<FundraiserEntity> findByIdAndAccountId(Long fundraiserId, Long accountId);

    Optional<FundraiserEntity> findById(Long id);
}
