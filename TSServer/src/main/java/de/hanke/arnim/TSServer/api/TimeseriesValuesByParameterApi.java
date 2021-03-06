/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.14).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package de.hanke.arnim.TSServer.api;

import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.DeleteParameterValuesDto;
import de.hanke.arnim.TSServer.model.Error;
import de.hanke.arnim.TSServer.model.PersistenceParameterValuesDto;
import de.hanke.arnim.TSServer.model.Timeseries;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-06-01T11:50:12.361Z")

@Api(value = "timeseriesValuesByParameter", description = "the timeseriesValuesByParameter API")
@RequestMapping(value = "/TSTool-api")
public interface TimeseriesValuesByParameterApi {

    @ApiOperation(value = "Removes values of given Timeseries. The Timeseries must exists!", nickname = "deleteTimeseriesValuesByDeleteParameterValuesDto", notes = "", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK"),
        @ApiResponse(code = 401, message = "unauthorized", response = Error.class),
        @ApiResponse(code = 404, message = "resource not found", response = Error.class),
        @ApiResponse(code = 200, message = "error", response = Error.class) })
    @RequestMapping(value = "/timeseriesValuesByParameter",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteTimeseriesValuesByDeleteParameterValuesDto(@ApiParam(value = ""  )  @Valid @RequestBody DeleteParameterValuesDto deleteParameterValuesDto,@ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval);


    @ApiOperation(value = "Get timeseries values for requested AccessParameterValuesDto", nickname = "getTimeseriesValuesByAccessParameterValuesDto", notes = "", response = Timeseries.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Timeseries.class),
        @ApiResponse(code = 401, message = "unauthorized", response = Error.class),
        @ApiResponse(code = 404, message = "resource not found", response = Error.class),
        @ApiResponse(code = 200, message = "error", response = Error.class) })
    @RequestMapping(value = "/timeseriesValuesByParameter",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Timeseries> getTimeseriesValuesByAccessParameterValuesDto(@ApiParam(value = ""  )  @Valid @RequestBody AccessParameterValuesDto accessParameterValuesDto,@ApiParam(value = "") @Valid @RequestParam(value = "interval", required = false) String interval);


    @ApiOperation(value = "Adds values to given Timeseries. The Timeseries must exists!", nickname = "putTimeseriesValuesByPersistenceParameterValuesDto", notes = "", response = Timeseries.class, tags={  })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "OK", response = Timeseries.class),
        @ApiResponse(code = 401, message = "unauthorized", response = Error.class),
        @ApiResponse(code = 404, message = "resource not found", response = Error.class),
        @ApiResponse(code = 200, message = "error", response = Error.class) })
    @RequestMapping(value = "/timeseriesValuesByParameter",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Timeseries> putTimeseriesValuesByPersistenceParameterValuesDto(@ApiParam(value = ""  )  @Valid @RequestBody PersistenceParameterValuesDto persistenceParameterValuesDto);

}
