package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Dto;

public class ApplicationRequest {

    private String appCode;
    private String appName;
    private String appDescription;

    public ApplicationRequest() {
    }

    public ApplicationRequest(String appCode, String appName, String appDescription) {
        this.appCode = appCode;
        this.appName = appName;
        this.appDescription = appDescription;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return appDescription;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

}
