package com.alliness.uzd.service;

import com.alliness.uzd.external.uzd.TrainsResponseEntity;
import com.alliness.uzd.web.dto.TrainData;

public class BookingMapper {

    public static TrainData map(TrainsResponseEntity.Data.Trains train) {
        TrainData           data          = new TrainData();
        TrainData.Direction fromDirection = new TrainData.Direction();
        TrainData.Direction toDirection   = new TrainData.Direction();

        fromDirection.setDateTime(train.getFrom().getDate());
        fromDirection.setTitle(train.getFrom().getStationTitle());
        fromDirection.setValue(train.getFrom().getStationCode());

        toDirection.setDateTime(train.getTo().getDate());
        toDirection.setTitle(train.getTo().getStationTitle());
        toDirection.setValue(train.getTo().getStationCode());

        data.setFrom(fromDirection);
        data.setTo(toDirection);
        data.setTravelTime(train.getTravelTime());
        data.setNumber(train.getNumber());
        data.setTotalPlaces(train.getTotalPlaces());

        for (TrainsResponseEntity.Data.Trains.WagonType wagonType : train.getWagonTypes()) {
            data.addWagon(BookingMapper.mapWagon(wagonType));
        }

        return data;
    }

    private static TrainData.WagonData mapWagon(TrainsResponseEntity.Data.Trains.WagonType wagonType) {
        TrainData.WagonData wagon = new TrainData.WagonData();
        wagon.setPlaces(wagonType.getPlaces())
             .setWagonClass(wagonType.getClazz())
             .setWagonType(wagonType.getType());

        return wagon;
    }
}
