package de.hanke.arnim.TSServer.timeseriesCatalog;


import com.opencsv.bean.CsvToBeanBuilder;
import de.hanke.arnim.TSTool.Raster;
import de.hanke.arnim.TSTool.TimeseriesUnit;
import de.hanke.arnim.TSPersistence.PersistenceConfig;
import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import de.hanke.arnim.TSPersistence.TimeseriesHeadKey;
import de.hanke.arnim.TSPersistence.postgres.PostgreSQLUtils;
import de.hanke.arnim.TSServer.model.*;
import de.hanke.arnim.TSServer.serivce.TimeseriesDefinitionByAPApiService;
import de.hanke.arnim.TSServer.serivce.TimeseriesDefinitionByAPsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//@SpringBootApplication
@Import(value = {PersistenceConfig.class})
@ComponentScan(basePackages = {"de.hanke.arnim.TSServer", "de.hanke.arnim.TSServer.api", "de.hanke.arnim.TSServer.configuration"})
public class TimeSeriesCatalog implements CommandLineRunner {

    @Autowired
    TimeseriesDefinitionByAPsApiService timeseriesDefinitionByAPsApiService;

    public static void main(String[] args) throws FileNotFoundException {

        new SpringApplication(TimeSeriesCatalog.class).run(args);
    }

    public void readTimeseriesDefinitionsFromFile(List<String> filenames) throws FileNotFoundException {

        for (String filename : filenames) {
            List<TimeSeriesDefinition> timeSeriesDefinitionsFromFile = new CsvToBeanBuilder(new InputStreamReader(new FileInputStream(new File("src/main/resources/" + filename))))
                    .withSeparator(';')
                    .withIgnoreEmptyLine(true
                    )
                    .withType(TimeSeriesDefinition.class)
                    .build()
                    .parse();
            System.out.println(timeSeriesDefinitionsFromFile.size());

            List<PersistenceParameterHeadDto> collect = timeSeriesDefinitionsFromFile.stream().map(timeSeriesDefinition -> {
                PersistenceParameterHeadDto timeSeriesHead = new PersistenceParameterHeadDto();
                String tsId = timeSeriesDefinition.getTsId().replace("ä", "ae").replace("ö", "oe").replace("ü", "ue").replace("ß", "ss");
                timeSeriesHead.setTsComposedKey(new TimeSeriesComposedKey().tsId(tsId).databaseName(timeSeriesDefinition.getDatabaseName()));
                timeSeriesHead.setTsraster(TimeSeriesRaster.valueOf(timeSeriesDefinition.getRaster()));
                timeSeriesHead.setTsUnit(TimeSeriesUnit.valueOf(timeSeriesDefinition.getTimeseriesUnit()));
                return timeSeriesHead;
            }).collect(Collectors.toList());

            System.out.println(collect.size());

            List<TimeSeriesHead> timeSeriesHeads = timeseriesDefinitionByAPsApiService.putTimeseriesDefinitionsByPersistenceParameterHeadDtos(collect);

            if (timeSeriesHeads.size() != collect.size()) {
                System.out.println("Nicht alle Koepfe gespeichert....");
            }

        }
    }

    @Override
    public void run(String... args) throws Exception {
        readTimeseriesDefinitionsFromFile(Collections.singletonList("ZRCatalog.Heizungssuite.csv"));
//        readTimeseriesDefinitionsFromFile(Collections.singletonList("ZRCatalog.Heizungssuite.PROD.csv"));
//        readTimeseriesDefinitionsFromFile(Collections.singletonList("ZRCatalog.AllergiedatenDWD.csv"));
    }
}
