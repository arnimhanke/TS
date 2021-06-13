package de.hanke.arnim.TSPersistence.postgres;

import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import de.hanke.arnim.TSPersistence.TimeseriesHeadKey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeseriesHeadRepository extends CrudRepository<TimeseriesHeadEntity, TimeseriesHeadKey> {

    @Override
    List<TimeseriesHeadEntity> findAll();

    List<TimeseriesHeadEntity> findByIdTsIdLike(String tsId);
    List<TimeseriesHeadEntity> findByIdTsIdLikeAndIdDatabaseName(String tsId, String databaseName);
}
