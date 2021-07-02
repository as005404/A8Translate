package com.foxrider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foxrider.model.request.ContextReverseRequest;
import com.foxrider.model.response.ContextReverseResponse;
import com.foxrider.settings.AppSettingsState;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class TranslateController {

    private static final String REVERSE_CONTEXT_API_URL = "https://api.reverso.net/translate/v1/translation";
    private static final String HOST_URL = "api.reverso.net";

    private final ObjectMapper mapper = new ObjectMapper();
    private final AppSettingsState settings = AppSettingsState.getInstance();

    private final String translateLanguageFrom;
    private final String translateLanguageInto;
    private final String textToTranslate;

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
                    .setHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8")
                    .setHeader(HttpHeaders.HOST, HOST_URL)
                    .setHeader(HttpHeaders.CONNECTION, "keep-alive")
                    .setEntity(new StringEntity(json, StandardCharsets.UTF_8))
                    .build();

            HttpResponse response = client.execute(httpPost);

            HttpEntity httpEntity = response.getEntity();
            String content = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);
            EntityUtils.consume(httpEntity);

            return mapper.readValue(content, ContextReverseResponse.class);
        } catch (IOException e) {
            log.error("Error while mapping to objecct" + e);
        }
        return null;
    }
}
