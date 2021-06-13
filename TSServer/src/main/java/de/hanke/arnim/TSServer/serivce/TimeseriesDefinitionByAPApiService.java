package de.hanke.arnim.TSServer.serivce;

import de.hanke.arnim.TSServer.model.AccessParamterHeadDto;
import de.hanke.arnim.TSServer.model.DeleteParameterHeadDto;
import de.hanke.arnim.TSServer.model.PersistenceParameterHeadDto;
import de.hanke.arnim.TSServer.model.TimeSeriesHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TimeseriesDefinitionByAPApiService {

    @Autowired
    PostgreSQLService postgreSQLService;


    public TimeSeriesHead getTimeseriesDefinitionByAccessParamterHeadDto(AccessParamterHeadDto accessParameter) {
        return postgreSQLService.getTimeseriesDefinitionByAP(accessParameter);
    }

    public TimeSeriesHead putTimeseriesDefinitionByPersistenceParameterHeadDto(PersistenceParameterHeadDto timeseriesHead) {
        return postgreSQLService.putTimeseriesDefinitionByAP(timeseriesHead);
    }

    public boolean deleteTimeseriesDefinitionByDeleteParameterHeadDto(DeleteParameterHeadDto deleteParameterHeadDto) {

        return postgreSQLService.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto);

    }
}
