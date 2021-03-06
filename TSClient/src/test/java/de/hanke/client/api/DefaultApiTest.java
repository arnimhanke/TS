/*
 * TSTool-service
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 *
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package de.hanke.client.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Response;
import de.hanke.client.ApiException;
import de.hanke.client.model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * API tests for DefaultApi
 */
@Ignore
public class DefaultApiTest {

    static TimeSeriesValue timeSeriesValue_1 = new TimeSeriesValue().time(Instant.parse("2020-02-01T00:00:00.00Z")).value(BigDecimal.TEN);
    static TimeSeriesValue timeSeriesValue_2 = new TimeSeriesValue().time(Instant.parse("2020-02-01T00:15:00.00Z")).value(BigDecimal.TEN);
    static TimeSeriesValue timeSeriesValue_3 = new TimeSeriesValue().time(Instant.parse("2020-02-01T00:30:00.00Z")).value(BigDecimal.TEN);
    static TimeSeriesValue timeSeriesValue_4 = new TimeSeriesValue().time(Instant.parse("2020-02-01T00:45:00.00Z")).value(BigDecimal.TEN);
    static ArrayList<TimeSeriesValue> tsvalues = new ArrayList<>();

    static {
        tsvalues.add(timeSeriesValue_1);
        tsvalues.add(timeSeriesValue_2);
        tsvalues.add(timeSeriesValue_3);
        tsvalues.add(timeSeriesValue_4);
    }

    private final DefaultApi api = new DefaultApi();
    String interval = "2020-02-01T00:00:00.00Z/2020-02-03T00:00:00.00Z";
    TimeSeriesComposedKey tsComposedKey_1 = new TimeSeriesComposedKey().databaseName("MyTestDB").tsId("test_1");
    TimeSeriesComposedKey tsComposedKey_2 = new TimeSeriesComposedKey().databaseName("MyTestDB").tsId("test_2");
    TimeSeriesComposedKey tsComposedKey_3 = new TimeSeriesComposedKey().databaseName("MyTestDB").tsId("test_3");
    TimeSeriesComposedKey tsComposedKey_4 = new TimeSeriesComposedKey().databaseName("MyTestDB").tsId("test_4");
    AccessParameterValuesDto accessParameterValues_1 = new AccessParameterValuesDto().tsComposedKey(tsComposedKey_1).tsraster(TimeSeriesRaster.PT15M);
    AccessParameterValuesDto accessParameterValues_2 = new AccessParameterValuesDto().tsComposedKey(tsComposedKey_2).tsraster(TimeSeriesRaster.PT15M);
    AccessParameterValuesDto accessParameterValues_3 = new AccessParameterValuesDto().tsComposedKey(tsComposedKey_3).tsraster(TimeSeriesRaster.PT15M);
    AccessParameterValuesDto accessParameterValues_4 = new AccessParameterValuesDto().tsComposedKey(tsComposedKey_4).tsraster(TimeSeriesRaster.PT15M);
    AccessParamterHeadDto accessParamterHeadDto_1 = new AccessParamterHeadDto().tsComposedKey(tsComposedKey_1);
    AccessParamterHeadDto accessParamterHeadDto_2 = new AccessParamterHeadDto().tsComposedKey(tsComposedKey_2);
    AccessParamterHeadDto accessParamterHeadDto_3 = new AccessParamterHeadDto().tsComposedKey(tsComposedKey_3);
    AccessParamterHeadDto accessParamterHeadDto_4 = new AccessParamterHeadDto().tsComposedKey(tsComposedKey_4);
    PersistenceParameterHeadDto persistenceParameterHeadDto_1 = new PersistenceParameterHeadDto().tsComposedKey(tsComposedKey_1).tsUnit(TimeSeriesUnit.MW).tsraster(TimeSeriesRaster.PT15M);
    PersistenceParameterHeadDto persistenceParameterHeadDto_2 = new PersistenceParameterHeadDto().tsComposedKey(tsComposedKey_2).tsUnit(TimeSeriesUnit.MW).tsraster(TimeSeriesRaster.PT15M);
    PersistenceParameterHeadDto persistenceParameterHeadDto_3 = new PersistenceParameterHeadDto().tsComposedKey(tsComposedKey_3).tsUnit(TimeSeriesUnit.MW).tsraster(TimeSeriesRaster.PT15M);
    PersistenceParameterHeadDto persistenceParameterHeadDto_4 = new PersistenceParameterHeadDto().tsComposedKey(tsComposedKey_4).tsUnit(TimeSeriesUnit.MW).tsraster(TimeSeriesRaster.PT15M);
    PersistenceParameterValuesDto persistenceParameterValuesDto_1 = new PersistenceParameterValuesDto().tsComposedKey(tsComposedKey_1).tsvalues(tsvalues);
    PersistenceParameterValuesDto persistenceParameterValuesDto_2 = new PersistenceParameterValuesDto().tsComposedKey(tsComposedKey_2).tsvalues(tsvalues);
    PersistenceParameterValuesDto persistenceParameterValuesDto_3 = new PersistenceParameterValuesDto().tsComposedKey(tsComposedKey_3).tsvalues(tsvalues);
    PersistenceParameterValuesDto persistenceParameterValuesDto_4 = new PersistenceParameterValuesDto().tsComposedKey(tsComposedKey_4).tsvalues(tsvalues);


