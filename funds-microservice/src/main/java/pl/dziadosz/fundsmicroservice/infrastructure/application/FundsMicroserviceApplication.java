package pl.dziadosz.fundsmicroservice.infrastructure.application;

import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.entity.FundraiserEntity;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"pl.dziadosz.fundsmicroservice.infrastructure.persistence.database"})
@EntityScan(basePackages = "pl.dziadosz.fundsmicroservice.infrastructure.persistence.database")
@ComponentScan(basePackages = {"pl.dziadosz.fundsmicroservice.infrastructure"})
public class FundsMicroserviceApplication implements CommandLineRunner{

    @Autowired
    private FundraiserRepository repository;

    @Autowired
    private FundraiserEventRepository eventRepository;

    public static void main(String[] args) {
        SpringApplication.run(FundsMicroserviceApplication.class, args);
    }

    @Override
    public void run(final String... args) {
        FundraiserEntity save = repository.save(
                FundraiserEntity.builder()
                        .accountId(1L)
                        .balance(BigDecimal.TEN)
                        .name("hospital-charity")
                        .build()
        );


        repository.save(save);
//        FundraiserEventEntity event = new FundraiserEventEntity(UUID.randomUUID().toString(),
//                1L,1L,BigDecimal.ONE, FundraiserEventType.WITHDRAWAL);
//
//        FundraiserEventEntity event2 = new FundraiserEventEntity(UUID.randomUUID().toString(),
//                1L,1L,BigDecimal.TEN, FundraiserEventType.WITHDRAWAL);
//
//        eventRepository.saveAll(List.of(event2, event));
    }
}
