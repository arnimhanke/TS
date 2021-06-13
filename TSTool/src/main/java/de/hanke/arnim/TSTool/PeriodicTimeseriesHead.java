package de.hanke.arnim.TSTool;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class PeriodicTimeseriesHead {


    private String tsId = null;

    @Enumerated(EnumType.STRING)
    private Raster raster = null;

    @Enumerated(EnumType.STRING)
    private TimeseriesUnit timeseriesUnit = null;

    private String databaseName = null;

    public PeriodicTimeseriesHead() {
    }

    public PeriodicTimeseriesHead(String tsId, Raster raster, TimeseriesUnit timeseriesUnit, String databaseName) {
        this.tsId = tsId;
        this.raster = raster;
        this.timeseriesUnit = timeseriesUnit;
        this.databaseName = databaseName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }

    public TimeseriesUnit getTimeseriesUnit() {
        return timeseriesUnit;
    }

    public void setTimeseriesUnit(TimeseriesUnit timeseriesUnit) {
        this.timeseriesUnit = timeseriesUnit;
    }

    public String getTsId() {
        return tsId;
    }

    public void setTsId(String tsId) {
        this.tsId = tsId;
    }

    public Raster getRaster() {
        return raster;
    }

    public void setRaster(Raster raster) {
        this.raster = raster;
    }
}
