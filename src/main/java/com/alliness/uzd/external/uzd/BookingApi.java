package com.alliness.uzd.external.uzd;

import com.alliness.uzd.core.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookingApi {

    private final        Logger log         = LoggerFactory.getLogger(BookingApi.class);
    private static final String domain      = "https://booking.uz.gov.ua";
    private static final String STATION_URI = "/train_search/station/?term={term}";
    private static final String TRAINS_URI  = "/api/";

    private RestClient http;

    public BookingApi() {
        this.http = new RestClient(domain);
        this.http.addHeader("User-Agent", "BookingUZ/1 CFNetwork/978.0.7 Darwin/18.7.0");
    }

    public ResponseEntity<byte[]> getStation(String term) {
        return http.get(STATION_URI.replace("{term}", term), byte[].class);
    }

    public ResponseEntity<TrainsResponseEntity> getTrains(RequestEntity request) {
        return http.post(TRAINS_URI, request.serialize().toString(), TrainsResponseEntity.class);
    }

}
