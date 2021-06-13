package de.hanke.arnim.TSServer.serivce.periodic;

import de.hanke.arnim.TSServer.model.AccessParameterValuesDto;
import de.hanke.arnim.TSServer.model.PeriodicTimeseries;
import de.hanke.arnim.TSServer.model.Timeseries;
import de.hanke.arnim.TSServer.serivce.TimeseriesValuesByTSHeadApiService;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.ArrayList;

@Controller
public class TimeseriesPeriodicValuesByTSHeadApiService {

    public static PeriodicTimeseries convertTimeseriesToPeriodicTimeseries(Timeseries timeseries) {

        PeriodicTimeseries periodicTimeseries = new PeriodicTimeseries();
        periodicTimeseries.setTimeSeriesHead(timeseries.getTimeSeriesHead());

        ArrayList<BigDecimal> timeSeriesValues = new ArrayList<>();
        timeseries.getTimeSeriesValues().stream().forEach(timeSeriesValue -> timeSeriesValues.add(timeSeriesValue.getValue()));

        periodicTimeseries.setTimeSeriesValues(timeSeriesValues);
        return periodicTimeseries;
    }

    public PeriodicTimeseries getTimeseriesValuesByAccessParameterValuesDto(AccessParameterValuesDto accessParameterValuesDto, String intervalAsString) {
        Timeseries timeseries = new TimeseriesValuesByTSHeadApiService().getTimeseriesValuesByAccessParameterValuesDto(accessParameterValuesDto, intervalAsString, true);
        return convertTimeseriesToPeriodicTimeseries(timeseries);
    }


}
