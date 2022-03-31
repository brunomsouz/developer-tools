package io.souz.developertools.facade;

import io.souz.developertools.model.Document;
import io.souz.developertools.service.DocumentService;
import io.souz.developertools.service.impl.DynamicDocumentService;
import org.springframework.stereotype.Component;

@Component
public class DocumentFacade {

    private final DynamicDocumentService dynamicDocumentService;

    public DocumentFacade(DynamicDocumentService dynamicDocumentService) {
        this.dynamicDocumentService = dynamicDocumentService;
    }

    public Document generateDocument(String documentType) {
        DocumentService service = this.dynamicDocumentService.getBean(documentType);

        return service.generateDocument();
    }

    public Boolean validateDocument(String documentType, Document document) {
        DocumentService service = this.dynamicDocumentService.getBean(documentType);

        return service.validateDocument(document);
    }

}
