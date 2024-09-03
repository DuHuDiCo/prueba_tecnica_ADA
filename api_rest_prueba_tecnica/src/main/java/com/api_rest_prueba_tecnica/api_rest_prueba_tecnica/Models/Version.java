package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "VERSION")
public class Version {

    @Id
    @Column(name = "VERSION_ID")
    private Long versionId;

    @Column(name = "VERSION", length = 50, nullable = false)
    private String version;

    @Column(name = "VERSION_DESCRIPTION", length = 255, nullable = true)
    private String versionDescription;

    @ManyToOne
    @JoinColumn(name = "APP_ID")
    private Application application;

    @OneToMany(mappedBy = "version", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    List<VersionCompany> versionCompanies = new ArrayList<>();

    public Version() {
    }

    public void agregarAplicacion(Application application) {
        this.application = application;
        application.getVersions().add(this);
    }

    public Version(Long versionId, String version, String versionDescription) {
        this.versionId = versionId;
        this.version = version;
        this.versionDescription = versionDescription;
    }

    public Version(Long versionId, String version, String versionDescription, Application application,
            List<VersionCompany> versionCompanies) {
        this.versionId = versionId;
        this.version = version;
        this.versionDescription = versionDescription;
        this.application = application;
        this.versionCompanies = versionCompanies;
    }

    public Long getVersionId() {
        return versionId;
    }

    public void setVersionId(Long versionId) {
        this.versionId = versionId;
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

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public List<VersionCompany> getVersionCompanies() {
        return versionCompanies;
    }

    public void setVersionCompanies(List<VersionCompany> versionCompanies) {
        this.versionCompanies = versionCompanies;
    }

    @Override
    public String toString() {
        return "Version [versionId=" + versionId + ", version=" + version + ", versionDescription=" + versionDescription
                + ", application=" + application + ", versionCompanies=" + versionCompanies + "]";
    }

}
