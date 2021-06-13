package de.hanke.arnim.TSServer.api;

import de.hanke.arnim.TSServer.model.AccessParamterHeadDto;
import de.hanke.arnim.TSServer.model.DeleteParameterHeadDto;

import java.util.List;
import de.hanke.arnim.TSServer.model.PersistenceParameterHeadDto;
import de.hanke.arnim.TSServer.model.TimeSeriesHead;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hanke.arnim.TSServer.serivce.TimeseriesDefinitionByAPsApiService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

@Controller
public class TimeseriesDefinitionByParametersApiController implements TimeseriesDefinitionByParametersApi {

    private static final Logger log = LoggerFactory.getLogger(TimeseriesDefinitionByParametersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    TimeseriesDefinitionByAPsApiService timeseriesDefinitionByAPsApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public TimeseriesDefinitionByParametersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteTimeseriesDefinitionByDeleteParameterHeadDtos(@ApiParam(value = ""  )  @Valid @RequestBody List<DeleteParameterHeadDto> deleteParametersHeadDto) {
        String accept = request.getHeader("Accept");

        timeseriesDefinitionByAPsApiService.deleteTimeseriesDefinitionByDeleteParameterHeadDtos(deleteParametersHeadDto);

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<TimeSeriesHead>> getTimeseriesDefinitionByAccessParamterHeadDtos(@ApiParam(value = ""  )  @Valid @RequestBody List<AccessParamterHeadDto> accessParamtersHeadDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<TimeSeriesHead>>(timeseriesDefinitionByAPsApiService.getTimeseriesDefinitionByAccessParamterHeadDtos(accessParamtersHeadDto), HttpStatus.OK);
        }

        return new ResponseEntity<List<TimeSeriesHead>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<TimeSeriesHead>> putTimeseriesDefinitionsByPersistenceParameterHeadDtos(@ApiParam(value = ""  )  @Valid @RequestBody List<PersistenceParameterHeadDto> persistenceParametersHeadDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<List<TimeSeriesHead>>(timeseriesDefinitionByAPsApiService.putTimeseriesDefinitionsByPersistenceParameterHeadDtos(persistenceParametersHeadDto), HttpStatus.OK);
        }

        return new ResponseEntity<List<TimeSeriesHead>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
