package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models.VersionCompany;

@Repository
public interface VersionCompanyRepository extends JpaRepository<VersionCompany, Long> {

}
