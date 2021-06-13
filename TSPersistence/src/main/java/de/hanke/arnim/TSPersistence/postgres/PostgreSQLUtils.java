package de.hanke.arnim.TSPersistence.postgres;

import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import de.hanke.arnim.TSPersistence.TimeseriesHeadKey;
import de.hanke.arnim.TSPersistence.TimeseriesHeadPersistenceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PostgreSQLUtils implements TimeseriesHeadPersistenceInterface {

    @Autowired
    private TimeseriesHeadRepository repository;

    @Override
    public List<TimeseriesHeadEntity> loadTimeseriesHeadsFromDatabase(String searchPatternTsID, String database) {
        return repository.findByIdTsIdLikeAndIdDatabaseName(searchPatternTsID, database);
    }

    @Override
    public TimeseriesHeadEntity putPeriodicTimeseriesHeadIntoDatabase(TimeseriesHeadEntity value) {
        return repository.save(value);
    }

    @Override
    public List<TimeseriesHeadEntity> putPeriodicTimeseriesHeadsIntoDatabase(List<TimeseriesHeadEntity> value) {
        ArrayList<TimeseriesHeadEntity> timeseriesHeadEntities = new ArrayList<>();
        repository.saveAll(value).forEach(timeseriesHeadEntities::add);
        return timeseriesHeadEntities;
    }

    @Override
    public boolean deleteTimeseriesDefinitionFromDatabase(String tsId, String databaseName) {
        TimeseriesHeadKey headKey = new TimeseriesHeadKey(tsId, databaseName);
        try {
            repository.deleteById(headKey);
        } catch(EmptyResultDataAccessException emptyResultDataAccessException) {
            // exception indicates that the entity is not in database, so everything is fine (we hope)
        }
        // check if timeseries is still in database or not
        return !repository.findById(headKey).isPresent();
    }
}
