package com.foxrider.utils;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CamelCaseUtilsTest {

    @Test
    public void dividePascalCaseIntoLowerCaseWordsTest() {
        CamelCaseUtils utils = new CamelCaseUtils("DivideCamelCaseIntoLowerCaseWords");
        String s = utils.divideCamelCaseIntoLowerCaseWordsOrDefault();
        System.out.println(s);
    }

    @Test
    public void divideCamelCaseIntoLowerCaseWordsTest() {
        CamelCaseUtils utils = new CamelCaseUtils("divideCamelCaseIntoLowerCaseWords");
        String s = utils.divideCamelCaseIntoLowerCaseWordsOrDefault();
        System.out.println(s);
    }

    @Test
    public void isCamelCaseTest() {
        CamelCaseUtils utils = new CamelCaseUtils("xmlHttpRequest");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("newCustomerId");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("innerStopwatch");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("supportsIpv6OnIos");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("youTubeImporter");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("youtubeImporter");
        assertTrue(utils.isCamelCase());

        utils.setSelectedText("XmlHttpRequest");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("NewCustomerId");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("InnerStopwatch");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("SupportsIpv6OnIos");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("YouTubeImporter");
        assertTrue(utils.isCamelCase());
        utils.setSelectedText("YoutubeImporter");
        assertTrue(utils.isCamelCase());
    }
}