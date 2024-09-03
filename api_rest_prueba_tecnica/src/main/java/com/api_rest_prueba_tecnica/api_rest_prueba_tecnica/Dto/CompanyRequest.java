package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Dto;

public class CompanyRequest {

    private String codigoCompany;
    private String nameCompany;
    private String descriptionCompany;

    private VersionRequest versionRequest;

    private String versionCompanyDescription;

    public CompanyRequest() {
    }

    public CompanyRequest(String codigoCompany, String nameCompany, String descriptionCompany,
            VersionRequest versionRequest) {
        this.codigoCompany = codigoCompany;
        this.nameCompany = nameCompany;
        this.descriptionCompany = descriptionCompany;
        this.versionRequest = versionRequest;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

    public String getDescriptionCompany() {
        return descriptionCompany;
    }

    public void setDescriptionCompany(String descriptionCompany) {
        this.descriptionCompany = descriptionCompany;
    }

    public VersionRequest getVersionRequest() {
        return versionRequest;
    }

    public void setVersionRequest(VersionRequest versionRequest) {
        this.versionRequest = versionRequest;
    }

    public String getCodigoCompany() {
        return codigoCompany;
    }

    public void setCodigoCompany(String codigoCompany) {
        this.codigoCompany = codigoCompany;
    }

    public String getVersionCompanyDescription() {
        return versionCompanyDescription;
    }

    public void setVersionCompanyDescription(String versionCompanyDescription) {
        this.versionCompanyDescription = versionCompanyDescription;
    }

}
