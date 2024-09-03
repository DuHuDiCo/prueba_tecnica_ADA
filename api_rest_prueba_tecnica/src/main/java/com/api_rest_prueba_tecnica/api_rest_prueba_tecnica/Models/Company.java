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
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "COMPANY")
public class Company {

    @Id
    @Column(name = "ID_COMPANY")
    private Long idCompany;

    @Column(name = "CODIGO_COMPANY", unique = true, nullable = false, length = 20)
    private String codigoCompany;

    @Column(name = "NAME_COMPANY", nullable = false, length = 100)
    private String nameCompany;

    @Column(name = "DESCRIPTION_COMPANY", length = 255)
    private String descriptionCompany;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    List<VersionCompany> versionCompanies = new ArrayList<>();

    public Company() {
    }

    public Company(Long idCompany, String codigoCompany, String nameCompany, String descriptionCompany) {
        this.idCompany = idCompany;
        this.codigoCompany = codigoCompany;
        this.nameCompany = nameCompany;
        this.descriptionCompany = descriptionCompany;
    }

    public Long getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(Long idCompany) {
        this.idCompany = idCompany;
    }

    public String getCodigoCompany() {
        return codigoCompany;
    }

    public void setCodigoCompany(String codigoCompany) {
        this.codigoCompany = codigoCompany;
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

    public List<VersionCompany> getVersionCompanies() {
        return versionCompanies;
    }

    public void setVersionCompanies(List<VersionCompany> versionCompanies) {
        this.versionCompanies = versionCompanies;
    }

    @Override
    public String toString() {
        return "Company [idCompany=" + idCompany + ", codigoCompany=" + codigoCompany + ", nameCompany=" + nameCompany
                + ", descriptionCompany=" + descriptionCompany + ", versionCompanies=" + versionCompanies + "]";
    }

}
