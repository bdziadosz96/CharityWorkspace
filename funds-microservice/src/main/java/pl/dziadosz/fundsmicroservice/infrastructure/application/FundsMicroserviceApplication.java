package pl.dziadosz.fundsmicroservice.infrastructure.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"pl.dziadosz.fundsmicroservice.infrastructure.persistence.database"})
@EntityScan(basePackages = "pl.dziadosz.fundsmicroservice.infrastructure.persistence.database")
@ComponentScan(basePackages = {"pl.dziadosz.fundsmicroservice.infrastructure"})
public class FundsMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FundsMicroserviceApplication.class, args);
    }

}
