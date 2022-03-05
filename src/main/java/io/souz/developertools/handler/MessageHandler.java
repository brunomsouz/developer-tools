package io.souz.developertools.handler;

import java.util.Locale;
import java.util.ResourceBundle;

public class MessageHandler {

    public static String getMessageForLocale(String messageKey) {
        return ResourceBundle.getBundle("messages", Locale.getDefault()).getString(messageKey);
    }

}
