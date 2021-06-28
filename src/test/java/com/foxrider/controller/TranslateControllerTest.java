package com.foxrider.controller;

import com.foxrider.lang.ContextReverseLanguage;
import com.foxrider.model.response.ContextReverseResponse;
import org.junit.Test;

public class TranslateControllerTest {

    @Test
    public void postForObject() {
        TranslateController controller = new TranslateController(ContextReverseLanguage.ENGLISH.lang,
                ContextReverseLanguage.RUSSIAN.lang, "I'm in trouble");
        ContextReverseResponse contextReverseResponse = controller.postForObject();
    }
}