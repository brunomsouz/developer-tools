package io.souz.developertools.exception;

import io.souz.developertools.util.MessageFormatterUtil;

public abstract class AbstractException extends RuntimeException {

    protected AbstractException(String message) {
        super(MessageFormatterUtil.getMessageForLocale(message));
    }

}
