package com.benutzer.washbayin.utilities;

/**
 * Created by amitesh on 23/5/16.
 */
public class HTTPStructure {
    private String contentType;
    private String dataJson;
    private String authToken;

    public HTTPStructure() {
        this.authToken = null;
        this.contentType = null;
        this.dataJson = null;
        this.method = null;
        this.url = null;
    }

    private String url;
    private String method;

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
