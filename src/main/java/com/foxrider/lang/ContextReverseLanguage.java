package com.foxrider.lang;

import java.util.Arrays;

/**
 * Enum that represents languages sent to context reverso
 */
public enum ContextReverseLanguage {
    ENGLISH("eng", "en"),
    FRENCH("fra", "fr"),
    GERMAN("ger", "de"),
    RUSSIAN("rus", "ru"),
    ITALIAN("ita", "it"),
    POLISH("pol", "pl"),
    SPANISH("spa", "es"),
    NOT_FOUND("notfound", "not_found");

    public String lang;
    public String detectedLang;

    ContextReverseLanguage(String lang, String detectedLang) {
        this.lang = lang;
        this.detectedLang = detectedLang;
    }

    public static ContextReverseLanguage valueFor(String detectedLang) {
        return Arrays.stream(ContextReverseLanguage.values())
                .filter(ts -> ts.detectedLang.equalsIgnoreCase(detectedLang))
                .findFirst().orElse(ContextReverseLanguage.NOT_FOUND);
    }

}
