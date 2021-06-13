package de.hanke.arnim.TSPersistence.influx;

import de.hanke.arnim.TSTool.PeriodicTimeseriesValue;
import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InfluxDBUtils implements AutoCloseable {

    private final String DATABASEURL = "http://192.168.180.48:8086";
    private final String USERNAME = "heatpump";
    private final String PASSWORD = "<PW>";
    protected InfluxDB influxDB;
    private String dbName;

    public InfluxDBUtils(String dataBaseName) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS);
        this.influxDB = InfluxDBFactory.connect(DATABASEURL, USERNAME, PASSWORD, okHttpClientBuilder);
        this.influxDB.setLogLevel(InfluxDB.LogLevel.NONE);
        influxDB.setRetentionPolicy("autogen");
        influxDB.enableBatch(1000, 100, TimeUnit.MILLISECONDS);
        this.dbName = dataBaseName;
    }

    public static void main(String[] args) {
        InfluxDBUtils test = new InfluxDBUtils("MyTestDB");

//        test.influxDB.createDatabase("StiebelEltronHeatPumpCorrectedDatasTest");
//        test.influxDB.createDatabase("StiebelEltronHeatPumpRawDatasTest"); GRANT ALL ON "StiebelEltronHeatPumpCorrectedDatasTest" TO "heatpump"


        test.close();

    }

    public boolean insertTimeSeries(InfluxTimeseries tsDto) {// tsId, raster, list of values(timstamp, value)
        this.influxDB.setDatabase(this.dbName);
        try {

//            BatchPoints.Builder autogen = BatchPoints
//                    .database(this.dbName)
//                    .retentionPolicy("autogen");

            tsDto.getValues().forEach(val -> {
                Point point = Point.measurement(tsDto.getTsId())
                        .time(val.getTime().toEpochMilli(), TimeUnit.MILLISECONDS)
                        .addField("value", val.getValue())
                        .build();
                influxDB.write(point);

//                autogen.point(point);
            });
//            BatchPoints build = autogen.build();
//            influxDB.write(build);
            return true;
        } catch (Exception e) {
            System.err.println("Fehler bei Index " + tsDto.getTsId());
            e.printStackTrace();
            return false;
        }
    }

    public List<InfluxTimeseries> getTimeSeries(String tsId, Instant from, Instant to) {
        List<InfluxTimeseries> result = new ArrayList<>();
        QueryResult queryResult;

        // Auslesen der Zeitreihe mit dem Zeitinterval in ms (InfluxDB will irgendwie nicht das normale ISO-Format für Datum UND Uhrzeit annehmen)
        String query = "Select * from " + tsId + " where time >= " + from.toEpochMilli() * 1000 * 1000 + " AND time <= " + to.toEpochMilli() * 1000 * 1000;

        try {
            queryResult = this.influxDB.query(new Query(query, this.dbName));
        } catch (Exception e) {
            throw e;
        }


        InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
        // Gesamtmenge an Ergebnissen
        for (QueryResult.Result queryResult2 : queryResult.getResults()) {

            // Einzelne Zeitreihen
            if (queryResult2.getSeries() == null) continue;
            for (QueryResult.Series series : queryResult2.getSeries()) {
                if (series == null) {
                    continue;
                }
                List<String> columns = series.getColumns();
                List<PeriodicTimeseriesValue> valuesNotSorted = new ArrayList<>();


                // Werte <=> namen
                for (List<Object> value : series.getValues()) {
                    PeriodicTimeseriesValue influxTimeseriesValue = new PeriodicTimeseriesValue();

                    // Die Reihenfolge der Spaltennamen und Werte in den Listen ist die gleiche, daher das indirekte Mapping
                    for (int i = 0; i < columns.size(); i++) {
                        Object valueForColumn = value.get(i);
                        try {
                            // Leerzeichen durch Unterstrich ersetzen
                            if (!(valueForColumn instanceof String)) {
                                influxTimeseriesValue.getClass().getDeclaredField(columns.get(i).replace(" ", "_")).set(influxTimeseriesValue, valueForColumn);
                            } else {
                                influxTimeseriesValue.getClass().getDeclaredField(columns.get(i).replace(" ", "_")).set(influxTimeseriesValue, Instant.parse((String) valueForColumn));
                            }
                        } catch (IllegalAccessException | NoSuchFieldException e) {
//                            e.printStackTrace();
                        }
                    }
                    valuesNotSorted.add(influxTimeseriesValue);
                }

                // sort values
                List<PeriodicTimeseriesValue> valuesSorted = new LinkedList<>();
                valuesNotSorted.stream().sorted(Comparator.comparing(PeriodicTimeseriesValue::getTime)).forEach(valuesSorted::add);

                // get missing value if the timestamp pof the first is not = start
                System.out.println("check if query for missing value is needed?");
                if (valuesSorted.get(0).getTime().isAfter(from)) {
                    System.out.println("query for missing value is needed!");
                    String queryStringMissingValue = "Select * from " + tsId + " where time <= " + from.toEpochMilli() * 1000 * 1000 + " order by time desc LIMIT 1";

                    QueryResult queryMissingValue = this.influxDB.query(new Query(queryStringMissingValue, this.dbName));
                    for (QueryResult.Result queryMissingValueResult : queryMissingValue.getResults()) {
                        for (QueryResult.Series queryMissingValueResultSeries : queryMissingValueResult.getSeries()) {

                            System.out.println("length of missing values " + queryMissingValueResultSeries.getValues().size());
                            for (List<Object> value : queryMissingValueResultSeries.getValues()) {
                                PeriodicTimeseriesValue influxTimeseriesValue = new PeriodicTimeseriesValue();

                                // Die Reihenfolge der Spaltennamen und Werte in den Listen ist die gleiche, daher das indirekte Mapping
                                for (int i = 0; i < columns.size(); i++) {
                                    Object valueForColumn = value.get(i);
                                    try {

                                        // Leerzeichen durch Unterstrich ersetzen
                                        if (!(valueForColumn instanceof String)) {
                                            influxTimeseriesValue.getClass().getDeclaredField(columns.get(i).replace(" ", "_")).set(influxTimeseriesValue, valueForColumn);
                                        } else {
                                            influxTimeseriesValue.getClass().getDeclaredField(columns.get(i).replace(" ", "_")).set(influxTimeseriesValue, Instant.parse((String) valueForColumn));
                                        }
                                    } catch (IllegalAccessException | NoSuchFieldException e) {
//                                        e.printStackTrace();
                                    }
                                }
                                valuesSorted.add(influxTimeseriesValue);
                                valuesSorted.sort(Comparator.comparing(PeriodicTimeseriesValue::getTime));
                            }
                        }
                    }
                }


                InfluxTimeseries periodicTimeseries = new InfluxTimeseries(series.getName(), this.dbName, valuesSorted);
                result.add(periodicTimeseries);
            }
        }
        return result;
    }

    public List<InfluxTimeseries> getLastValuesForIndex(String tsId) {
        List<InfluxTimeseries> result = new ArrayList<>();
        QueryResult queryResult;

        // Auslesen der Zeitreihe mit dem Zeitinterval in ms (InfluxDB will irgendwie nicht das normale ISO-Format für Datum UND Uhrzeit annehmen)
        String query = "Select * from " + tsId + " order by time desc LIMIT 1";
        queryResult = this.influxDB.query(new Query(query, this.dbName));

        for (QueryResult.Result queryResult2 : queryResult.getResults()) {

            // Einzelne Zeitreihen
            if (queryResult2.getSeries() == null) continue;
            for (QueryResult.Series series : queryResult2.getSeries()) {
                if (series == null) {
                    continue;
                }
                List<String> columns = series.getColumns();
                ArrayList<PeriodicTimeseriesValue> values = new ArrayList<>();
                InfluxTimeseries influxTimeseries = new InfluxTimeseries(series.getName(), this.dbName, values);

                // Werte <=> namen
                for (List<Object> value : series.getValues()) {
                    PeriodicTimeseriesValue periodicTimeseriesValue = new PeriodicTimeseriesValue();

                    // Die Reihenfolge der Spaltennamen und Werte in den Listen ist die gleiche, daher das indirekte Mapping
                    for (int i = 0; i < columns.size(); i++) {
                        Object valueForColumn = value.get(i);
                        try {
                            // Leerzeichen durch Unterstrich ersetzen
                            if (!(valueForColumn instanceof String)) {
                                periodicTimeseriesValue.getClass().getDeclaredField(columns.get(i).replace(" ", "_")).set(periodicTimeseriesValue, valueForColumn);
                            } else {
                                periodicTimeseriesValue.getClass().getDeclaredField(columns.get(i).replace(" ", "_")).set(periodicTimeseriesValue, Instant.parse((String) valueForColumn));
                            }
                        } catch (IllegalAccessException | NoSuchFieldException e) {
//                            e.printStackTrace();
                        }
                    }
                    values.add(periodicTimeseriesValue);
                }
                result.add(influxTimeseries);
            }
        }
        return result;
    }

    @Override
    public void close() {

        this.influxDB.close();

    }

    public boolean deleteTimeseriesValues(String tsId, Instant from, Instant to) {
        String query = "DELETE from " + tsId + " where time >= " + from.toEpochMilli() * 1000 * 1000 + " AND time <= " + to.toEpochMilli() * 1000 * 1000;
        QueryResult queryResult = this.influxDB.query(new Query(query, this.dbName));

        return !queryResult.hasError();
    }
}
