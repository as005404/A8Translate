package com.foxrider.detect;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.foxrider.lang.ContextReverseLanguage;
import com.foxrider.settings.AppSettingsState;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DefaultLanguageDetector {

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
            log.error("Error while detect language. " + apiError);
            return ContextReverseLanguage.NOT_FOUND;
        }

    }
}
