package com.foxrider.parser;

import org.apache.http.client.fluent.Response;

public interface ResponseParser {

    String parse(Response response);
}
