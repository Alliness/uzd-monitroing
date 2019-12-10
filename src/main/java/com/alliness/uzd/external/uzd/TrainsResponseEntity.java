package com.alliness.uzd.external.uzd;

import com.alliness.uzd.core.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class TrainsResponseEntity extends Serializable {

    @JsonProperty("datetime_utc")
    private String datetimeUtc;
    @JsonProperty("version")
    private String version;
    @JsonProperty("tran_id")
    private String tranId;
    @JsonProperty("data")
    private Data data;

    //region getters
    public String getDatetimeUtc() {
        return datetimeUtc;
    }

    public String getVersion() {
        return version;
    }

    public String getTranId() {
        return tranId;
    }

    public Data getData() {
        return data;
    }
    //endregion

    public static class Data extends Serializable {

        @JsonProperty("result_code")
        private int resultCode;
        @JsonProperty("trains")
        private List<Trains> trains;

        //region getters
        public int getResultCode() {
            return resultCode;
        }

        public List<Trains> getTrains() {
            return trains;
        }
        //endregion

        public static class Trains extends Serializable {

            @JsonProperty("number")
            private String number;
            @JsonProperty("category")
            private int category;
            @JsonProperty("is_transformer")
            private boolean isTransformer;
            @JsonProperty("from")
            private Direction from;
            @JsonProperty("to")
            private Direction to;
            @JsonProperty("travel_time")
            private String travelTime;
            @JsonProperty("total_places")
            private int totalPlaces;
            @JsonProperty("wagon_types")
            private List<WagonType> wagonTypes;
            @JsonProperty("allow_stud")
            private boolean allowStud;
            @JsonProperty("allow_privilege")
            private boolean allowPrivilege;
            @JsonProperty("allow_transportation")
            private boolean allowTransportation;
            @JsonProperty("allow_booking")
            private boolean allowBooking;
            @JsonProperty("is_cis")
            private boolean isCis;
            @JsonProperty("is_europe")
            private boolean isEurope;

            //region getters
            public String getNumber() {
                return number;
            }

            public int getCategory() {
                return category;
            }

            public boolean isTransformer() {
                return isTransformer;
            }

            public Direction getFrom() {
                return from;
            }

            public Direction getTo() {
                return to;
            }

            public String getTravelTime() {
                return travelTime;
            }

            public int getTotalPlaces() {
                return totalPlaces;
            }

            public List<WagonType> getWagonTypes() {
                return wagonTypes;
            }

            public boolean isAllowStud() {
                return allowStud;
            }

            public boolean isAllowPrivilege() {
                return allowPrivilege;
            }

            public boolean isAllowTransportation() {
                return allowTransportation;
            }

            public boolean isAllowBooking() {
                return allowBooking;
            }

            public boolean isCis() {
                return isCis;
            }

            public boolean isEurope() {
                return isEurope;
            }
            //endregion
            public static class Direction {

                @JsonProperty("station_code")
                private String stationCode;
                @JsonProperty("station_title")
                private String stationTitle;
                @JsonProperty("date")
                private String date;

                //region getters
                public String getStationCode() {
                    return stationCode;
                }

                public String getStationTitle() {
                    return stationTitle;
                }

                public String getDate() {
                    return date;
                }
                //endregion
            }

            public static class WagonType {

                @JsonProperty("type")
                private String type;
                @JsonProperty("class")
                private String clazz;
                @JsonProperty("places")
                private int    places;

                //region getters
                public String getType() {
                    return type;
                }

                public String getClazz() {
                    return clazz;
                }

                public int getPlaces() {
                    return places;
                }
                //endregion
            }
        }
    }
}
