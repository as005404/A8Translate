package com.foxrider.detect;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.foxrider.lang.ContextReverseLanguage;
import com.foxrider.settings.AppSettingsState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class LanguageDetection{
    private static final Logger LOGGER = LoggerFactory.getLogger(LanguageDetection.class);

    public static ContextReverseLanguage detectLanguage(String text) {
        AppSettingsState settings = AppSettingsState.getInstance();
        DetectLanguage.apiKey = settings.detectionLanguageApiKey;

        try {
            List<Result> detectList = DetectLanguage.detect(text);
            return detectList.stream()
                    .filter(x -> x.isReliable)
                    .map(x -> x.language)
                    .findFirst()
                    .map(ContextReverseLanguage::valueFor)
                    .orElse(ContextReverseLanguage.NOT_FOUND);

        } catch (APIError apiError) {
            LOGGER.error("Error while detect language. " + apiError);
            return ContextReverseLanguage.NOT_FOUND;
        }

    }
}
