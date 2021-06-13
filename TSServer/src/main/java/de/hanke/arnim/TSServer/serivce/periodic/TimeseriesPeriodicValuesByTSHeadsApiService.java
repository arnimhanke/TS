package de.hanke.arnim.TSServer.serivce.periodic;

import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.PeriodicTimeseries;
import de.hanke.arnim.TSServer.model.Timeseries;
import de.hanke.arnim.TSServer.serivce.TimeseriesValuesByTSHeadsApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TimeseriesPeriodicValuesByTSHeadsApiService {

    @Autowired
    TimeseriesValuesByTSHeadsApiService timeseriesValuesByTSHeadsApiService;

    public static PeriodicTimeseries convertTimeseriesToPeriodicTimeseries(Timeseries timeseries) {

        PeriodicTimeseries periodicTimeseries = new PeriodicTimeseries();
        periodicTimeseries.setTimeSeriesHead(timeseries.getTimeSeriesHead());

        ArrayList<BigDecimal> timeSeriesValues = new ArrayList<>();
        timeseries.getTimeSeriesValues().stream().forEach(timeSeriesValue -> timeSeriesValues.add(timeSeriesValue.getValue()));

        periodicTimeseries.setTimeSeriesValues(timeSeriesValues);
        return periodicTimeseries;
    }

    public List<PeriodicTimeseries> getTimeseriesValuesByAccessParameterValuesDtos(List<AccessParameterValuesDto> accessParameterValuesDtos, String intervalAsString) {
        List<Timeseries> timeseriesValuesByAccessParameterValuesDtos = timeseriesValuesByTSHeadsApiService
                .getTimeseriesValuesByAccessParameterValuesDtos(accessParameterValuesDtos, intervalAsString, true);
        return timeseriesValuesByAccessParameterValuesDtos
                .stream()
                .map(TimeseriesPeriodicValuesByTSHeadsApiService::convertTimeseriesToPeriodicTimeseries)
                .collect(Collectors.toList());
    }
}
