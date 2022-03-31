package io.souz.developertools.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale;
import java.util.ResourceBundle;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MessageFormatterUtil {

    public static String getMessageForLocale(String messageKey) {
        return ResourceBundle.getBundle("messages", Locale.getDefault()).getString(messageKey);
    }

}
