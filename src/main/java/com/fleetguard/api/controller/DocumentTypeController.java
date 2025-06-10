package com.fleetguard.api.controller;

import com.fleetguard.api.model.DocumentType;
import com.fleetguard.api.repository.DocumentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = {"http://localhost:3000", "https://front-fleet-guard360.vercel.app"})

@RestController
public class DocumentTypeController {
    @Autowired
    final private DocumentTypeRepository repository;

    DocumentTypeController(DocumentTypeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/api/doc-type")
    public @ResponseBody Iterable <DocumentType> getAllDocumentType(){
        return repository.findAll();
    }
}
