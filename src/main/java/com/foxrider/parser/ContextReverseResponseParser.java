package com.foxrider.parser;

import com.foxrider.model.response.ContextResults;
import com.foxrider.model.response.ContextReverseResponse;
import com.foxrider.model.response.Result;
import com.google.common.base.Strings;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Parse ContextReverseResponse to lines of text with \n
 */
@Slf4j
@Getter
@Setter
@AllArgsConstructor
public class ContextReverseResponseParser implements ResponseParser {

    private final static Integer TRANSLATION_LIMIT = 5;
    private ContextReverseResponse response;

    @Override
    public String parse() {
        StringBuilder builder = new StringBuilder();

        // response may be empty
        if (response == null || response.getTranslation() == null || response.getTranslation().size() == 0) {
            log.warn("ContextReverseResponseParser. Response from context reverso is null or there is no tranlations");
            return builder.toString();
        }

        List<String> translate = new ArrayList<>(response.getTranslation());

        // results may be empty
        ContextResults results = response.getContextResults();
        if (results != null && results.getResults() != null && results.getResults().size() != 0) {
            List<String> fromResults = results.getResults()
                    .stream()
                    .map(Result::getTranslation)
                    .collect(Collectors.toList());

            translate.addAll(fromResults);
        }

        translate.stream()
                .map(String::toLowerCase)
                .distinct()
                .filter(x -> !Strings.isNullOrEmpty(x))
                .limit(TRANSLATION_LIMIT)
                .forEach(x -> builder.append(x).append("\n"));

        return builder.toString();
    }
}
