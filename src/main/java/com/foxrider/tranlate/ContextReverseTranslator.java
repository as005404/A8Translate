package com.foxrider.tranlate;

import com.foxrider.controller.TranslateController;
import com.foxrider.model.response.ContextReverseResponse;

public class ContextReverseTranslator {

    public String getTranslationForText(String langFrom, String langTo, String textToTranslate) {
        ContextReverseResponse response = new TranslateController(langFrom, langTo, textToTranslate).postForObject();
        return "";
    }
}
