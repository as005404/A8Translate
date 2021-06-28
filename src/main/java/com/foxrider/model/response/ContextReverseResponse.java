package com.foxrider.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
/**
 * Model that represents response that come from context reverso
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextReverseResponse {
    private String id;
    private String from;
    private String to;
    private List<String> input;
    private String correctedText;
    private List<String> translation;
    private List<String> engines;
    private LanguageDetection languageDetection;
    private ContextResults contextResults;
    private Boolean truncated;
    private Integer timeTaken;
}
