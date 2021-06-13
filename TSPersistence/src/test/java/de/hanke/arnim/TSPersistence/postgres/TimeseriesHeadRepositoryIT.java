package de.hanke.arnim.TSPersistence.postgres;

import de.hanke.arnim.TSTool.Raster;
import de.hanke.arnim.TSTool.TimeseriesUnit;
import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import de.hanke.arnim.TSPersistence.TimeseriesHeadKey;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TimeseriesHeadRepositoryIT {

    @Autowired
    TimeseriesHeadRepository timeseriesHeadRepository;

    @Test
    public void timeseriesHead_CRUD() {
        TimeseriesHeadKey key = new TimeseriesHeadKey("TS_ID", "DATABASENAME");
        TimeseriesHeadEntity testEntity = createTimeseriesHeadEntry(key);
        assertEquals(0, timeseriesHeadRepository.findAll().size());

        assertNotNull(timeseriesHeadRepository.save(testEntity));
        assertEquals(1, timeseriesHeadRepository.findAll().size());

        assertTrue(timeseriesHeadRepository.findById(key).isPresent());
        assertEquals(key, timeseriesHeadRepository.findById(key).get().getId());

        assertEquals(1, timeseriesHeadRepository.findByIdTsIdLike("%").size());
        assertEquals(1, timeseriesHeadRepository.findByIdTsIdLike("TS%").size());
        assertEquals(1, timeseriesHeadRepository.findByIdTsIdLikeAndIdDatabaseName("TS%", "DATABASENAME").size());
        assertEquals(0, timeseriesHeadRepository.findByIdTsIdLikeAndIdDatabaseName("TS%", "blub").size());

        TimeseriesHeadEntity timeseriesHeadEntityFromDB = timeseriesHeadRepository.findById(key).get();
        timeseriesHeadEntityFromDB.setRaster(Raster.PT1H);

        timeseriesHeadRepository.save(timeseriesHeadEntityFromDB);

        assertEquals(Raster.PT1H, timeseriesHeadRepository.findById(key).get().getRaster());

        timeseriesHeadRepository.deleteById(key);
        assertFalse(timeseriesHeadRepository.findById(key).isPresent());
    }

    public TimeseriesHeadEntity createTimeseriesHeadEntry(TimeseriesHeadKey key) {
        TimeseriesHeadEntity timeseriesHeadEntity = new TimeseriesHeadEntity();
        timeseriesHeadEntity.setId(key);
        timeseriesHeadEntity.setRaster(Raster.PT1D);
        timeseriesHeadEntity.setTimeseriesUnit(TimeseriesUnit.GRADCELCIUS);

        return timeseriesHeadEntity;
    }

}