package com.alliness.uzd.service;

import com.alliness.uzd.core.Serializable;
import com.alliness.uzd.external.uzd.BookingApi;
import com.alliness.uzd.external.uzd.BookingApiRequestBuilder;
import com.alliness.uzd.external.uzd.RequestEntity;
import com.alliness.uzd.external.uzd.TrainsResponseEntity;
import com.alliness.uzd.web.dto.StationData;
import com.alliness.uzd.web.dto.TrainData;
import com.alliness.uzd.web.dto.TrainRequestData;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class ApiService {

    private       BookingApi api;
    private final Logger     log = LoggerFactory.getLogger(ApiService.class);

    public ApiService(BookingApi api) {
        this.api = api;
    }

    public List<StationData> searchStations(String term) {
        List<StationData>      data     = new ArrayList<>();
        ResponseEntity<byte[]> response = api.getStation(term);

        if (!response.getStatusCode().isError() && response.getBody() != null) {
            for (Object o : new JSONArray(new String(response.getBody(), StandardCharsets.UTF_8))) {
                data.add(Serializable.deserialize(o, StationData.class));
            }
        } else {
            log.error("Has Error response code for searchStations method. StatusCode:{} \t body: {}", response.getStatusCode(), response.toString());
        }

        return data;
    }

    public List<TrainData> getTrains(TrainRequestData requestData) {
        List<TrainData> data  = new ArrayList<>();
        List<String>    dates = new ArrayList<>();

        dates.add(requestData.getDate());
        if (requestData.getDateUntil() != null) {
            LocalDate startDate = LocalDate.parse(requestData.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate endDate = LocalDate.parse(requestData.getDateUntil(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate);

            IntStream.iterate(1, i -> i + 1)
                                           .limit(numOfDaysBetween)
                                           .mapToObj(i -> startDate.plusDays(i).format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                           .forEach(dates::add);
        }

        for (String date : dates) {
            RequestEntity request = BookingApiRequestBuilder.builder()
                                                            .setLocationFrom(String.valueOf(requestData.getFrom()))
                                                            .setLocationTo(String.valueOf(requestData.getTo()))
                                                            .setDispatchDateTime(date, requestData.getTime())
                                                            .build();

            getTrainForDate(request, data);
        }

        return data;
    }

    private void getTrainForDate(RequestEntity request, List<TrainData> data) {
        ResponseEntity<TrainsResponseEntity> response = api.getTrains(request);
        if (!response.getStatusCode().isError() && response.getBody() != null) {
            for (TrainsResponseEntity.Data.Trains train : response.getBody().getData().getTrains()) {
                if(train.getTotalPlaces() > 0){
                    data.add(BookingMapper.map(train));
                }
            }
        } else {
            log.error("Has Error response code for searchStations method. StatusCode:{} \t body: {}", response.getStatusCode(), response.toString());
        }
    }
}
