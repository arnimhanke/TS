package de.hanke.arnim.TSServer.serivce;

import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import de.hanke.arnim.TSPersistence.elastic.ElasticSearchUtils;
import de.hanke.arnim.TSPersistence.postgres.PostgreSQLUtils;
import de.hanke.arnim.TSServer.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static de.hanke.arnim.TSServer.serivce.TimeseriesHeaderServiceInterface.parseElasticTimeseriesHeadToTimeSeriesHead;
import static de.hanke.arnim.TSServer.serivce.TimeseriesHeaderServiceInterface.parseTimeSeriesHeadToElasticTimeseriesHead;

@Component
public class PostgreSQLService implements TimeseriesHeaderServiceInterface {

    @Autowired
    PostgreSQLUtils postgreSQLUtils;


    @Override
    public TimeSeriesHead getTimeseriesDefinitionByAP(AccessParamterHeadDto accessParameter) {
        TimeSeriesComposedKey tsComposedKey = accessParameter.getTsComposedKey();
        List<TimeseriesHeadEntity> timeSeriesHeadEntities = postgreSQLUtils.loadTimeseriesHeadsFromDatabase(tsComposedKey.getTsId(), tsComposedKey.getDatabaseName());

        List<TimeseriesHeadEntity> filteredForDatabaseName = timeSeriesHeadEntities.stream().filter(periodicTimeSeriesHead -> periodicTimeSeriesHead.getId().getDatabaseName().equals(tsComposedKey.getDatabaseName())).collect(Collectors.toList());
        if (filteredForDatabaseName.size() == 1) {
            TimeSeriesHead timeseriesHead = new TimeSeriesHead();

            timeseriesHead.setTsId(filteredForDatabaseName.get(0).getId().getTsId());
            timeseriesHead.setDatabase(filteredForDatabaseName.get(0).getId().getDatabaseName());
            timeseriesHead.setTsRaster(TimeSeriesRaster.fromValue(filteredForDatabaseName.get(0).getRaster().toString()));
            timeseriesHead.setTsUnit(TimeSeriesUnit.fromValue(filteredForDatabaseName.get(0).getTimeseriesUnit().toString()));
            return timeseriesHead;

        } else {
            return null;
        }
    }

    @Override
    public List<TimeSeriesHead> getTimeseriesDefinitionByAPs(List<AccessParamterHeadDto> accessParameters) {
        ArrayList<TimeSeriesHead> timeseriesHeads = new ArrayList<>();

        for (AccessParamterHeadDto accessParameter : accessParameters) {
            TimeSeriesHead timeseriesDefinitionByAP = getTimeseriesDefinitionByAP(accessParameter);
            timeseriesHeads.add(timeseriesDefinitionByAP);
        }

        return timeseriesHeads;
    }

    @Override
    public List<TimeSeriesHead> putTimeseriesDefinitionByAPs(List<PersistenceParameterHeadDto> persistenceParameterHeadDtos) {
        ArrayList<TimeseriesHeadEntity> periodicTimeSeriesHeads = new ArrayList<>();

        for (PersistenceParameterHeadDto timeseriesHead : persistenceParameterHeadDtos) {
            periodicTimeSeriesHeads.add(parseTimeSeriesHeadToElasticTimeseriesHead(timeseriesHead));
        }
        List<TimeseriesHeadEntity> timeseriesHeadEntities = postgreSQLUtils.putPeriodicTimeseriesHeadsIntoDatabase(periodicTimeSeriesHeads);

        if (timeseriesHeadEntities != null) {
            ArrayList<TimeSeriesHead> timeseriesHeads = new ArrayList<>();
            for (TimeseriesHeadEntity periodicTimeSeriesHead : timeseriesHeadEntities) {
                timeseriesHeads.add(parseElasticTimeseriesHeadToTimeSeriesHead(periodicTimeSeriesHead));
            }
            return timeseriesHeads;
        } else {
            return null;
        }
    }

    @Override
    public TimeSeriesHead putTimeseriesDefinitionByAP(PersistenceParameterHeadDto timeseriesHead) {
        TimeseriesHeadEntity timeSeriesHeadEntity = parseTimeSeriesHeadToElasticTimeseriesHead(timeseriesHead);
        TimeseriesHeadEntity timeseriesHeadEntity = postgreSQLUtils.putPeriodicTimeseriesHeadIntoDatabase(timeSeriesHeadEntity);

        if (timeseriesHeadEntity != null) {
            TimeSeriesComposedKey tsComposedKey = timeseriesHead.getTsComposedKey();
            return parseElasticTimeseriesHeadToTimeSeriesHead(timeSeriesHeadEntity);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteTimeseriesDefinitionByDeleteParameterHeadDto(DeleteParameterHeadDto deleteParameterHeadDto) {
        return postgreSQLUtils.deleteTimeseriesDefinitionFromDatabase(deleteParameterHeadDto.getTsId(), deleteParameterHeadDto.getDatabaseName());
    }

    @Override
    public boolean deleteTimeseriesDefinitionByDeleteParameterHeadDtos(List<DeleteParameterHeadDto> deleteParametersHeadDto) {
        boolean erfolgreich = true;
        for (DeleteParameterHeadDto deleteParameterHeadDto : deleteParametersHeadDto) {
            boolean b = this.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto);
            if (!b) {
                erfolgreich = false;
            }
        }
        return erfolgreich;
    }
}
