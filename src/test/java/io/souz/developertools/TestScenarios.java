package io.souz.developertools;

import io.souz.developertools.model.Document;

public class TestScenarios {

    public static final String CPF_DOCUMENT_VALID = "11122233396";
    public static final String CPF_DOCUMENT_INVALID = "11111111111";
    public static final String CNPJ_DOCUMENT_VALID = "11222333000181";
    public static final String CNPJ_DOCUMENT_INVALID = "11111111111111";

    public static Document getDocument(String document) {
        return new Document()
                .documentNumber(document);
    }

}
