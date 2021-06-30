package com.foxrider.parser;

import com.foxrider.model.response.ContextResults;
import com.foxrider.model.response.ContextReverseResponse;
import com.foxrider.model.response.Result;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ContextReverseResponseParser implements ResponseParser {
    private final static Integer TRANSLATION_LIMIT = 5;

    @Override
    public String parse(ContextReverseResponse response) {
        StringBuilder builder = new StringBuilder();
        List<String> translate = new ArrayList<>(response.getTranslation());

        // results may be empty
        ContextResults results = response.getContextResults();
        if (results != null && results.getResults().size() != 0) {
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
