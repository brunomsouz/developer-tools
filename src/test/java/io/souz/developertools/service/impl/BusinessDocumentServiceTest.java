package io.souz.developertools.service.impl;

import io.souz.developertools.model.Document;
import io.souz.developertools.service.DocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.souz.developertools.TestScenarios.*;
import static org.junit.jupiter.api.Assertions.*;

public class BusinessDocumentServiceTest {

    private DocumentService documentService;

    @BeforeEach
    public void setup() {
        this.documentService = new BusinessDocumentService();
    }

    @Test
    public void shouldGenerateValidDocument() {
        Document document = this.documentService.generateDocument();

        assertEquals(14, document.getDocumentNumber().length());
        assertTrue(this.documentService.validateDocument(document));
    }

    @Test
    public void shouldValidateDocumentAndReturnTrueWhenValid() {
        Boolean isValid = this.documentService.validateDocument(getDocument(CNPJ_DOCUMENT_VALID));

        assertTrue(isValid);
    }

    @Test
    public void shouldValidateDocumentAndReturnFalseWhenInvalid() {
        Boolean isValid = this.documentService.validateDocument(getDocument(CNPJ_DOCUMENT_INVALID));

        assertFalse(isValid);
    }


}
