package de.hanke.arnim.TSServer.timeseriesCatalog;

import com.opencsv.bean.CsvBindByName;
import de.hanke.arnim.TSTool.Raster;
import de.hanke.arnim.TSTool.TimeseriesUnit;

public class TimeSeriesDefinition {


    @CsvBindByName
    private String tsId;

    @CsvBindByName
    private String databaseName;

    @CsvBindByName
    private String timeseriesUnit;

    @CsvBindByName
    private String raster;

    public String getTsId() {
        return tsId;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getTimeseriesUnit() {
        return timeseriesUnit;
    }

    public String getRaster() {
        return raster;
    }
}
