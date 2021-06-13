package de.hanke.arnim.TSServer.serivce;

import de.hanke.arnim.TSTool.PeriodicTimeseriesHead;
import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import de.hanke.arnim.TSPersistence.TimeseriesHeadKey;
import de.hanke.arnim.TSServer.model.*;

import java.util.List;

public interface TimeseriesHeaderServiceInterface {
    static PeriodicTimeseriesHead parseTimeSeriesHeadToPeriodicTimeSeriesHead(TimeSeriesHead timeseriesHead) {
        PeriodicTimeseriesHead periodicTimeSeriesHead = new PeriodicTimeseriesHead();
        periodicTimeSeriesHead.setTsId(timeseriesHead.getTsId());
        periodicTimeSeriesHead.setDatabaseName(timeseriesHead.getDatabase());
        periodicTimeSeriesHead.setRaster(de.hanke.arnim.TSTool.Raster.fromValue(timeseriesHead.getTsRaster().toString()));
        periodicTimeSeriesHead.setTimeseriesUnit(de.hanke.arnim.TSTool.TimeseriesUnit.valueOf(timeseriesHead.getTsUnit().toString()));
        return periodicTimeSeriesHead;
    }

    static TimeSeriesHead parseElasticTimeseriesHeadToTimeSeriesHead(TimeseriesHeadEntity timeSeriesHeadEntity) {
        TimeSeriesHead timeseriesHead = new TimeSeriesHead();
        timeseriesHead.setTsId(timeSeriesHeadEntity.getId().getTsId());
        timeseriesHead.setDatabase(timeSeriesHeadEntity.getId().getDatabaseName());
        timeseriesHead.setTsRaster(TimeSeriesRaster.fromValue(timeSeriesHeadEntity.getRaster().toString()));
        timeseriesHead.setTsUnit(TimeSeriesUnit.fromValue(timeSeriesHeadEntity.getTimeseriesUnit().toString()));
        return timeseriesHead;
    }

    static TimeseriesHeadEntity parseTimeSeriesHeadToElasticTimeseriesHead(PersistenceParameterHeadDto timeseriesHead) {
        TimeseriesHeadEntity timeSeriesHeadEntity = new TimeseriesHeadEntity();
        timeSeriesHeadEntity.setId(new TimeseriesHeadKey(timeseriesHead.getTsComposedKey().getTsId(), timeseriesHead.getTsComposedKey().getDatabaseName()));
        timeSeriesHeadEntity.setRaster(de.hanke.arnim.TSTool.Raster.fromValue(timeseriesHead.getTsraster().toString()));
        timeSeriesHeadEntity.setTimeseriesUnit(de.hanke.arnim.TSTool.TimeseriesUnit.valueOf(timeseriesHead.getTsUnit().name()));
        return timeSeriesHeadEntity;
    }

    TimeSeriesHead getTimeseriesDefinitionByAP(AccessParamterHeadDto accessParameter);

    List<TimeSeriesHead> getTimeseriesDefinitionByAPs(List<AccessParamterHeadDto> accessParameters);

    List<TimeSeriesHead> putTimeseriesDefinitionByAPs(List<PersistenceParameterHeadDto> persistenceParameterHeadDtos);

    TimeSeriesHead putTimeseriesDefinitionByAP(PersistenceParameterHeadDto timeseriesHead);

    boolean deleteTimeseriesDefinitionByDeleteParameterHeadDto(DeleteParameterHeadDto deleteParameterHeadDto);

    boolean deleteTimeseriesDefinitionByDeleteParameterHeadDtos(List<DeleteParameterHeadDto> deleteParametersHeadDto);
}