    DeleteParameterHeadDto deleteParameterHeadDto_1 = new DeleteParameterHeadDto().databaseName(this.tsComposedKey_1.getDatabaseName()).tsId(this.tsComposedKey_1.getTsId());
    DeleteParameterHeadDto deleteParameterHeadDto_2 = new DeleteParameterHeadDto().databaseName(this.tsComposedKey_2.getDatabaseName()).tsId(this.tsComposedKey_2.getTsId());
    DeleteParameterHeadDto deleteParameterHeadDto_3 = new DeleteParameterHeadDto().databaseName(this.tsComposedKey_3.getDatabaseName()).tsId(this.tsComposedKey_3.getTsId());
    DeleteParameterHeadDto deleteParameterHeadDto_4 = new DeleteParameterHeadDto().databaseName(this.tsComposedKey_4.getDatabaseName()).tsId(this.tsComposedKey_4.getTsId());

    DeleteParameterValuesDto deleteParameterValuesDto_1 = new DeleteParameterValuesDto().databaseName(tsComposedKey_1.getDatabaseName()).tsId(tsComposedKey_1.getTsId());
    DeleteParameterValuesDto deleteParameterValuesDto_2 = new DeleteParameterValuesDto().databaseName(tsComposedKey_2.getDatabaseName()).tsId(tsComposedKey_2.getTsId());
    DeleteParameterValuesDto deleteParameterValuesDto_3 = new DeleteParameterValuesDto().databaseName(tsComposedKey_3.getDatabaseName()).tsId(tsComposedKey_3.getTsId());
    DeleteParameterValuesDto deleteParameterValuesDto_4 = new DeleteParameterValuesDto().databaseName(tsComposedKey_4.getDatabaseName()).tsId(tsComposedKey_4.getTsId());

    @Before
    public void tearUp() {
        try {

//            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() {
        try {
            ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_1, deleteParameterHeadDto_1);
            ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_2, deleteParameterHeadDto_2);
            ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_3, deleteParameterHeadDto_3);
            ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_4, deleteParameterHeadDto_4);


            ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_1, deleteParameterValuesDto_1, interval);
            ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_2, deleteParameterValuesDto_2, interval);
            ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_3, deleteParameterValuesDto_3, interval);
            ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_4, deleteParameterValuesDto_4, interval);
