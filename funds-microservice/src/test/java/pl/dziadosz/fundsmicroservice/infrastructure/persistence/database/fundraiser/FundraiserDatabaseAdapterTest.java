package pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserEventRepository;
import pl.dziadosz.fundsmicroservice.infrastructure.persistence.database.fundraiser.repository.FundraiserRepository;

@Testcontainers
@DataJpaTest
@ContextConfiguration(classes = PersistenceConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FundraiserDatabaseAdapterTest {
    @Autowired
    private FundraiserRepository repository;

    @Autowired
    private FundraiserEventRepository eventRepository;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test");

    @DynamicPropertySource
    private static void init(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

}