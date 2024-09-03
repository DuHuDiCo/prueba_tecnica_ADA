package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Dto.CompanyRequest;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Service.CompanyService;

@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/")
    public ResponseEntity<Object> guardarCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.guardarCompany(companyRequest);
    }

    @GetMapping("/")
    public ResponseEntity<Object> listarCompanies() {
        return companyService.listarCompanies();
    }

    @GetMapping("/company")
    public ResponseEntity<Object> obtenerCompany(@RequestParam(name = "code") String code) {
        return companyService.obtenerCompany(code);
    }

    @PutMapping("/")
    public ResponseEntity<Object> editarCompany(@RequestParam(name = "code") String code,
            @RequestBody CompanyRequest companyRequest) {
        return companyService.editarCompany(code, companyRequest);
    }

    @DeleteMapping("/")
    public ResponseEntity<Object> eliminarCompany(@RequestParam(name = "code") String code) {
        return companyService.obtenerCompany(code);
    }

}
