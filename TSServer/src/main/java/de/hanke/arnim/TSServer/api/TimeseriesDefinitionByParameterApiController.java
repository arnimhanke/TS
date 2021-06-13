package de.hanke.arnim.TSServer.api;

import de.hanke.arnim.TSServer.model.AccessParamterHeadDto;
import de.hanke.arnim.TSServer.model.DeleteParameterHeadDto;
import de.hanke.arnim.TSServer.model.PersistenceParameterHeadDto;
import de.hanke.arnim.TSServer.model.TimeSeriesHead;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hanke.arnim.TSServer.serivce.TimeseriesDefinitionByAPApiService;
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
public class TimeseriesDefinitionByParameterApiController implements TimeseriesDefinitionByParameterApi {

    @Autowired
    TimeseriesDefinitionByAPApiService timeseriesDefinitionByAPApiService;

    private static final Logger log = LoggerFactory.getLogger(TimeseriesDefinitionByParameterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TimeseriesDefinitionByParameterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteTimeseriesDefinitionByDeleteParameterHeadDto(@ApiParam(value = ""  )  @Valid @RequestBody DeleteParameterHeadDto deleteParameterHeadDto) {
        String accept = request.getHeader("Accept");

        boolean b = timeseriesDefinitionByAPApiService.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto);
        if(b) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TimeSeriesHead> getTimeseriesDefinitionByAccessParamterHeadDto(@ApiParam(value = ""  )  @Valid @RequestBody AccessParamterHeadDto accessParamterHeadDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<TimeSeriesHead>(timeseriesDefinitionByAPApiService.getTimeseriesDefinitionByAccessParamterHeadDto(accessParamterHeadDto), HttpStatus.OK);
        }

        return new ResponseEntity<TimeSeriesHead>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<TimeSeriesHead> putTimeseriesDefinitionByPersistenceParameterHeadDto(@ApiParam(value = ""  )  @Valid @RequestBody PersistenceParameterHeadDto persistenceParameterHeadDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesDefinitionByAPApiService.putTimeseriesDefinitionByPersistenceParameterHeadDto(persistenceParameterHeadDto), HttpStatus.OK);
        }

        return new ResponseEntity<TimeSeriesHead>(HttpStatus.NOT_IMPLEMENTED);
    }

}
