package de.hanke.arnim.TSServer.serivce;

import de.hanke.arnim.TSPersistence.influx.InfluxDBService;
import de.hanke.arnim.TSTool.Interval;
import de.hanke.arnim.TSTool.PeriodicTimeseries;
import de.hanke.arnim.TSTool.PeriodicTimeseriesValue;
import de.hanke.arnim.TSPersistence.influx.InfluxTimeseries;
import de.hanke.arnim.TSServer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static de.hanke.arnim.TSServer.serivce.TimeseriesHeaderServiceInterface.parseTimeSeriesHeadToPeriodicTimeSeriesHead;

@Component
public class TimeseriesValuesByTSHeadsApiService {

    @Autowired
    TimeseriesDefinitionByAPApiService timeseriesDefinitionByAPApiService;

    public List<Timeseries> getTimeseriesValuesByAccessParameterValuesDtos(List<AccessParameterValuesDto> accessParameterValuesDtos, String intervalAsString) {
        return this.getTimeseriesValuesByAccessParameterValuesDtos(accessParameterValuesDtos, intervalAsString, false);
    }

    public List<Timeseries> getTimeseriesValuesByAccessParameterValuesDtos(List<AccessParameterValuesDto> accessParameterValuesDtos, String intervalAsString, boolean fillSeries) {

        Interval interval = Interval.parse(intervalAsString);

        Map<String, List<AccessParameterValuesDto>> groupedByDatabaseName = new HashMap<>();
        accessParameterValuesDtos.forEach(timeseriesHead -> {
            groupedByDatabaseName.computeIfAbsent(timeseriesHead.getTsComposedKey().getDatabaseName().replace("StiebelEltronHeatPumpCorrectedDatasTest", "StiebelEltronHeatPumpRawDatas"), (s) -> new ArrayList<>());
            groupedByDatabaseName.get(timeseriesHead.getTsComposedKey().getDatabaseName().replace("StiebelEltronHeatPumpCorrectedDatasTest", "StiebelEltronHeatPumpRawDatas")).add(timeseriesHead);
        });

        List<List<Timeseries>> listOfTimeseriesGroupedByDatabaseName = groupedByDatabaseName.entrySet().parallelStream().map(databaseNameToTimeseriesHead -> {
            List<String> tsIDs = databaseNameToTimeseriesHead.getValue().stream().map(accessParameterValuesDto -> accessParameterValuesDto.getTsComposedKey().getTsId()).collect(Collectors.toList());

            Map<String, List<PeriodicTimeseriesValue>> timeseriesForInterval = InfluxDBService.getTimeseriesForInterval(tsIDs, databaseNameToTimeseriesHead.getKey(), interval.getFrom(), interval.getTo());

            List<Timeseries> timeseriesPerDatabaseName = timeseriesForInterval.entrySet().parallelStream().map(timeseriesEntry -> {
                List<PeriodicTimeseriesValue> values = timeseriesEntry.getValue();

                TimeSeriesComposedKey tsComposedKey = new TimeSeriesComposedKey().tsId(timeseriesEntry.getKey()).databaseName(databaseNameToTimeseriesHead.getKey().replace("StiebelEltronHeatPumpRawDatas", "StiebelEltronHeatPumpCorrectedDatasTest"));
                TimeSeriesHead timeseriesHead = timeseriesDefinitionByAPApiService.getTimeseriesDefinitionByAccessParamterHeadDto(new AccessParamterHeadDto().tsComposedKey(tsComposedKey));

                if(timeseriesHead == null) {
                    System.out.println("Kein TimeseriesHead gefunden fuer " + tsComposedKey.getTsId());
                    return null;
                }

                PeriodicTimeseries periodicTimeseries = new PeriodicTimeseries(parseTimeSeriesHeadToPeriodicTimeSeriesHead(timeseriesHead), values);

                if(fillSeries) {
                    periodicTimeseries = periodicTimeseries.fillSeries(interval.getFrom(), interval.getTo(), periodicTimeseries.getRaster());
                }

//                long startAggregation = System.currentTimeMillis();
//                periodicTimeseries.forRaster(Raster.fromValue(periodicTimeseries.getRaster().toString()), interval.getFrom(), interval.getTo());
//                System.out.println("Aggregation : " + (System.currentTimeMillis() - startAggregation) + "");

                Timeseries timeseries = new Timeseries();
                timeseries.setTimeSeriesHead(timeseriesHead);
                timeseries.setTimeSeriesValues(
                        periodicTimeseries.getValues().stream()
                                .map(periodicTimeseriesValue -> new TimeSeriesValue().time(periodicTimeseriesValue.getTime()).value(BigDecimal.valueOf(periodicTimeseriesValue.getValue())))
                                .collect(Collectors.toList()));
                return timeseries;
            }).collect(Collectors.toList());

            return timeseriesPerDatabaseName;


        }).collect(Collectors.toList());

        ArrayList<Timeseries> timeseries = new ArrayList<>();
        listOfTimeseriesGroupedByDatabaseName.forEach(timeseries::addAll);
        return timeseries;
    }

