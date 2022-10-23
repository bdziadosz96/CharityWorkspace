package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEventEntity;

public interface FundraiserEventRepository extends JpaRepository<FundraiserEventEntity, String> {
}
