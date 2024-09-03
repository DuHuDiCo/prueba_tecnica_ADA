package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Dto;

public class VersionRequest {

    private String version;
    private String versionDescription;

    private ApplicationRequest applicationRequest;

    public VersionRequest(String version, String versionDescription, ApplicationRequest applicationRequest) {
        this.version = version;
        this.versionDescription = versionDescription;
        this.applicationRequest = applicationRequest;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersionDescription() {
        return versionDescription;
    }

    public void setVersionDescription(String versionDescription) {
        this.versionDescription = versionDescription;
    }

    public ApplicationRequest getApplicationRequest() {
        return applicationRequest;
    }

    public void setApplicationRequest(ApplicationRequest applicationRequest) {
        this.applicationRequest = applicationRequest;
    }

}
