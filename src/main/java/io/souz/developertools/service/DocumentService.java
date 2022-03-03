package io.souz.developertools.service;

import io.souz.developertools.model.Document;

public interface DocumentService {

    Document generateDocument();

    Boolean validateDocument(Document document);

}
