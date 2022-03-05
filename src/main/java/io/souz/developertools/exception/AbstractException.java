package io.souz.developertools.exception;

import io.souz.developertools.handler.MessageHandler;

public abstract class AbstractException extends RuntimeException {

    public AbstractException(String message) {
        super(MessageHandler.getMessageForLocale(message));
    }

}
