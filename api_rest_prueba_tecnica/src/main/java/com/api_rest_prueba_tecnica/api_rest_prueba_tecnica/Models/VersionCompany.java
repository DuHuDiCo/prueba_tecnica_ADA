package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "VERSION_COMPANY")
public class VersionCompany {

    @Id
    @Column(name = "VERSION_COMPANY_ID")
    private Long idVersionCompany;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    @JsonBackReference
    private Company company;

    @ManyToOne
    @JoinColumn(name = "VERSION_ID")
    @JsonManagedReference
    private Version version;

    @Column(name = "VERSION_COMPANY_DECRIPTION", length = 255)
    private String versionCompanyDescription;

    public VersionCompany() {
    }

    public void agregarVersionCompany(Company company, Version version) {
        this.company = company;
        company.getVersionCompanies().add(this);
        this.version = version;
        version.getVersionCompanies().add(this);

    }

    public VersionCompany(Long idVersionCompany, Company company, Version version, String versionCompanyDescription) {
        this.idVersionCompany = idVersionCompany;
        this.company = company;
        this.version = version;
        this.versionCompanyDescription = versionCompanyDescription;
    }

    public Long getIdVersionCompany() {
        return idVersionCompany;
    }

    public void setIdVersionCompany(Long idVersionCompany) {
        this.idVersionCompany = idVersionCompany;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public String getVersionCompanyDescription() {
        return versionCompanyDescription;
    }

    public void setVersionCompanyDescription(String versionCompanyDescription) {
        this.versionCompanyDescription = versionCompanyDescription;
    }

    @Override
    public String toString() {
        return "VersionCompany [idVersionCompany=" + idVersionCompany + ", company=" + company + ", version=" + version
                + ", versionCompanyDescription=" + versionCompanyDescription + "]";
    }

}
