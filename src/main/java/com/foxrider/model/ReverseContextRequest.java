package com.foxrider.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model that represents request that come to context reverso
 */

public class ReverseContextRequest {
    @JsonProperty("from")
    private String translateFrom;
    @JsonProperty("to")
    private String translateTo;
    @JsonProperty("input")
    private String textToTranslate;
    private String format;
    private Options options;


    public ReverseContextRequest() {}

    public ReverseContextRequest(String translateFrom, String translateTo, String textToTranslate, String format) {
        this.translateFrom = translateFrom;
        this.translateTo = translateTo;
        this.textToTranslate = textToTranslate;
        this.format = format;
        this.options = new Options();
    }

    public String getTranslateFrom() {
        return translateFrom;
    }

    public void setTranslateFrom(String translateFrom) {
        this.translateFrom = translateFrom;
    }

    public String getTranslateTo() {
        return translateTo;
    }

    public void setTranslateTo(String translateTo) {
        this.translateTo = translateTo;
    }

    public String getTextToTranslate() {
        return textToTranslate;
    }

    public void setTextToTranslate(String textToTranslate) {
        this.textToTranslate = textToTranslate;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    static class Options{
        public boolean contextResults = true;
        public boolean languageDetection = true;
        public String origin = "reversomobile";
        public boolean sentenceSplitter = false;
    }
}
