package com.alliness.uzd.web.dto;

import com.alliness.uzd.core.Serializable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TrainData extends Serializable {

    private Direction       from;
    private Direction       to;
    private String          number;
    private String          travelTime;
    private List<WagonData> wagons;
    private int             totalPlaces;

    public Direction getFrom() {
        return from;
    }

    public Direction getTo() {
        return to;
    }

    public String getNumber() {
        return number;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public List<WagonData> getWagons() {
        if (wagons == null) {
            wagons = new ArrayList<>();
        }
        return wagons;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public TrainData setFrom(Direction from) {
        this.from = from;
        return this;
    }

    public TrainData setTo(Direction to) {
        this.to = to;
        return this;
    }

    public TrainData setNumber(String number) {
        this.number = number;
        return this;
    }

    public TrainData setTravelTime(String travelTime) {
        this.travelTime = travelTime;
        return this;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public TrainData addWagon(WagonData wagon) {
        if (wagons == null) {
            wagons = new ArrayList<>();
        }
        wagons.add(wagon);
        return this;
    }

    public static class Direction extends StationData {

        private String date;
        private String time;

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public Direction setDateTime(String dateTime) {
            LocalDateTime ldt = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            date = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            time = ldt.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            return this;
        }

        public Direction setTitle(String title) {
            this.title = title;
            return this;
        }

        public Direction setValue(String value) {
            this.value = Integer.parseInt(value);
            return this;
        }
    }

    public static class WagonData extends Serializable {
        private int    places;
        private String wagonClass;
        private String wagonType;

        public int getPlaces() {
            return places;
        }

        public String getWagonClass() {
            return wagonClass;
        }

        public String getWagonType() {
            return wagonType;
        }

        public WagonData setPlaces(int places) {
            this.places = places;
            return this;
        }

        public WagonData setWagonClass(String wagonClass) {
            this.wagonClass = wagonClass;
            return this;
        }

        public WagonData setWagonType(String wagonType) {
            this.wagonType = wagonType;
            return this;
        }
    }
}
