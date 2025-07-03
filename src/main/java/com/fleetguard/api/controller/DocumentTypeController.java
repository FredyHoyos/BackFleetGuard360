package com.fleetguard.api.controller;

import com.fleetguard.api.model.DocumentType;
import com.fleetguard.api.repository.DocumentTypeRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Document Type")
@RestController
public class DocumentTypeController {
    @Autowired
    final private DocumentTypeRepository repository;

    DocumentTypeController(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @Operation(
            summary = "Get all the document types",
            description = "Este endpoint retorna una lista con todos los tipos de documentos de identidad",
            security = @SecurityRequirement(name = "bearerAuth")
    )
    @GetMapping("/api/doc-type")
    public @ResponseBody Iterable <DocumentType> getAllDocumentType(){
        return repository.findAll();
    }
}
