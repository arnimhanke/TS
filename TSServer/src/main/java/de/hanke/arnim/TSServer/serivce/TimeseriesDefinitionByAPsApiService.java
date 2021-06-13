package de.hanke.arnim.TSServer.serivce;

import de.hanke.arnim.TSServer.model.AccessParamterHeadDto;
import de.hanke.arnim.TSServer.model.DeleteParameterHeadDto;
import de.hanke.arnim.TSServer.model.PersistenceParameterHeadDto;
import de.hanke.arnim.TSServer.model.TimeSeriesHead;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TimeseriesDefinitionByAPsApiService {

    @Autowired
    PostgreSQLService postgreSQLService;

    public List<TimeSeriesHead> getTimeseriesDefinitionByAccessParamterHeadDtos(List<AccessParamterHeadDto> accessParameters) {
        return postgreSQLService.getTimeseriesDefinitionByAPs(accessParameters);
    }

    public List<TimeSeriesHead> putTimeseriesDefinitionsByPersistenceParameterHeadDtos(List<PersistenceParameterHeadDto> timeseriesHeads) {
        return postgreSQLService.putTimeseriesDefinitionByAPs(timeseriesHeads);
    }

    public boolean deleteTimeseriesDefinitionByDeleteParameterHeadDtos(List<DeleteParameterHeadDto> deleteParametersHeadDto) {

        return postgreSQLService.deleteTimeseriesDefinitionByDeleteParameterHeadDtos(deleteParametersHeadDto);
    }
}