//            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_getAccessToken() throws ApiException, IOException {
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("grant_type", "password");
        formParams.put("username", "test");
        formParams.put("password", "Test");
        formParams.put("client_id", "login-app");
        formParams.put("", "");
        HashMap<String, String> headerParams = new HashMap<>();
        headerParams.put("Content-Type", "application/x-www-form-urlencoded");
        Call post = api.getApiClient()
                .buildCall("http://localhost:8070/auth/realms/main/protocol/openid-connect/token", "POST", new ArrayList<>(), new ArrayList<>(), null, headerParams, formParams, new String[]{}, null, false);
        Response execute = post
                .execute();
        ObjectMapper objectMapper = new ObjectMapper();
        String string = execute.body().string();
        System.out.println(execute.code());

        KeycloakLoginDto keycloakLoginDto = objectMapper.readValue(string, KeycloakLoginDto.class);

        System.out.println(keycloakLoginDto.getAccess_token());
    }

    /**
     * Get timeseries-Definition for requested AccessParamterHeadDto
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTimeseriesDefinitionByAccessParamterHeadDtoTest() throws ApiException, InterruptedException {
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);

//        Thread.sleep(1500);

        TimeSeriesHead response = api.getTimeseriesDefinitionByAccessParamterHeadDto(accessParamterHeadDto_1);

        // TODO: test validations
        assertEquals("MyTestDB", response.getDatabase());
    }

    /**
     * Get timeseries-Definitions for requested AccessParamterHeadDtos
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTimeseriesDefinitionByAccessParamterHeadDtosTest() throws ApiException, InterruptedException {
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_2, persistenceParameterHeadDto_2);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_3, persistenceParameterHeadDto_3);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_4, persistenceParameterHeadDto_4);
        List<AccessParamterHeadDto> accessParameters = new ArrayList<>();
        accessParameters.add(accessParamterHeadDto_1);
        accessParameters.add(accessParamterHeadDto_2);
        accessParameters.add(accessParamterHeadDto_3);
        accessParameters.add(accessParamterHeadDto_4);

//        Thread.sleep(1500);

        List<TimeSeriesHead> response = api.getTimeseriesDefinitionByAccessParamterHeadDtos(accessParameters);

        // TODO: test validations
        for (TimeSeriesHead timeSeriesHead : response) {
            assertEquals("MyTestDB", timeSeriesHead.getDatabase());
        }
    }

    /**
     * Get timeseries values for requested AccessParameterValuesDto
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTimeseriesValuesByAccessParameterValuesDtoTest() throws ApiException, InterruptedException {

        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);

//        Thread.sleep(1500);

        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_1, persistenceParameterValuesDto_1, interval);

        Timeseries response = api.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValues_1, interval);

        // TODO: test validations
        assertNotNull(response);
        assertTrue(response.getTimeSeriesValues().size() > 0);
    }

    /**
     * Get timeseries values for requested AccessParameterValuesDtos
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getTimeseriesValuesByAccessParameterValuesDtosTest() throws ApiException, InterruptedException {
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_2, persistenceParameterHeadDto_2);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_3, persistenceParameterHeadDto_3);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_4, persistenceParameterHeadDto_4);

//        Thread.sleep(2000);

        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_1, persistenceParameterValuesDto_1, interval);
        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_2, persistenceParameterValuesDto_2, interval);
        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_3, persistenceParameterValuesDto_3, interval);
        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_4, persistenceParameterValuesDto_4, interval);
        List<AccessParameterValuesDto> tsHeads = new ArrayList<>();
        tsHeads.add(accessParameterValues_1);
        tsHeads.add(accessParameterValues_2);
        tsHeads.add(accessParameterValues_3);
        tsHeads.add(accessParameterValues_4);
        List<Timeseries> response = api.getTimeseriesValuesByAccessParameterValuesDtos(tsHeads, interval);

        // TODO: test validations
        assertNotNull(response);
        assertEquals(4, response.size());
    }

    /**
     * Create a new TimeseriesDefinition
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void putTimeseriesDefinitionByPersistenceParameterHeadDtoTest() throws ApiException, InterruptedException {

        ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_1, deleteParameterHeadDto_1);

//        Thread.sleep(1500);

        TimeSeriesHead response = api.putTimeseriesDefinitionByPersistenceParameterHeadDto(persistenceParameterHeadDto_1);

        // TODO: test validations
        assertNotNull(response);
        assertEquals(response.getDatabase(), tsComposedKey_1.getDatabaseName());
        assertEquals(response.getTsId(), tsComposedKey_1.getTsId());
    }

    /**
     * Create new TimeseriesDefinitions
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void putTimeseriesDefinitionsByPersistenceParameterHeadDtosTest() throws ApiException, InterruptedException {

        ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_1, deleteParameterHeadDto_1);
        ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_2, deleteParameterHeadDto_2);
        ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_3, deleteParameterHeadDto_3);
        ifGivenTSIsInDBThanDeleteIt(accessParamterHeadDto_4, deleteParameterHeadDto_4);

//        Thread.sleep(1500);


        List<PersistenceParameterHeadDto> tsHeads = new ArrayList<>();

        tsHeads.add(persistenceParameterHeadDto_1);
        tsHeads.add(persistenceParameterHeadDto_2);
        tsHeads.add(persistenceParameterHeadDto_3);
        tsHeads.add(persistenceParameterHeadDto_4);
        List<TimeSeriesHead> response = api.putTimeseriesDefinitionsByPersistenceParameterHeadDtos(tsHeads);

        // TODO: test validations
        assertNotNull(response);
        assertEquals(4, response.size());
    }

    /**
     * Adds values to given Timeseries. The Timeseries must exists!
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void putTimeseriesValuesByPersistenceParameterValuesDtoTest() throws ApiException, InterruptedException {
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);

//        Thread.sleep(1500);

        ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_1, deleteParameterValuesDto_1, interval);
        ArrayList<TimeSeriesValue> tsvalues = new ArrayList<>();
        tsvalues.add(timeSeriesValue_1);
        tsvalues.add(timeSeriesValue_2);
        tsvalues.add(timeSeriesValue_3);
        tsvalues.add(timeSeriesValue_4);
        PersistenceParameterValuesDto timeseries = new PersistenceParameterValuesDto().tsComposedKey(tsComposedKey_4).tsvalues(tsvalues);
        Timeseries response = api.putTimeseriesValuesByPersistenceParameterValuesDto(timeseries);

        // TODO: test validations
//        assertNotNull(response);
//        assertEquals(4, response.getTimeSeriesValues().size());
    }

    /**
     * Adds values to given Timeseries&#39;s. All Timeseries&#39;s must exists!
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void putTimeseriesValuesByPersistenceParameterValuesDtosTest() throws ApiException, InterruptedException {

        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_2, persistenceParameterHeadDto_2);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_3, persistenceParameterHeadDto_3);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_4, persistenceParameterHeadDto_4);

//        Thread.sleep(1500);

        ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_1, deleteParameterValuesDto_1, interval);
        ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_2, deleteParameterValuesDto_2, interval);
        ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_3, deleteParameterValuesDto_3, interval);
        ifGivenValuesAreInDBThanDeleteThem(accessParameterValues_4, deleteParameterValuesDto_4, interval);


        ArrayList<PersistenceParameterValuesDto> persistenceParameterValuesDtos = new ArrayList<>();
        persistenceParameterValuesDtos.add(persistenceParameterValuesDto_1);
        persistenceParameterValuesDtos.add(persistenceParameterValuesDto_2);
        persistenceParameterValuesDtos.add(persistenceParameterValuesDto_3);
        persistenceParameterValuesDtos.add(persistenceParameterValuesDto_4);
        List<PersistenceParameterValuesDto> tsValues = persistenceParameterValuesDtos;
        List<Timeseries> response = api.putTimeseriesValuesByPersistenceParameterValuesDtos(tsValues);

        // TODO: test validations
        assertNotNull(response);
        assertEquals(4, response.size());
    }

    private void ifGivenValuesAreInDBThanDeleteThem(AccessParameterValuesDto accessParameterValues, DeleteParameterValuesDto deleteParameterValuesDto, String interval) throws ApiException {

        Timeseries response = api.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValues, interval);
        if (response != null && response.getTimeSeriesValues() != null && response.getTimeSeriesValues().size() > 0) {
            api.deleteTimeseriesValuesByDeleteParameterValuesDto(deleteParameterValuesDto, interval);
        }

    }

    private void checkIfGivenValuesAreInDBAndCreateThem(AccessParameterValuesDto accessParameterValues, PersistenceParameterValuesDto persistenceParameterValuesDto, String interval) throws ApiException {

        Timeseries response = api.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValues, interval);
        if (response == null || response.getTimeSeriesValues() == null || response.getTimeSeriesValues().size() == 0) {
            api.putTimeseriesValuesByPersistenceParameterValuesDto(persistenceParameterValuesDto);
        }

    }

    /**
     * Removes timeseries head. The Timeseries must exists!
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteTimeseriesDefinitionByDeleteParameterHeadDtoTest() throws ApiException, InterruptedException {

        // Kontrolle, ob die zu loeschende Zeitreihe ueberhaupt vorhanden ist
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);

//        Thread.sleep(1500);

        api.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto_1);

//        Thread.sleep(1500);

        checkIfGivenTSIsInDB(accessParamterHeadDto_1);
        // TODO: test validations
    }

    private void ifGivenTSIsInDBThanDeleteIt(AccessParamterHeadDto accessParamterHeadDto, DeleteParameterHeadDto deleteParameterHeadDto) throws ApiException {
        TimeSeriesHead timeseriesDefinitionByAccessParamterHeadDto = api.getTimeseriesDefinitionByAccessParamterHeadDto(accessParamterHeadDto);
        if (timeseriesDefinitionByAccessParamterHeadDto != null) {
            api.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto);
        }
    }

    private void checkIfGivenTSIsInDBorCreateIt(AccessParamterHeadDto accessParamterHeadDto, PersistenceParameterHeadDto persistenceParameterHeadDto) throws ApiException {
        TimeSeriesHead timeseriesDefinitionByAccessParamterHeadDto = api.getTimeseriesDefinitionByAccessParamterHeadDto(accessParamterHeadDto);
        if (timeseriesDefinitionByAccessParamterHeadDto == null) {
            TimeSeriesHead response = api.putTimeseriesDefinitionByPersistenceParameterHeadDto(persistenceParameterHeadDto);
            assertNotNull(response);
        }
    }

    private void checkIfGivenTSIsInDB(AccessParamterHeadDto accessParamterHeadDto) throws ApiException {
        TimeSeriesHead timeseriesDefinitionByAccessParamterHeadDto = api.getTimeseriesDefinitionByAccessParamterHeadDto(accessParamterHeadDto);
        assertNull(timeseriesDefinitionByAccessParamterHeadDto);
    }

    /**
     * Removes timeseries heads. The Timeseries must exists!
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteTimeseriesDefinitionByDeleteParameterHeadDtosTest() throws ApiException, InterruptedException {


        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_1, persistenceParameterHeadDto_1);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_2, persistenceParameterHeadDto_2);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_3, persistenceParameterHeadDto_3);
        checkIfGivenTSIsInDBorCreateIt(accessParamterHeadDto_4, persistenceParameterHeadDto_4);

//        Thread.sleep(1500);

        api.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto_1);
        api.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto_2);
        api.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto_3);
        api.deleteTimeseriesDefinitionByDeleteParameterHeadDto(deleteParameterHeadDto_4);

//        Thread.sleep(1500);

        // TODO: test validations
        checkIfGivenTSIsInDB(accessParamterHeadDto_1);
        checkIfGivenTSIsInDB(accessParamterHeadDto_2);
        checkIfGivenTSIsInDB(accessParamterHeadDto_3);
        checkIfGivenTSIsInDB(accessParamterHeadDto_4);
    }

    /**
     * Removes values of given Timeseries. The Timeseries must exists!
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteTimeseriesValuesByDeleteParameterValuesDtoTest() throws ApiException, InterruptedException {

        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_1, persistenceParameterValuesDto_1, interval);

//        Thread.sleep(1500);

        api.deleteTimeseriesValuesByDeleteParameterValuesDto(deleteParameterValuesDto_1, interval);

        checkIfGivenValuesAreNotInDB(accessParameterValues_1, interval);

        // TODO: test validations
    }

    private void checkIfGivenValuesAreNotInDB(AccessParameterValuesDto accessParameterValues, String interval) throws ApiException {
        Timeseries timeseriesValuesByAccessParameterValuesDto = api.getTimeseriesValuesByAccessParameterValuesDto(accessParameterValues, interval);
        assertNull(timeseriesValuesByAccessParameterValuesDto);
    }

    /**
     * Removes values of given Timeseries. The Timeseries must exists!
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void deleteTimeseriesValuesByDeleteParameterValuesDtosTest() throws ApiException, InterruptedException {


        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_1, persistenceParameterValuesDto_1, interval);
        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_2, persistenceParameterValuesDto_2, interval);
        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_3, persistenceParameterValuesDto_3, interval);
        checkIfGivenValuesAreInDBAndCreateThem(accessParameterValues_4, persistenceParameterValuesDto_4, interval);

//        Thread.sleep(1500);


        List<DeleteParameterValuesDto> deleteParametersValuesDto = new ArrayList<>();

        deleteParametersValuesDto.add(deleteParameterValuesDto_1);
        deleteParametersValuesDto.add(deleteParameterValuesDto_2);
        deleteParametersValuesDto.add(deleteParameterValuesDto_3);
        deleteParametersValuesDto.add(deleteParameterValuesDto_4);

        api.deleteTimeseriesValuesByDeleteParameterValuesDtos(deleteParametersValuesDto, interval);

        checkIfGivenValuesAreNotInDB(accessParameterValues_1, interval);
        checkIfGivenValuesAreNotInDB(accessParameterValues_2, interval);
        checkIfGivenValuesAreNotInDB(accessParameterValues_3, interval);
        checkIfGivenValuesAreNotInDB(accessParameterValues_4, interval);

        // TODO: test validations
    }

    @Ignore
    @Test
    public void addAllTsHeaderForHeatPump() throws ApiException {
        List<String> indicies = new ArrayList<>();
        indicies.add("da_heizkreispumpe");
        indicies.add("iw_verdichter");
        indicies.add("da_pufferladepumpe");
        indicies.add("iw_vd_heizen");
        indicies.add("iw_waermemenge_nhz_heizen_summe");
        indicies.add("ia_quellentemperatur");
        indicies.add("iw_vd_k??hlen");
        indicies.add("iw_nhz_2");
        indicies.add("ia_raumfeuchte");
        indicies.add("ia_r??cklaufisttemperatur");
        indicies.add("ia_anlagenfrost");
        indicies.add("ia_vorlaufisttemperatur_wp");
        indicies.add("ia_quellendruck");
        indicies.add("da_reststillstand");
        indicies.add("ia_vorlaufisttemperatur_nhz");
        indicies.add("ds_nebenversionsnummer");
        indicies.add("ia_isttemperatur_hk_1");

        indicies.add("ds_ok");
        indicies.add("ia_volumenstrom");
        indicies.add("da_verdichterschuetz");
        indicies.add("da_verdichter");
        indicies.add("ia_isttemperatur_gebl??se");
        indicies.add("iw_leistungsaufnahme_vd_heizen_tag");
        indicies.add("da_quellenpumpe");
        indicies.add("ia_isttemperatur_fek");
        indicies.add("da_heizen");
        indicies.add("ds_wpm_3i");
        indicies.add("ds_software");
        indicies.add("ds_revisionsnummer");
        indicies.add("iw_nhz_1_durch_2");
        indicies.add("ia_solltemperatur_fek");
        indicies.add("iw_waermemenge_vd_heizen_tag");
        indicies.add("ds_hauptversionsnummer");
        indicies.add("ia_solltemperatur_gebl??se");
        indicies.add("ia_puffersolltemperatur");
        indicies.add("ds_sg_ready");
        indicies.add("iw_leistungsaufnahme_vd_heizen_summe");
        indicies.add("iw_waermemenge_vd_heizen_summe");
        indicies.add("iw_nhz_1");
        indicies.add("da_heizkreis_1_pumpe");
        indicies.add("ia_pufferisttemperatur");
        indicies.add("ia_taupunkttemperatur");
        indicies.add("ia_aussentemperatur");
        indicies.add("ia_solltemperatur_hk_1");
        indicies.add("ia_heizungsdruck");

        indicies.add("ia_solltemperatur_flaeche");
        indicies.add("ia_isttemperatur_flaeche");

        List<PersistenceParameterHeadDto> stiebelEltronHeatPumpRawDatasTest = indicies.stream().map(s -> new PersistenceParameterHeadDto().tsComposedKey(new TimeSeriesComposedKey().tsId(s).databaseName("StiebelEltronHeatPumpRawDatasTest")).tsUnit(TimeSeriesUnit.MW).tsraster(TimeSeriesRaster.PT15S)).collect(Collectors.toList());


        List<TimeSeriesHead> response = api.putTimeseriesDefinitionsByPersistenceParameterHeadDtos(stiebelEltronHeatPumpRawDatasTest);

        assertEquals(indicies.size(), response.size());


    }


}
