package com.foxrider.parser;

import com.foxrider.model.response.ContextReverseResponse;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContextReverseResponseParserTest {

    @Test
    public void parse() {
        ContextReverseResponseParser parser = new ContextReverseResponseParser(new ContextReverseResponse());
        System.out.println(parser.parse());
    }

}