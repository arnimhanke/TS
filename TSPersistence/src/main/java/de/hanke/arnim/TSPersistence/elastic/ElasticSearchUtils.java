package de.hanke.arnim.TSPersistence.elastic;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.hanke.arnim.TSPersistence.TimeseriesHeadEntity;
import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchScrollRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


/**
 * Deprecated, because not in use and not under maintenance
 */
@Deprecated()
public class ElasticSearchUtils implements de.hanke.arnim.TSPersistence.TimeseriesHeadPersistenceInterface {


    public final ObjectMapper mapper = new ObjectMapper();

    public String ADDRESS_ELASTICSEARCH;
    public int PORT_ELASTICSEARCH;
    public String PROTOCOL_ELASTICSEARCH;
    public String ADDRESS_ISG;
    Properties properties;

    public static final String QUELL_INDEX = "source_timeseries_definitions";
    public static final String QUELL_ES_TYPE = "source";

    public ElasticSearchUtils() {
        properties = new Properties();
        try {
            properties.load(ElasticSearchUtils.class.getClassLoader().getResourceAsStream("elasticsearch.properties"));
            ADDRESS_ELASTICSEARCH = properties.getProperty("ADRESS_ELASTICSEARCH");
            PORT_ELASTICSEARCH = Integer.parseInt(properties.getProperty("PORT_ELASTICSEARCH"));
            ADDRESS_ISG = properties.getProperty("ADDRESS_ISG");
        } catch (Exception e) {
            System.out.println("Fehler beim Laden der Konfiguration");
            e.printStackTrace();
        }
    }

    public RestHighLevelClient generateESRestClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(ADDRESS_ELASTICSEARCH, PORT_ELASTICSEARCH, PROTOCOL_ELASTICSEARCH)));
    }

    public RestHighLevelClient generateLocalESRestClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(properties.getProperty("ADRESS_ELASTICSEARCH_LOKAL"), PORT_ELASTICSEARCH, PROTOCOL_ELASTICSEARCH)));
    }


    @Override
    public List<TimeseriesHeadEntity> loadTimeseriesHeadsFromDatabase(String searchPatternTsID, String database) {
        List<TimeseriesHeadEntity> allSearchHits = new ArrayList<>();
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(QUELL_INDEX);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.size(10);

        searchRequest.source(searchSourceBuilder);
        Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        searchRequest.scroll(scroll);
        searchSourceBuilder.query(QueryBuilders.regexpQuery("tsId", searchPatternTsID));

        try (RestHighLevelClient client = generateESRestClient()) {
            SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
            String scrollId = searchResponse.getScrollId();
            SearchHit[] searchHits = searchResponse.getHits().getHits();

            for (SearchHit searchHit : searchHits) {
                // add ValueDtos to list
                TimeseriesHeadEntity timeseriesHeadEntity = mapper.readValue(searchHit.getSourceAsString(), TimeseriesHeadEntity.class);
                if (timeseriesHeadEntity.getId().getDatabaseName().equals(database)) {
                    allSearchHits.add(timeseriesHeadEntity);
                }
            }

            while (searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = client.scroll(scrollRequest, RequestOptions.DEFAULT);
                scrollId = searchResponse.getScrollId();
                searchHits = searchResponse.getHits().getHits();

                for (SearchHit searchHit : searchHits) {
                    // add ValueDtos to list
                    allSearchHits.add(mapper.readValue(searchHit.getSourceAsString(), TimeseriesHeadEntity.class));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allSearchHits;
    }

    @Override
    public TimeseriesHeadEntity putPeriodicTimeseriesHeadIntoDatabase(TimeseriesHeadEntity value) {

        try (RestHighLevelClient client = generateESRestClient()) {
            IndexRequest source = new IndexRequest(QUELL_INDEX.toLowerCase()).id(value.getId().getTsId().toLowerCase() + "-" + value.getId().getDatabaseName().toLowerCase()).source(mapper.writeValueAsString(value), XContentType.JSON);
            IndexResponse indexResponse = client.index(source, RequestOptions.DEFAULT);
            if (indexResponse.status().compareTo(RestStatus.CREATED) == 0 || indexResponse.status().compareTo(RestStatus.OK) == 0) {
                return value;
            } else {
                System.err.println(indexResponse.status());
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public List<TimeseriesHeadEntity> putPeriodicTimeseriesHeadsIntoDatabase(List<TimeseriesHeadEntity> value) {

        boolean overallStatus = true;

        ArrayList<TimeseriesHeadEntity> timeseriesHeadEntities = new ArrayList<>();
        for (TimeseriesHeadEntity periodicTimeseriesHead : value) {
            TimeseriesHeadEntity timeseriesHeadEntity = putPeriodicTimeseriesHeadIntoDatabase(periodicTimeseriesHead);
            timeseriesHeadEntities.add(timeseriesHeadEntity);
        }

        return timeseriesHeadEntities;
    }

    @Override
    public boolean deleteTimeseriesDefinitionFromDatabase(String tsId, String databaseName) {
        try (RestHighLevelClient client = generateESRestClient()) {
            DeleteRequest deleteRequest = new DeleteRequest().index(QUELL_INDEX).id(tsId + "-" + databaseName);
            DeleteResponse delete = client.delete(deleteRequest, RequestOptions.DEFAULT);
            if (delete.status().compareTo(RestStatus.OK) == 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
