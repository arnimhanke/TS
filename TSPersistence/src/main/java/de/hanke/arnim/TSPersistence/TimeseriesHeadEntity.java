package de.hanke.arnim.TSPersistence;

import de.hanke.arnim.TSTool.Raster;
import de.hanke.arnim.TSTool.TimeseriesUnit;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TimeseriesHead")
public class TimeseriesHeadEntity {

    @Id
    @EmbeddedId
    private TimeseriesHeadKey id;

    private TimeseriesUnit timeseriesUnit;
    private Raster raster;

    public TimeseriesHeadEntity(TimeseriesHeadKey id, TimeseriesUnit timeseriesUnit, Raster raster) {
        this.id = id;
        this.timeseriesUnit = timeseriesUnit;
        this.raster = raster;
    }

    public TimeseriesHeadEntity() {
    }

    public TimeseriesHeadKey getId() {
        return id;
    }

    public void setId(TimeseriesHeadKey id) {
        this.id = id;
    }

    public TimeseriesUnit getTimeseriesUnit() {
        return timeseriesUnit;
    }

    public void setTimeseriesUnit(TimeseriesUnit timeseriesUnit) {
        this.timeseriesUnit = timeseriesUnit;
    }

    public Raster getRaster() {
        return raster;
    }

    public void setRaster(Raster raster) {
        this.raster = raster;
    }


}
