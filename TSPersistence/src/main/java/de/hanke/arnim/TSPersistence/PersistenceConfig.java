package de.hanke.arnim.TSPersistence;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ={"de.hanke.arnim.TSPersistence"})
@EntityScan(basePackages = {"de.hanke.arnim.TSPersistence"})
@ComponentScan(basePackages = {"de.hanke.arnim.TSPersistence"})
public class PersistenceConfig {

}

