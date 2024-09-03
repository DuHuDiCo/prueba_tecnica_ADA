package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Company findByCodigoCompany(String code);

}
