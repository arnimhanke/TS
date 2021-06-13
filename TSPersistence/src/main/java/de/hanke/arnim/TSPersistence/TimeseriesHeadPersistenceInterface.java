package de.hanke.arnim.TSPersistence;

import java.util.List;

public interface TimeseriesHeadPersistenceInterface {
    List<TimeseriesHeadEntity> loadTimeseriesHeadsFromDatabase(String searchPatternTsID, String database);

    TimeseriesHeadEntity putPeriodicTimeseriesHeadIntoDatabase(TimeseriesHeadEntity value);

    List<TimeseriesHeadEntity> putPeriodicTimeseriesHeadsIntoDatabase(List<TimeseriesHeadEntity> value);

    boolean deleteTimeseriesDefinitionFromDatabase(String tsId, String databaseName);
}
