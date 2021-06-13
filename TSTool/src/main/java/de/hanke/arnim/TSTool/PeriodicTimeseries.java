package de.hanke.arnim.TSTool;


import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import static de.hanke.arnim.TSTool.AggregateFunctions.aggregateSeiresForGivenAggregationType;

public class PeriodicTimeseries implements Timeseries {

    private PeriodicTimeseriesHead periodicTimeseriesHead;

    private List<PeriodicTimeseriesValue> values = null;

    public PeriodicTimeseries() {
        this.periodicTimeseriesHead = new PeriodicTimeseriesHead();
    }

    public PeriodicTimeseries(String tsId, Raster raster, TimeseriesUnit timeseriesUnit, String databaseName, List<PeriodicTimeseriesValue> values) {
        this.periodicTimeseriesHead = new PeriodicTimeseriesHead(tsId, raster, timeseriesUnit, databaseName);
        this.values = values;
    }

    public PeriodicTimeseries(PeriodicTimeseriesHead periodicTimeseriesHead, List<PeriodicTimeseriesValue> values) {
        this.periodicTimeseriesHead = periodicTimeseriesHead;
        this.values = values;
    }

    public String getTsId() {
        return this.getPeriodicTimeseriesHead().getTsId();
    }

    public void setTsId(String tsId) {
        this.getPeriodicTimeseriesHead().setTsId(tsId);
    }

    public Raster getRaster() {
        return getPeriodicTimeseriesHead().getRaster();
    }

    public void setRaster(Raster raster) {
        this.getPeriodicTimeseriesHead().setRaster(raster);
    }

    public List<PeriodicTimeseriesValue> getValues() {
        return values;
    }

    public void setValues(List<PeriodicTimeseriesValue> values) {
        this.values = values;
    }

    public PeriodicTimeseriesHead getPeriodicTimeseriesHead() {
        return periodicTimeseriesHead;
    }

    public PeriodicTimeseries forRaster(Raster raster, Instant start, Instant end) {

        List<PeriodicTimeseriesValue> retValues = aggregateSeiresForGivenAggregationType(AggregationTypes.aggregationConfig.get("heizungssuite_" + this.getTsId()), this.getValues(), start, end, raster, this.getTsId());

        return new PeriodicTimeseries(this.getPeriodicTimeseriesHead(), retValues);
    }

    @Override
    public PeriodicTimeseries fillSeries(Instant start, Instant end, Raster raster) {
        long startFilling = System.currentTimeMillis();
        List<PeriodicTimeseriesValue> filledSeries = AggregateFunctions.fillSeries(this.getValues(), start, end, Raster.getDuration(raster));

        System.out.println("End filling after " + (System.currentTimeMillis() - startFilling) + " for " + this.periodicTimeseriesHead.getTsId());

        return new PeriodicTimeseries(this.getPeriodicTimeseriesHead(), filledSeries);
    }

    @Override
    public List<PeriodicTimeseriesValue> getValues(Interval interval) {
        return this.values.stream().filter(periodicTimeseriesValue -> interval.contains(periodicTimeseriesValue.getTime())).collect(Collectors.toList());
    }

    @Override
    public PeriodicTimeseries forRaster(Raster raster, Interval interval) {
        return this.forRaster(raster, interval.getFrom(), interval.getTo());
    }

    // private Interval gueltigkeitsInterval;

}
