package de.hanke.arnim.TSServer.serivce;

import de.hanke.arnim.TSPersistence.influx.InfluxDBService;
import de.hanke.arnim.TSTool.Interval;
import de.hanke.arnim.TSTool.PeriodicTimeseries;
import de.hanke.arnim.TSTool.PeriodicTimeseriesValue;
import de.hanke.arnim.TSTool.Raster;
import de.hanke.arnim.TSPersistence.influx.InfluxTimeseries;
import de.hanke.arnim.TSServer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.hanke.arnim.TSServer.serivce.TimeseriesHeaderServiceInterface.parseTimeSeriesHeadToPeriodicTimeSeriesHead;


@Component
public class TimeseriesValuesByTSHeadApiService {

    @Autowired
    TimeseriesDefinitionByAPApiService timeseriesDefinitionByAPApiService;

    public Timeseries getTimeseriesValuesByAccessParameterValuesDto(AccessParameterValuesDto accessParameterValuesDto, String intervalAsString) {
        return this.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValuesDto, intervalAsString, false);
    }

    public Timeseries getTimeseriesValuesByAccessParameterValuesDto(AccessParameterValuesDto accessParameterValuesDto, String intervalAsString, boolean fillSeries) {

        Interval interval = Interval.parse(intervalAsString);

        List<PeriodicTimeseriesValue> timeseriesForInterval = InfluxDBService.getTimeseriesForInterval(accessParameterValuesDto.getTsComposedKey().getTsId(), accessParameterValuesDto.getTsComposedKey().getDatabaseName(), interval.getFrom(), interval.getTo());
        if (timeseriesForInterval == null) {
            return null;
        }

        TimeSeriesHead timeseriesHead = timeseriesDefinitionByAPApiService.getTimeseriesDefinitionByAccessParamterHeadDto(new AccessParamterHeadDto().tsComposedKey(accessParameterValuesDto.getTsComposedKey()));

        if(timeseriesHead == null) {
            System.out.println("Kein TimeseriesHead gefunden fuer " + accessParameterValuesDto.getTsComposedKey().getTsId());
            return null;
        }

        PeriodicTimeseries periodicTimeseries = new PeriodicTimeseries(parseTimeSeriesHeadToPeriodicTimeSeriesHead(timeseriesHead), timeseriesForInterval);

        if(fillSeries) {
            periodicTimeseries = periodicTimeseries.fillSeries(interval.getFrom(), interval.getTo(), periodicTimeseries.getRaster());
        }

//        long startAggregation = System.currentTimeMillis();
//        periodicTimeseries.forRaster(Raster.fromValue(accessParameterValuesDto.getTsraster().toString()), interval.getFrom(), interval.getTo());
//        System.out.println("Aggregation : " + (System.currentTimeMillis() - startAggregation) + "");

        Timeseries timeseries = new Timeseries();
        timeseries.setTimeSeriesHead(timeseriesHead);
        timeseries.setTimeSeriesValues(
                periodicTimeseries.getValues().stream()
                        .map(periodicTimeseriesValue -> new TimeSeriesValue().time(periodicTimeseriesValue.getTime()).value(BigDecimal.valueOf(periodicTimeseriesValue.getValue())))
                        .collect(Collectors.toList()));

        return timeseries;
    }

    public Timeseries putTimeseriesValuesByPersistenceParameterValuesDto(PersistenceParameterValuesDto persistenceParameterValuesDto) {

        PeriodicTimeseries tsDto = new PeriodicTimeseries();
        TimeSeriesHead timeSeriesHead = timeseriesDefinitionByAPApiService.getTimeseriesDefinitionByAccessParamterHeadDto(new AccessParamterHeadDto().tsComposedKey(persistenceParameterValuesDto.getTsComposedKey()));

        if(timeSeriesHead == null) {
            System.out.println("Kein TimeseriesHead gefunden fuer " + persistenceParameterValuesDto.getTsComposedKey().getTsId());
            return null;
        }

        tsDto.setRaster(Raster.fromValue(timeSeriesHead.getTsRaster().toString()));
        tsDto.setTsId(timeSeriesHead.getTsId());
        ArrayList<PeriodicTimeseriesValue> values = new ArrayList<>();
        persistenceParameterValuesDto.getTsvalues().forEach(timeseriesValue -> {
            values.add(new PeriodicTimeseriesValue(timeseriesValue.getTime(), timeseriesValue.getValue().doubleValue()));
        });
        tsDto.setValues(values);

        TimeSeriesComposedKey tsComposedKey = new TimeSeriesComposedKey().databaseName(timeSeriesHead.getDatabase()).tsId(timeSeriesHead.getTsId());
        TimeSeriesHead timeseriesDefinitionByAP = timeseriesDefinitionByAPApiService.getTimeseriesDefinitionByAccessParamterHeadDto(new AccessParamterHeadDto().tsComposedKey(tsComposedKey));
        if (timeseriesDefinitionByAP != null) {
            InfluxTimeseries influxTimeseries = InfluxDBService.putTimeseriesValues(new InfluxTimeseries(timeSeriesHead.getTsId(), timeSeriesHead.getDatabase(), values));
            ArrayList<TimeSeriesValue> timeSeriesValues = new ArrayList<>();
            influxTimeseries.getValues().forEach(periodicTimeseriesValue -> timeSeriesValues.add(new TimeSeriesValue().time(periodicTimeseriesValue.getTime()).value(BigDecimal.valueOf(periodicTimeseriesValue.getValue()))));

            return new Timeseries().timeSeriesHead(timeSeriesHead).timeSeriesValues(timeSeriesValues);
        }

        return null;
    }

    public boolean deleteTimeseriesValuesByDeleteParameterValuesDto(DeleteParameterValuesDto deleteParameterValuesDto, String intervalAsString) {
        Interval interval = Interval.parse(intervalAsString);
        return InfluxDBService.deleteTimeseriesValues(deleteParameterValuesDto.getTsId(), deleteParameterValuesDto.getDatabaseName(), interval.getFrom(), interval.getTo());
    }
}
