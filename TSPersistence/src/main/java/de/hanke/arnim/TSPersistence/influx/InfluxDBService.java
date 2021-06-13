package de.hanke.arnim.TSPersistence.influx;


import de.hanke.arnim.TSTool.PeriodicTimeseriesValue;
import de.hanke.arnim.TSPersistence.influx.InfluxDBUtils;
import de.hanke.arnim.TSPersistence.influx.InfluxTimeseries;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InfluxDBService {

    /**
     * @return all PeriodicTimeseries from given dbName for given IDs with values in given Interval
     */
    public static Map<String, List<PeriodicTimeseriesValue>> getTimeseriesForInterval(List<String> tsIds, String dbName, Instant from, Instant to) {
        Map<String, List<PeriodicTimeseriesValue>> ret = new HashMap<>();
        long start = System.currentTimeMillis();
        try (InfluxDBUtils utils = new InfluxDBUtils(dbName)) {
            tsIds.parallelStream().forEach(tsId -> {
                try {
                    List<InfluxTimeseries> timeSeries = utils.getTimeSeries(tsId.replace("ä", "ae")
                            .replace("ü", "ue")
                            .replace("ö", "oe"), from, to);
                    if (timeSeries.size() != 1) {
                        System.err.println("Falsche Anzahl " + timeSeries.size() + " an timeseries gefunden für " + tsId);
                    } else {
                        ret.put(tsId, timeSeries.get(0).getValues());
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
            });
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
        System.out.println("Laden fertig nach " + (System.currentTimeMillis() - start));
        return ret;
    }

    /**
     * @return all PeriodicTimeseries from given dbName for given IDs with values in given Interval
     */
    public static List<PeriodicTimeseriesValue> getTimeseriesForInterval(String tsId, String dbName, Instant from, Instant to) {
        long start = System.currentTimeMillis();
        try (InfluxDBUtils utils = new InfluxDBUtils(dbName)) {

            List<InfluxTimeseries> timeSeries = utils.getTimeSeries(tsId.replace("ä", "ae")
                    .replace("ü", "ue")
                    .replace("ö", "oe"), from, to);
            if (timeSeries.size() != 1) {
                System.err.println(tsId);
                //new IllegalStateException("Fuer die ZeitreihenId erwarten wir eine Zeitriehe").printStackTrace();
                return null;
            } else {
                System.out.println((System.currentTimeMillis() - start));
                return timeSeries.get(0).getValues();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    public static InfluxTimeseries putTimeseriesValues(InfluxTimeseries timeseriesToSave) {

        try (InfluxDBUtils influxDBUtils = new InfluxDBUtils(timeseriesToSave.getDatabaseName())) {
            boolean b = influxDBUtils.insertTimeSeries(timeseriesToSave);
            if (!b) {
                throw new RuntimeException("Timeseries couldn't be saved " + timeseriesToSave.getTsId());
            }
        }

        return timeseriesToSave;
    }

    public static List<InfluxTimeseries> putTimeseriesValues(List<InfluxTimeseries> timeseriesToSave, String dbName) {

        try (InfluxDBUtils influxDBUtils = new InfluxDBUtils(dbName)) {
            timeseriesToSave.parallelStream().forEach(periodicTimeseries -> {
                if (periodicTimeseries != null) {

                    boolean b = influxDBUtils.insertTimeSeries(periodicTimeseries);
                    if (!b) {
                        throw new RuntimeException("Timeseries couldn't be saved " + periodicTimeseries.getTsId());
                    }
                }
            });
        }

        return timeseriesToSave;
    }

    public static boolean deleteTimeseriesValues(String tsId, String dbName, Instant from, Instant to) {
        try (InfluxDBUtils influxDBUtils = new InfluxDBUtils(dbName)) {
            return influxDBUtils.deleteTimeseriesValues(tsId, from, to);
        }
    }
}
