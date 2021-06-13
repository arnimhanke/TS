package de.hanke.arnim.TSPersistence.influx;

import de.hanke.arnim.TSTool.PeriodicTimeseriesValue;

import java.util.List;

public class InfluxTimeseries {

    private String tsId;
    private String databaseName;
    private List<PeriodicTimeseriesValue> values;

    public InfluxTimeseries(String tsId, String databaseName, List<PeriodicTimeseriesValue> values) {
        this.tsId = tsId;
        this.databaseName = databaseName;
        this.values = values;
    }

    public String getTsId() {
        return tsId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public List<PeriodicTimeseriesValue> getValues() {
        return values;
    }

}
