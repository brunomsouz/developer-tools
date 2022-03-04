package io.souz.developertools.service.impl;

import io.souz.developertools.model.Document;
import io.souz.developertools.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ConsumerDocumentService implements DocumentService {

    @Override
    public Document generateDocument() {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 9; i++) {
            builder.append(random.nextInt(10));
        }

        builder.append(this.generateFirstVerifier(builder.toString()));
        builder.append(this.generateSecondVerifier(builder.toString()));

        return new Document()
                .documentNumber(builder.toString());
    }

    @Override
    public Boolean validateDocument(Document document) {
        String documentNumber = document.getDocumentNumber();

        if (documentNumber.matches("(\\d)\\1{10}")) {
            return false;
        }

        int firstVerifier = this.generateFirstVerifier(documentNumber);
        int secondVerifier = this.generateSecondVerifier(documentNumber);

        return Character.getNumericValue(documentNumber.charAt(9)) == firstVerifier &&
                Character.getNumericValue(documentNumber.charAt(10)) == secondVerifier;
    }

    private int generateFirstVerifier(String document) {
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            sum += Character.getNumericValue(document.charAt(i)) * (10 - i);
        }

        return this.calculateVerifierFromSum(sum);
    }

    private int generateSecondVerifier(String document) {
        int sum = 0;

        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(document.charAt(i)) * (11 - i);
        }

        return this.calculateVerifierFromSum(sum);
    }

    private int calculateVerifierFromSum(int sum) {
        int verifier = (sum * 10) % 11;

        if (verifier > 9) {
            verifier = 0;
        }

        return verifier;
    }

}
