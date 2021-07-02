package com.foxrider.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model that represents request that come to context reverso
 */
@Getter
@Setter
@NoArgsConstructor
public class ContextReverseRequest {
    @JsonProperty("from")
    private String translateFrom;
    @JsonProperty("to")
    private String translateTo;
    @JsonProperty("input")
    private String textToTranslate;
    private String format;
    private Options options;

    public ContextReverseRequest(String translateFrom, String translateTo, String textToTranslate) {
        this.translateFrom = translateFrom;
        this.translateTo = translateTo;
        this.textToTranslate = textToTranslate;
        this.format = "text";
        this.options = new Options();
    }

    /**
     * This class is static but it is needed in request to ContextReverso
     */
    static class Options {
        public boolean contextResults = true;
        public boolean languageDetection = true;
        public String origin = "reversomobile";
        public boolean sentenceSplitter = false;
    }
}
