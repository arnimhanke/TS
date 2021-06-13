package de.hanke.arnim.TSServer.api;

import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.DeleteParameterValuesDto;
import de.hanke.arnim.TSServer.model.PersistenceParameterValuesDto;
import de.hanke.arnim.TSServer.model.Timeseries;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hanke.arnim.TSServer.serivce.TimeseriesValuesByTSHeadApiService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

@Controller
public class TimeseriesValuesByParameterApiController implements TimeseriesValuesByParameterApi {

    @Autowired
    TimeseriesValuesByTSHeadApiService timeseriesValuesByTSHeadApiService;

    private static final Logger log = LoggerFactory.getLogger(TimeseriesValuesByParameterApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public TimeseriesValuesByParameterApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> deleteTimeseriesValuesByDeleteParameterValuesDto(@ApiParam(value = ""  )  @Valid @RequestBody DeleteParameterValuesDto deleteParameterValuesDto,@ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval) {
        String accept = request.getHeader("Accept");

        boolean b = timeseriesValuesByTSHeadApiService.deleteTimeseriesValuesByDeleteParameterValuesDto(deleteParameterValuesDto, interval);
        if(b) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Timeseries> getTimeseriesValuesByAccessParameterValuesDto(@ApiParam(value = ""  )  @Valid @RequestBody AccessParameterValuesDto accessParameterValuesDto,@ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesValuesByTSHeadApiService.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValuesDto, interval), HttpStatus.OK);
        }

        return new ResponseEntity<Timeseries>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Timeseries> putTimeseriesValuesByPersistenceParameterValuesDto(@ApiParam(value = ""  )  @Valid @RequestBody PersistenceParameterValuesDto persistenceParameterValuesDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesValuesByTSHeadApiService.putTimeseriesValuesByPersistenceParameterValuesDto(persistenceParameterValuesDto), HttpStatus.OK);
        }

        return new ResponseEntity<Timeseries>(HttpStatus.NOT_IMPLEMENTED);
    }

}
