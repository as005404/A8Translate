package com.foxrider.tranlate;

import com.foxrider.controller.TranslateController;
import com.foxrider.model.response.ContextReverseResponse;
import com.foxrider.parser.ContextReverseResponseParser;
import com.foxrider.parser.ResponseParser;

import java.util.List;

public class ContextReverseTranslator {

    public List<String> getTranslationForText(String langFrom, String langTo, String textToTranslate) {
        ContextReverseResponse response = new TranslateController(langFrom, langTo, textToTranslate).postForObject();
        ResponseParser parser = new ContextReverseResponseParser(response);
        return parser.parse();
    }
}
