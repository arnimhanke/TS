package de.hanke.arnim.TSTool;

import java.time.Instant;
import java.util.List;

public interface Timeseries {

    List<PeriodicTimeseriesValue> getValues(Interval interval);

    PeriodicTimeseries forRaster(Raster raster, Interval interval);

    PeriodicTimeseries fillSeries(Instant start, Instant end, Raster raster);


}
