package com.foxrider.parser;

import com.foxrider.model.response.ContextReverseResponse;
import com.foxrider.model.response.Result;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//todo: make camelCase into normal text
// make reverse translation from one prefference language to another(core language)
public class ContextReverseResponseParser implements ResponseParser {
    private final static Integer TRANSLATION_LIMIT = 7;
    @Override
    public String parse(ContextReverseResponse response) {
        StringBuilder builder = new StringBuilder();
        List<String> translate = new ArrayList<>(response.getTranslation());

        List<String> fromResults = response.getContextResults().getResults()
                .stream()
                .map(Result::getTranslation)
                .collect(Collectors.toList());

        translate.addAll(fromResults);

        translate.stream()
                .map(String::toLowerCase)
                .distinct()
                .filter(x -> !Strings.isNullOrEmpty(x))
                .limit(TRANSLATION_LIMIT)
                .forEach(x -> builder.append(x).append("\n"));

        return builder.toString();
    }
}
