package com.foxrider.parser;

import com.foxrider.model.response.ContextReverseResponse;

public interface ResponseParser {

    String parse(ContextReverseResponse response);
}
