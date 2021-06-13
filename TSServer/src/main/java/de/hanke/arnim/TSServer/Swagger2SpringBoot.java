package de.hanke.arnim.TSServer;

import de.hanke.arnim.TSPersistence.PersistenceConfig;
import de.hanke.arnim.TSPersistence.postgres.TimeseriesHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@Import(value = {PersistenceConfig.class})
@ComponentScan(basePackages = {"de.hanke.arnim.TSServer", "de.hanke.arnim.TSServer.api", "de.hanke.arnim.TSServer.configuration"})
@EnableJpaRepositories(basePackages = {"de.hanke.arnim.TSServer.service"})
public class Swagger2SpringBoot implements CommandLineRunner {

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
//        ElasticApmAttacher.attach();
        new SpringApplication(Swagger2SpringBoot.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
