package pl.dziadosz.fundsmicroservice.infrastructure.application.rest.fundraiser;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "pl.dziadosz.fundsmicroservice.infrastructure.persistence.database")
@ComponentScan(basePackages = {"pl.dziadosz.fundsmicroservice.infrastructure"})
public class WebPersistenceConfiguration {
}
