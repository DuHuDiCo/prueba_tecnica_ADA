package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Service;

import org.springframework.http.ResponseEntity;

import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Dto.CompanyRequest;

public interface CompanyService {

    // GUARDA UNA COMPAÑIA CON SUS RELACIONES
    public ResponseEntity<Object> guardarCompany(CompanyRequest companyRequest);

    // OBTIENE TODAS LAS COMPAÑIAS EXISTENTES
    public ResponseEntity<Object> listarCompanies();

    // OBTIENE UNA COMPAÑIA POR EL CODIGO DE COMPAÑIA
    public ResponseEntity<Object> obtenerCompany(String codeCompany);

    // ELIMINA UNA COMPAÑIA POR SU CODIGO DE COMPAÑIA
    public ResponseEntity<Object> eliminarCompany(String codeCompany);

    // EDITA UNA COMPAÑIA
    public ResponseEntity<Object> editarCompany(String codeCompany, CompanyRequest companyRequest);

}
