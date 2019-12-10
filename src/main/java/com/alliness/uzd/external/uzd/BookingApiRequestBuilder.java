package com.alliness.uzd.external.uzd;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookingApiRequestBuilder {

    private static final String DEFAULT_TIME = "00:00:00";
    private RequestEntity request;
    private final String             requestId;
    private final String             requestTime;

    private BookingApiRequestBuilder() {
        request = new RequestEntity();
        requestTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        requestId = generateRequestId();
    }

    private String generateRequestId() {
        String digest = "";
        try {

            MessageDigest md   = MessageDigest.getInstance("MD5");
            byte[]        hash = md.digest(requestTime.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb   = new StringBuilder(2 * hash.length);

            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return digest.toUpperCase();
    }

    public static BookingApiRequestBuilder builder() {
        return new BookingApiRequestBuilder();
    }

    public BookingApiRequestBuilder setLocationFrom(String locationNumber) {
        request.getData().setFromCode(locationNumber);
        return this;
    }

    public BookingApiRequestBuilder setLocationTo(String locationNumber) {
        request.getData().setToCode(locationNumber);
        return this;
    }

    public String getRequestId() {
        return requestId;
    }

    public BookingApiRequestBuilder setDispatchDateTime(LocalDateTime dateTime) {
        String date = dateTime.toLocalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = dateTime.toLocalTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        request.getData().setDate(date);
        request.getData().setTimeFrom(time);
        return this;
    }

    public BookingApiRequestBuilder setDispatchDateTime(String date, String time) {
        request.getData().setDate(date);
        if(time == null){
            time = DEFAULT_TIME;
        }
        request.getData().setTimeFrom(time);
        return this;
    }

    public RequestEntity build() {

        request.setLang("ru");
        request.setRequestId(requestId);
        request.setTranId("trains");
        request.setVersion("1.013");
        request.setDateTimeUtc(requestTime);
        request.setOs(1);
        return request;
    }
}
