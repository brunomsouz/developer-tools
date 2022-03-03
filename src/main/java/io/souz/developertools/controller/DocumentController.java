package io.souz.developertools.controller;

import io.souz.developertools.api.DocumentApi;
import io.souz.developertools.exception.BadRequestException;
import io.souz.developertools.model.Document;
import io.souz.developertools.service.DocumentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
public class DocumentController implements BaseController, DocumentApi {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    public ResponseEntity<Document> generateDocument(@PathVariable("documentType") String documentType) {
        return ResponseEntity.ok(documentService.generateDocument());
    }

    public ResponseEntity<Boolean> validateDocument(@PathVariable("documentType") String documentType, @RequestBody Document document) {
        if (isBlank(document.getDocument())) {
            throw new BadRequestException("Document is required");
        }

        if (!document.getDocument().matches("^([0-9]{11}|[0-9]{14})$")) {
            throw new BadRequestException("Document is invalid");
        }

        return ResponseEntity.ok(documentService.validateDocument(document));
    }

}
