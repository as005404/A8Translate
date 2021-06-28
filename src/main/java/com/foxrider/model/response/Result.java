package com.foxrider.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result {
    private String translation;
    private List<String> sourceExamples;
    private List<String> targetExamples;
    private Boolean rude;
    private Boolean colloquial;
    private Object partOfSpeech;
}
