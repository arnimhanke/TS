package de.hanke.arnim.TSServer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.PeriodicTimeseries;
import de.hanke.arnim.TSServer.model.PersistenceParameterValuesDto;
import de.hanke.arnim.TSServer.model.Timeseries;
import de.hanke.arnim.TSServer.serivce.periodic.TimeseriesPeriodicValuesByTSHeadApiService;
import de.hanke.arnim.TSServer.serivce.periodic.TimeseriesPeriodicValuesByTSHeadsApiService;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-02T18:14:14.852Z")

@Controller
public class PeriodicApiController implements PeriodicApi {

    private static final Logger log = LoggerFactory.getLogger(PeriodicApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final TimeseriesPeriodicValuesByTSHeadsApiService timeseriesPeriodicValuesByTSHeadsApiService;
    private final TimeseriesPeriodicValuesByTSHeadApiService timeseriesPeriodicValuesByTSHeadApiService;

    @org.springframework.beans.factory.annotation.Autowired
    public PeriodicApiController(ObjectMapper objectMapper, HttpServletRequest request, TimeseriesPeriodicValuesByTSHeadsApiService timeseriesPeriodicValuesByTSHeadsApiService, TimeseriesPeriodicValuesByTSHeadApiService timeseriesPeriodicValuesByTSHeadApiService) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.timeseriesPeriodicValuesByTSHeadsApiService = timeseriesPeriodicValuesByTSHeadsApiService;
        this.timeseriesPeriodicValuesByTSHeadApiService = timeseriesPeriodicValuesByTSHeadApiService;
    }

    public ResponseEntity<PeriodicTimeseries> getPeriodicTimeseriesValuesByAccessParameterValuesDto(@ApiParam(value = "") @Valid @RequestBody AccessParameterValuesDto accessParameterValuesDto, @ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesPeriodicValuesByTSHeadApiService.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValuesDto, interval), HttpStatus.OK);
        }

        return new ResponseEntity<PeriodicTimeseries>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<PeriodicTimeseries>> getPeriodicTimeseriesValuesByAccessParameterValuesDtos(@ApiParam(value = "") @Valid @RequestBody List<AccessParameterValuesDto> accessParametersValuesDto, @ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            return new ResponseEntity<>(timeseriesPeriodicValuesByTSHeadsApiService.getTimeseriesValuesByAccessParameterValuesDtos(accessParametersValuesDto, interval), HttpStatus.OK);
        }

        return new ResponseEntity<List<PeriodicTimeseries>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Timeseries> putPeriodicTimeseriesValuesByPersistenceParameterValuesDto(@ApiParam(value = "") @Valid @RequestBody PersistenceParameterValuesDto persistenceParameterValuesDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Timeseries>(objectMapper.readValue("{  \"timeSeriesValues\" : [ {    \"time\" : \"2000-01-23\",    \"value\" : 0.80082819046101150206595775671303272247314453125  }, {    \"time\" : \"2000-01-23\",    \"value\" : 0.80082819046101150206595775671303272247314453125  } ],  \"timeSeriesHead\" : {    \"database\" : \"database\",    \"tsRaster\" : { },    \"tsId\" : \"tsId\",    \"tsUnit\" : { }  }}", Timeseries.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Timeseries>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Timeseries>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Timeseries>> putPeriodicTimeseriesValuesByPersistenceParameterValuesDtos(@ApiParam(value = "") @Valid @RequestBody List<PersistenceParameterValuesDto> persistenceParametersValuesDto) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {



                return new ResponseEntity<List<Timeseries>>(objectMapper.readValue("[ {  \"timeSeriesValues\" : [ {    \"time\" : \"2000-01-23\",    \"value\" : 0.80082819046101150206595775671303272247314453125  }, {    \"time\" : \"2000-01-23\",    \"value\" : 0.80082819046101150206595775671303272247314453125  } ],  \"timeSeriesHead\" : {    \"database\" : \"database\",    \"tsRaster\" : { },    \"tsId\" : \"tsId\",    \"tsUnit\" : { }  }}, {  \"timeSeriesValues\" : [ {    \"time\" : \"2000-01-23\",    \"value\" : 0.80082819046101150206595775671303272247314453125  }, {    \"time\" : \"2000-01-23\",    \"value\" : 0.80082819046101150206595775671303272247314453125  } ],  \"timeSeriesHead\" : {    \"database\" : \"database\",    \"tsRaster\" : { },    \"tsId\" : \"tsId\",    \"tsUnit\" : { }  }} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Timeseries>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Timeseries>>(HttpStatus.NOT_IMPLEMENTED);
    }

}
