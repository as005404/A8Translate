package com.foxrider.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LanguageDetection {
    private String detectedLanguage;
    private Boolean isDirectionChanged;
    private String originalDirection;
    private Integer originalDirectionContextMatches;
    private Integer changedDirectionContextMatches;
    private Integer timeTaken;
}
