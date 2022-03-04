package io.souz.developertools.service.impl;

import io.souz.developertools.model.Document;
import io.souz.developertools.service.DocumentService;

import java.util.Random;

public class BusinessDocumentService implements DocumentService {

    @Override
    public Document generateDocument() {
        Random random = new Random();

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            builder.append(random.nextInt(10));
        }

        builder.append("0001");

        builder.append(this.generateFirstVerifier(this.reverseString(builder.toString())));
        builder.append(this.generateSecondVerifier(this.reverseString(builder.toString())));

        return new Document()
                .documentNumber(builder.toString());
    }

    @Override
    public Boolean validateDocument(Document document) {
        String documentNumber = document.getDocumentNumber();

        if (documentNumber.matches("(\\d)\\1{13}")) {
            return false;
        }

        int firstVerifier = this.generateFirstVerifier(this.reverseString(documentNumber.substring(0, 12)));
        int secondVerifier = this.generateSecondVerifier(this.reverseString(documentNumber.substring(0, 13)));

        return Character.getNumericValue(documentNumber.charAt(12)) == firstVerifier &&
                Character.getNumericValue(documentNumber.charAt(13)) == secondVerifier;
    }

    private int generateFirstVerifier(String document) {
        int sum = 0;

        for (int i = 0; i < 12; i++) {
            sum += Character.getNumericValue(document.charAt(i)) * (i + 2 < 10 ? i + 2 : i - 6);
        }

        return this.calculateVerifierFromSum(sum);
    }

    private int generateSecondVerifier(String document) {
        int sum = 0;

        for (int i = 0; i < 13; i++) {
            sum += Character.getNumericValue(document.charAt(i)) * (i + 2 < 10 ? i + 2 : i - 6);
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

    private String reverseString(String document) {
        return new StringBuilder(document).reverse().toString();
    }

}