    public List<Timeseries> putTimeseriesValuesByPersistenceParameterValuesDtos(List<PersistenceParameterValuesDto> persistenceParameterValuesDtos) {

        Map<String, List<PersistenceParameterValuesDto>> groupedByDatabaseName = new HashMap<>();

        // Group by Databasename
        persistenceParameterValuesDtos.forEach(timeseries -> {
            String database = timeseries.getTsComposedKey().getDatabaseName();
            groupedByDatabaseName.computeIfAbsent(database, (s) -> new ArrayList<>());
            groupedByDatabaseName.get(database).add(timeseries);
        });

        ArrayList<Timeseries> timeseriesToReturn = new ArrayList<>();

        // Save Timeseries divided by database
        groupedByDatabaseName.entrySet().parallelStream().forEach(databaseNameToTimeseriesList -> {
            List<InfluxTimeseries> tsDtos = new ArrayList<>();

            // Timeseries to PeriodicTimeseries
            databaseNameToTimeseriesList.getValue().forEach(timeseries -> {

                ArrayList<PeriodicTimeseriesValue> values = new ArrayList<>();

                timeseries.getTsvalues().forEach(timeseriesValue -> {
                    values.add(new PeriodicTimeseriesValue(timeseriesValue.getTime(), timeseriesValue.getValue().doubleValue()));
                });

                InfluxTimeseries tsDto = new InfluxTimeseries(timeseries.getTsComposedKey().getTsId(), timeseries.getTsComposedKey().getDatabaseName(), values);

                tsDtos.add(tsDto);
            });

            boolean failedToSaveHeads = false;

            Map<TimeSeriesComposedKey, TimeSeriesHead> timeSeriesComposedKeyTimeSeriesHeadMap = new HashMap<>();
            for (PersistenceParameterValuesDto timeseries : databaseNameToTimeseriesList.getValue()) {

                TimeSeriesComposedKey tsComposedKey = timeseries.getTsComposedKey();
                TimeSeriesHead timeseriesHead = timeseriesDefinitionByAPApiService.getTimeseriesDefinitionByAccessParamterHeadDto(new AccessParamterHeadDto().tsComposedKey(tsComposedKey));
                if (timeseriesHead == null) {
                    failedToSaveHeads = true;
                } else {
                    timeSeriesComposedKeyTimeSeriesHeadMap.put(tsComposedKey, timeseriesHead);
                }
            }

            if (!failedToSaveHeads) {
                // Save list of PeriodicTimerseries
                List<InfluxTimeseries> influxTimeseriesSaved = InfluxDBService.putTimeseriesValues(tsDtos, databaseNameToTimeseriesList.getKey());
                for (InfluxTimeseries influxTimeseries : influxTimeseriesSaved) {
                    TimeSeriesComposedKey timeSeriesComposedKey = new TimeSeriesComposedKey().databaseName(influxTimeseries.getDatabaseName()).tsId(influxTimeseries.getTsId());
                    TimeSeriesHead timeSeriesHead = timeSeriesComposedKeyTimeSeriesHeadMap.get(timeSeriesComposedKey);
                    ArrayList<TimeSeriesValue> timeSeriesValues = new ArrayList<>();
                    influxTimeseries.getValues().forEach(periodicTimeseriesValue -> timeSeriesValues.add(new TimeSeriesValue().time(periodicTimeseriesValue.getTime()).value(BigDecimal.valueOf(periodicTimeseriesValue.getValue()))));
                    timeseriesToReturn.add(new Timeseries().timeSeriesHead(timeSeriesHead).timeSeriesValues(timeSeriesValues));
                }
            } else {
                System.err.println("Failed to Save Values");
            }
        });


        return timeseriesToReturn;
    }

    public boolean deleteTimeseriesValuesByDeleteParametersValuesDto(List<DeleteParameterValuesDto> deleteParametersValuesDto, String intervalAsString) {

        Interval interval = Interval.parse(intervalAsString);
        boolean erfolgreich = true;

        for (DeleteParameterValuesDto deleteParameterValuesDto : deleteParametersValuesDto) {
            boolean b = InfluxDBService.deleteTimeseriesValues(deleteParameterValuesDto.getTsId(), deleteParameterValuesDto.getDatabaseName(), interval.getFrom(), interval.getTo());
            if(!b) {
                erfolgreich = false;
            }
        }

        return erfolgreich;
    }
}
