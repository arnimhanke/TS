package de.hanke.arnim.TSServer.api;

import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.DeleteParameterValuesDto;

import java.util.List;
import de.hanke.arnim.TSServer.model.PersistenceParameterValuesDto;
import de.hanke.arnim.TSServer.model.Timeseries;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hanke.arnim.TSServer.serivce.TimeseriesValuesByTSHeadApiService;
import de.hanke.arnim.TSServer.serivce.TimeseriesValuesByTSHeadsApiService;
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
public class TimeseriesValuesByParametersApiController implements TimeseriesValuesByParametersApi {

    private static final Logger log = LoggerFactory.getLogger(TimeseriesValuesByParametersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final TimeseriesValuesByTSHeadsApiService timeseriesValuesByTSHeadsApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public TimeseriesValuesByParametersApiController(ObjectMapper objectMapper, HttpServletRequest request, TimeseriesValuesByTSHeadsApiService timeseriesValuesByTSHeadsApiService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.timeseriesValuesByTSHeadsApiService = timeseriesValuesByTSHeadsApiService;
    }

    public ResponseEntity<Void> deleteTimeseriesValuesByDeleteParameterValuesDtos(@ApiParam(value = ""  )  @Valid @RequestBody List<DeleteParameterValuesDto> deleteParametersValuesDto,@ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval) {
        String accept = request.getHeader("Accept");

        boolean b = timeseriesValuesByTSHeadsApiService.deleteTimeseriesValuesByDeleteParametersValuesDto(deleteParametersValuesDto, interval);
        if(b) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        }

        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Timeseries>> getTimeseriesValuesByAccessParameterValuesDtos(@ApiParam(value = ""  )  @Valid @RequestBody List<AccessParameterValuesDto> accessParametersValuesDto,@ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesValuesByTSHeadsApiService.getTimeseriesValuesByAccessParameterValuesDtos(accessParametersValuesDto, interval), HttpStatus.OK);

        }

        return new ResponseEntity<List<Timeseries>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Timeseries>> putTimeseriesValuesByPersistenceParameterValuesDtos(@ApiParam(value = ""  )  @Valid @RequestBody List<PersistenceParameterValuesDto> persistenceParametersValuesDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesValuesByTSHeadsApiService.putTimeseriesValuesByPersistenceParameterValuesDtos(persistenceParametersValuesDto), HttpStatus.OK);
        }

        return new ResponseEntity<List<Timeseries>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
