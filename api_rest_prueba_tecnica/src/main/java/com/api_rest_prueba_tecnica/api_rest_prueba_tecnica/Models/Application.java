package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "APPLICATION")
public class Application {

    @Id
    @Column(name = "APP_ID")
    private Long idApplication;

    @Column(name = "APP_CODE", length = 20, unique = true, nullable = false)
    private String appCode;

    @Column(name = "APP_NAME", length = 100, nullable = false)
    private String appName;

    @Column(name = "APP_DESCRIPTION", length = 255, nullable = true)
    private String appDescription;

    @OneToMany(mappedBy = "application", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    List<Version> versions = new ArrayList<>();

    public Application() {
    }

    public Application(Long idApplication, String appCode, String appName, String appDescription) {
        this.idApplication = idApplication;
        this.appCode = appCode;
        this.appName = appName;
        this.appDescription = appDescription;
    }

    public Application(String appCode, String appName, String appDescription, List<Version> versions) {
        this.appCode = appCode;
        this.appName = appName;
        this.appDescription = appDescription;
        this.versions = versions;
    }

    public Long getIdApplication() {
        return idApplication;
    }

    public void setIdApplication(Long idApplication) {
        this.idApplication = idApplication;
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

    public List<Version> getVersions() {
        return versions;
    }

    public void setVersions(List<Version> versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        return "Application [idApplication=" + idApplication + ", appCode=" + appCode + ", appName=" + appName
                + ", appDescription=" + appDescription + ", versions=" + versions + "]";
    }

}
