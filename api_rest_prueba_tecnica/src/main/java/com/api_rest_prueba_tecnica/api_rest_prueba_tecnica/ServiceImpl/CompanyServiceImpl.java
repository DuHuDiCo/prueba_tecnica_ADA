package com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Dto.CompanyRequest;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Exceptions.Message;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Funtions.Functions;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models.Application;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models.Company;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models.Version;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Models.VersionCompany;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Repository.CompanyRepository;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Repository.VersionCompanyRepository;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Repository.VersionRepository;
import com.api_rest_prueba_tecnica.api_rest_prueba_tecnica.Service.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final Functions functions;
    private final VersionRepository versionRepository;

    private final VersionCompanyRepository versionCompanyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository, Functions functions,
            VersionRepository versionRepository, VersionCompanyRepository versionCompanyRepository) {
        this.companyRepository = companyRepository;
        this.functions = functions;
        this.versionRepository = versionRepository;
        this.versionCompanyRepository = versionCompanyRepository;
    }

    @Override
    public ResponseEntity<Object> guardarCompany(CompanyRequest companyRequest) {

        // validar si la compañia existe
        Company company = companyRepository.findByCodigoCompany(companyRequest.getCodigoCompany());
        if (Objects.nonNull(company)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("COMPANY EXISTENTE EN BASE DE DATOS"));
        }

        // crear una nueva compañia
        company = new Company(
                functions.mathRamdomId(), companyRequest.getCodigoCompany(), companyRequest.getNameCompany(),
                companyRequest.getDescriptionCompany());

        company = companyRepository.save(company);

        // validar si el objeto de version request no viene vacio
        if (Objects.isNull(companyRequest.getVersionRequest())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("VERSION NO AGREGADA"));
        }

        // creamos la version
        Version version = new Version(functions.mathRamdomId(), companyRequest.getVersionRequest().getVersion(),
                companyRequest.getVersionRequest().getVersionDescription());

        // validamos si el objeto de application existe en la peticion
        if (Objects.isNull(companyRequest.getVersionRequest().getApplicationRequest())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("DATOS DE APLICACION NO AGREGADA"));
        }

        // creamos la apliccion

        Application application = new Application(
                functions.mathRamdomId(), companyRequest.getVersionRequest().getApplicationRequest().getAppCode(),
                companyRequest.getVersionRequest().getApplicationRequest().getAppName(),
                companyRequest.getVersionRequest().getApplicationRequest().getAppDescription());

        version.agregarAplicacion(application);

        version = versionRepository.save(version);

        // creamos version company que es la tabla relacionada
        VersionCompany versionCompany = new VersionCompany();
        versionCompany.agregarVersionCompany(company, version);
        versionCompany.setVersionCompanyDescription(companyRequest.getVersionCompanyDescription());

        versionCompany = versionCompanyRepository.save(versionCompany);

        return ResponseEntity.status(HttpStatus.OK).body(versionCompany);

    }

    @Override
    public ResponseEntity<Object> listarCompanies() {
        // listar todas las compañias existentes
        List<Company> companies = CollectionUtils.isEmpty(companyRepository.findAll()) ? new ArrayList<>()
                : companyRepository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(companies);
    }

    @Override
    public ResponseEntity<Object> obtenerCompany(String codeCompany) {

        /// obtenemos una compañia por el codigo de compañia enviado en la peticion
        Company company = companyRepository.findByCodigoCompany(codeCompany);
        if (Objects.isNull(company)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("COMPANY NO ENCONTRADA"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(company);
    }

    @Override
    public ResponseEntity<Object> eliminarCompany(String codeCompany) {
        // validamos si existe una compañia a eliminar
        Company company = companyRepository.findByCodigoCompany(codeCompany);
        if (Objects.isNull(company)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("COMPANY NO ENCONTRADA"));
        }

        // si existe la eliminamos
        companyRepository.delete(company);
        return ResponseEntity.status(HttpStatus.OK).body(new Message("COMPANY ELIMINDAD"));
    }

    @Override
    public ResponseEntity<Object> editarCompany(String codeCompany, CompanyRequest companyRequest) {
        // validar si la compañia existe
        Company company = companyRepository.findByCodigoCompany(codeCompany);
        if (Objects.isNull(company)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("COMPANY NO ENCONTRADA"));
        }

        // validamos si viene presente el objeto de versions
        if (Objects.isNull(companyRequest.getVersionRequest())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("VERSION NO AGREGADA"));
        }

        // validamos si ya existe uan version equivalente a la version enviada en la
        // peticion
        VersionCompany versionCompany = company.getVersionCompanies().stream()
                .filter(vc -> vc.getVersion().getVersion().equals(companyRequest.getVersionRequest().getVersion()))
                .findFirst().orElse(null);

        // si no existe creamos una nueva
        if (Objects.isNull(versionCompany)) {

            if (Objects.isNull(companyRequest.getVersionRequest())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Message("VERSION NO AGREGADA"));
            }

            Version version = new Version(functions.mathRamdomId(), companyRequest.getVersionRequest().getVersion(),
                    companyRequest.getVersionRequest().getVersionDescription());

            if (Objects.isNull(companyRequest.getVersionRequest().getApplicationRequest())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new Message("DATOS DE APLICACION NO AGREGADA"));
            }
            Application application = new Application(
                    functions.mathRamdomId(), companyRequest.getVersionRequest().getApplicationRequest().getAppCode(),
                    companyRequest.getVersionRequest().getApplicationRequest().getAppName(),
                    companyRequest.getVersionRequest().getApplicationRequest().getAppDescription());

            version.agregarAplicacion(application);

            version = versionRepository.save(version);

            VersionCompany versionCompanyNew = new VersionCompany();
            versionCompanyNew.agregarVersionCompany(company, version);
            versionCompanyNew.setVersionCompanyDescription(companyRequest.getVersionCompanyDescription());

            versionCompanyNew = versionCompanyRepository.save(versionCompanyNew);

        }

        // si existe actalizamos los datos con los de la peticion
        Version version = versionCompany.getVersion();
        version.setVersion(companyRequest.getVersionRequest().getVersion());
        version.setVersionDescription(companyRequest.getVersionRequest().getVersionDescription());

        if (Objects.isNull(companyRequest.getVersionRequest().getApplicationRequest())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Message("DATOS DE APLICACION NO AGREGADA"));
        }
        Application application = version.getApplication();
        application.setAppCode(companyRequest.getVersionRequest().getApplicationRequest().getAppCode());
        application.setAppDescription(companyRequest.getVersionRequest().getApplicationRequest().getAppDescription());
        application.setAppName(companyRequest.getVersionRequest().getApplicationRequest().getAppName());

        version = versionRepository.save(version);

        versionCompany.setVersionCompanyDescription(companyRequest.getVersionCompanyDescription());

        versionCompany = versionCompanyRepository.save(versionCompany);

        return ResponseEntity.status(HttpStatus.OK).body(versionCompany);
    }

}
