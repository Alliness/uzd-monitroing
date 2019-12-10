package com.alliness.uzd.web;

import com.alliness.uzd.service.ApiService;
import com.alliness.uzd.web.dto.StationData;
import com.alliness.uzd.web.dto.TrainData;
import com.alliness.uzd.web.dto.TrainRequestData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Logger log = LoggerFactory.getLogger(ApiController.class);
    private final ApiService service;

    public ApiController(ApiService service){
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/stations")
    public ResponseEntity<List<StationData>> getStations(@RequestParam("term") String term){
        if(term.length() < 3){
            log.warn("Param length [term= '{}'] must be 3 symbols or more.", term);
            return ResponseEntity.of(Optional.empty());
        }else{
            return ResponseEntity.of(Optional.of(service.searchStations(term)));
        }
    }

    @RequestMapping(method = RequestMethod.POST, path = "/trains")
    public ResponseEntity<List<TrainData>> getTrains(@RequestBody TrainRequestData request){
        if(request == null){
            log.warn("unable to parse request");
            return ResponseEntity.of(Optional.empty());
        }else{
            return ResponseEntity.of(Optional.of(service.getTrains(request)));
        }
    }


}
