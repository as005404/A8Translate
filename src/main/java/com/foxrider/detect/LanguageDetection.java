package com.foxrider.detect;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.foxrider.lang.ContextReverseLanguage;
import com.foxrider.settings.AppSettingsState;

import java.util.List;

public class LanguageDetection {
    public static ContextReverseLanguage detectLanguage(String text) throws APIError {
        AppSettingsState settings = AppSettingsState.getInstance();
        DetectLanguage.apiKey = settings.detectionLanguageApiKey;
        List<Result> detectList = DetectLanguage.detect(text);
        return detectList.stream()
                .filter(x -> x.isReliable)
                .map(x -> x.language)
                .findFirst()
                .map(ContextReverseLanguage::valueFor)
                .orElse(ContextReverseLanguage.NOT_FOUND);
    }
}
