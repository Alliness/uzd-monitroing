package com.alliness.uzd.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestClient {

    private final Logger log = LoggerFactory.getLogger(RestClient.class);
    private String       domain;
    private RestTemplate rest;
    private HttpHeaders  headers;
    private HttpStatus   status;

    public RestClient(String domain) {
        this.rest = new RestTemplate();
        this.headers = new HttpHeaders();
        this.domain = domain;
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "*/*");
        headers.add("Connection", "keep-alive");
    }

    public RestClient addHeader(String name, String value){
        headers.add(name,value);
        return this;
    }

    public HttpHeaders getRequestHeaders(){
        return headers;
    }

    public <RS> ResponseEntity<RS> get(String uri, Class<RS> clazz) {
        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        log.info("[GET] {}{}", domain, uri);
        return rest.exchange(domain + uri, HttpMethod.GET, requestEntity, clazz);
    }

    public <RQ, RS> ResponseEntity<RS> post(String uri, RQ json, Class<RS> clazz) {
        HttpEntity<RQ> requestEntity = new HttpEntity<>(json, headers);
        log.info("[POST] {}{}", domain, uri);
        log.info("{}",json.toString());
        return rest.exchange(domain + uri, HttpMethod.POST, requestEntity, clazz);
    }

    public HttpStatus getStatus() {
        return status;
    }

    private void setStatus(HttpStatus status) {
        this.status = status;
    }
}
