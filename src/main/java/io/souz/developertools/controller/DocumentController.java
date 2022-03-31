package io.souz.developertools.controller;

import io.souz.developertools.api.DocumentApi;
import io.souz.developertools.exception.BadRequestException;
import io.souz.developertools.facade.DocumentFacade;
import io.souz.developertools.model.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
public class DocumentController implements BaseController, DocumentApi {

    private final DocumentFacade documentFacade;

    public DocumentController(DocumentFacade documentFacade) {
        this.documentFacade = documentFacade;
    }

    public ResponseEntity<Document> generateDocument(@PathVariable("documentType") String documentType) {
        return ResponseEntity.ok(documentFacade.generateDocument(documentType));
    }

    public ResponseEntity<Boolean> validateDocument(@PathVariable("documentType") String documentType, @RequestBody Document document) {
        if (isBlank(document.getDocumentNumber())) {
            throw new BadRequestException("1");
        }

        return ResponseEntity.ok(documentFacade.validateDocument(documentType, document));
    }

}
