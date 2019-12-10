package com.alliness.uzd.external.uzd;

import com.alliness.uzd.core.Serializable;
import org.json.JSONPropertyName;

public class RequestEntity extends Serializable{

    private String             requestId;
    private String             lang;
    private RequestEntity.Data data;
    private String             tranId;
    private String             version;
    private String             dateTimeUtc;
    private int                os;

    public RequestEntity() {
        this.data = new Data();
    }

    @JSONPropertyName("request_id")
    public String getRequestId() {
        return requestId;
    }

    public String getLang() {
        return lang;
    }

    public Data getData() {
        return data;
    }

    @JSONPropertyName("tran_id")
    public String getTranId() {
        return tranId;
    }

    public String getVersion() {
        return version;
    }

    @JSONPropertyName("datetime_utc")
    public String getDateTimeUtc() {
        return dateTimeUtc;
    }

    public int getOs() {
        return os;
    }

    public RequestEntity setRequestId(String requestId) {
        this.requestId = requestId;
        return this;
    }

    public RequestEntity setLang(String lang) {
        this.lang = lang;
        return this;
    }

    public RequestEntity setData(Data data) {
        this.data = data;
        return this;
    }

    public RequestEntity setTranId(String tranId) {
        this.tranId = tranId;
        return this;
    }

    public RequestEntity setVersion(String version) {
        this.version = version;
        return this;
    }

    public RequestEntity setDateTimeUtc(String dateTimeUtc) {
        this.dateTimeUtc = dateTimeUtc;
        return this;
    }

    public RequestEntity setOs(int os) {
        this.os = os;
        return this;
    }

    public static class Data{

        private String date;
        private String timeFrom;
        private String toCode;
        private String fromCode;

        public String getDate() {
            return date;
        }

        @JSONPropertyName("time_from")
        public String getTimeFrom() {
            return timeFrom;
        }

        @JSONPropertyName("to_code")
        public String getToCode() {
            return toCode;
        }

        @JSONPropertyName("from_code")
        public String getFromCode() {
            return fromCode;
        }

        public Data setDate(String date) {
            this.date = date;
            return this;
        }

        public Data setTimeFrom(String timeFrom) {
            this.timeFrom = timeFrom;
            return this;
        }

        public Data setToCode(String toCode) {
            this.toCode = toCode;
            return this;
        }

        public Data setFromCode(String fromCode) {
            this.fromCode = fromCode;
            return this;
        }
    }

}
