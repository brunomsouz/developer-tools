package io.souz.developertools.controller;

import io.souz.developertools.exception.BadRequestException;
import io.souz.developertools.facade.DocumentFacade;
import io.souz.developertools.model.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static io.souz.developertools.TestScenarios.CPF_DOCUMENT_VALID;
import static io.souz.developertools.TestScenarios.getDocument;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentControllerTest {

    @Mock
    private DocumentFacade documentFacade;

    private DocumentController documentController;

    @BeforeEach
    public void setup() {
        this.documentController = new DocumentController(this.documentFacade);
    }

    @Test
    void shouldGenerateDocument() {
        when(this.documentFacade.generateDocument(anyString())).thenReturn(getDocument(CPF_DOCUMENT_VALID));

        ResponseEntity<Document> response = this.documentController.generateDocument("cpf");

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldValidateDocument() {
        when(this.documentFacade.validateDocument(anyString(), any())).thenReturn(true);

        ResponseEntity<Boolean> response = this.documentController.validateDocument("cpf", getDocument(CPF_DOCUMENT_VALID));

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenDocumentNumberIsNull() {
        Document document = getDocument(null);

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.documentController.validateDocument("cpf", document));

        assertEquals("Document cannot be blank or null", exception.getMessage());
    }

    @Test
    void shouldThrowBadRequestExceptionWhenDocumentNumberIsBlank() {
        Document document = getDocument("");

        BadRequestException exception = assertThrows(BadRequestException.class,
                () -> this.documentController.validateDocument("cpf", document));

        assertEquals("Document cannot be blank or null", exception.getMessage());
    }

}
