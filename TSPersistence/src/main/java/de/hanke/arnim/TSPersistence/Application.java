package de.hanke.arnim.TSPersistence;

import de.hanke.arnim.TSPersistence.postgres.TimeseriesHeadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {PersistenceConfig.class})
public class Application implements CommandLineRunner {

    @Autowired
    TimeseriesHeadRepository timeseriesHeadRepository;

    public static void main(String[] args) throws Exception {
//        ElasticApmAttacher.attach();
        new SpringApplication(Application.class).run(args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("asdf");

    }
}
