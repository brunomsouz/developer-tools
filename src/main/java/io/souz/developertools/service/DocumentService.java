package io.souz.developertools.service;

import io.souz.developertools.model.Document;

public interface DocumentService {

    Document generateDocument();

    boolean validateDocument(Document document);

}
