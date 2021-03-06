/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.17).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package de.hanke.arnim.TSServer.api;

import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.PeriodicTimeseries;
import de.hanke.arnim.TSServer.model.PersistenceParameterValuesDto;
import de.hanke.arnim.TSServer.model.Timeseries;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-12-02T18:14:14.852Z")

@Api(value = "periodic", description = "the periodic API")
@RequestMapping(value = "/TSTool-api")
public interface PeriodicApi {

    @ApiOperation(value = "Get timeseries values for requested AccessParameterValuesDto", nickname = "getPeriodicTimeseriesValuesByAccessParameterValuesDto", notes = "", response = PeriodicTimeseries.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PeriodicTimeseries.class),
            @ApiResponse(code = 401, message = "unauthorized", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class),
            @ApiResponse(code = 200, message = "error", response = Error.class)})
    @RequestMapping(value = "/periodic/timeseriesValuesByParameter",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<PeriodicTimeseries> getPeriodicTimeseriesValuesByAccessParameterValuesDto(@ApiParam(value = "") @Valid @RequestBody AccessParameterValuesDto accessParameterValuesDto, @ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval);


    @ApiOperation(value = "Get timeseries values for requested AccessParameterValuesDtos", nickname = "getPeriodicTimeseriesValuesByAccessParameterValuesDtos", notes = "", response = PeriodicTimeseries.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = PeriodicTimeseries.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class),
            @ApiResponse(code = 200, message = "error", response = Error.class)})
    @RequestMapping(value = "/periodic/timeseriesValuesByParameters",
            produces = {"application/json"},
            method = RequestMethod.POST)
    ResponseEntity<List<PeriodicTimeseries>> getPeriodicTimeseriesValuesByAccessParameterValuesDtos(@ApiParam(value = "") @Valid @RequestBody List<AccessParameterValuesDto> accessParametersValuesDto, @ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval);


    @ApiOperation(value = "Adds values to given Timeseries. The Timeseries must exists!", nickname = "putPeriodicTimeseriesValuesByPersistenceParameterValuesDto", notes = "", response = Timeseries.class, tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Timeseries.class),
            @ApiResponse(code = 401, message = "unauthorized", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class),
            @ApiResponse(code = 200, message = "error", response = Error.class)})
    @RequestMapping(value = "/periodic/timeseriesValuesByParameter",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<Timeseries> putPeriodicTimeseriesValuesByPersistenceParameterValuesDto(@ApiParam(value = "") @Valid @RequestBody PersistenceParameterValuesDto persistenceParameterValuesDto);


    @ApiOperation(value = "Adds values to given Timeseries's. All Timeseries's must exists!", nickname = "putPeriodicTimeseriesValuesByPersistenceParameterValuesDtos", notes = "", response = Timeseries.class, responseContainer = "List", tags = {})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Timeseries.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "unauthorized", response = Error.class),
            @ApiResponse(code = 404, message = "resource not found", response = Error.class),
            @ApiResponse(code = 200, message = "error", response = Error.class)})
    @RequestMapping(value = "/periodic/timeseriesValuesByParameters",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    ResponseEntity<List<Timeseries>> putPeriodicTimeseriesValuesByPersistenceParameterValuesDtos(@ApiParam(value = "") @Valid @RequestBody List<PersistenceParameterValuesDto> persistenceParametersValuesDto);

}
