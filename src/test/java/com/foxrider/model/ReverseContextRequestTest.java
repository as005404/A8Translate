package com.foxrider.model;

import com.detectlanguage.DetectLanguage;
import com.detectlanguage.Result;
import com.detectlanguage.errors.APIError;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.List;

public class ReverseContextRequestTest{
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testee() throws JsonProcessingException, APIError {
        DetectLanguage.apiKey = "ccebf2c1e108048a34061dc5cec6613c";
        List<Result> krieg = DetectLanguage.detect("Krieg");
        System.out.printf(krieg.toString());

    }
}