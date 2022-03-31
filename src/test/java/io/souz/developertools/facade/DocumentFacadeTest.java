package io.souz.developertools.facade;

import io.souz.developertools.model.Document;
import io.souz.developertools.service.impl.ConsumerDocumentService;
import io.souz.developertools.service.impl.DynamicDocumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static io.souz.developertools.TestScenarios.CPF_DOCUMENT_VALID;
import static io.souz.developertools.TestScenarios.getDocument;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DocumentFacadeTest {

    @Mock
    private DynamicDocumentService dynamicDocumentService;

    private DocumentFacade documentFacade;

    @BeforeEach
    public void setup() {
        this.documentFacade = new DocumentFacade(this.dynamicDocumentService);
    }

    @Test
    void shouldGenerateDocument() {
        when(this.dynamicDocumentService.getBean(anyString())).thenReturn(new ConsumerDocumentService());

        Document document = this.documentFacade.generateDocument("cpf");

        assertNotNull(document);
        assertNotNull(document.getDocumentNumber());
    }

    @Test
    void shouldValidateDocument() {
        when(this.dynamicDocumentService.getBean(anyString())).thenReturn(new ConsumerDocumentService());

        Boolean isValid = this.documentFacade.validateDocument("cpf", getDocument(CPF_DOCUMENT_VALID));

        assertNotNull(isValid);
    }

}