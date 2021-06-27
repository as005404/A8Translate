package com.foxrider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxrider.model.request.ContextReverseRequest;
import com.foxrider.model.response.ContextReverseResponse;
import com.foxrider.settings.AppSettingsState;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class TranslateController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TranslateController.class);

    private ObjectMapper mapper = new ObjectMapper();
    AppSettingsState settings = AppSettingsState.getInstance();
    private static String REVERSE_CONTEXT_API_URL = "https://api.reverso.net/translate/v1/translation";
    private static String HOST_URL = "api.reverso.net";
    private String translateLanguageFrom;
    private String translateLanguageInto;
    private String textToTranslate;

    public TranslateController(String translateLanguageFrom, String translateLanguageInto, String textToTranslate) {
        this.translateLanguageFrom = translateLanguageFrom;
        this.translateLanguageInto = translateLanguageInto;
        this.textToTranslate = textToTranslate;
    }

    public ContextReverseResponse postForObject() {
        ContextReverseRequest request = new ContextReverseRequest(translateLanguageFrom, translateLanguageInto, textToTranslate);
        try {
            HttpClient client = HttpClients.custom().build();
            String json = mapper.writeValueAsString(request);
            HttpUriRequest httpPost = RequestBuilder.post()
                    .setUri(REVERSE_CONTEXT_API_URL)
                    .setHeader(HttpHeaders.USER_AGENT, settings.userAgent)
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                    .setHeader(HttpHeaders.ACCEPT, "*/*")
                    .setHeader(HttpHeaders.HOST,HOST_URL)
                    .setHeader(HttpHeaders.CONNECTION, "keep-alive")
                    .setEntity(new StringEntity(json))
                    .build();

            HttpResponse response = client.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, "utf-8");
            EntityUtils.consume(httpEntity);

            return mapper.readValue(content, ContextReverseResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("Error while mapping to objecct" + e);
        }
        return null;
    }
}
