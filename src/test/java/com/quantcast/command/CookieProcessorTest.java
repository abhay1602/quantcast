package com.quantcast.command;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

class CookieProcessorTest {

    private String inputFile;
    private String format;
    private CookieProcessor cookieProcessor;

    @BeforeEach
    public void setUp(){
        inputFile =  "/Users/abhaykolapkar/Downloads/quantcast/src/main/resources/cookies.csv";
        format = "yyyy-MM-dd";
        cookieProcessor = CookieProcessor.getProcessCookie();
    }
    @Test
    void getProcessCookieTest() throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Date cDate = df.parse("2018-12-09");
        List<Cookie> cookieList = cookieProcessor.getLatestCookie(inputFile, cDate);
        Assertions.assertEquals("AtY0laUfhglK3lC7", cookieList.get(0).getCookieName());
    }

    @Test
    void getProcessCookieForEmptyTest() throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Date cDate = df.parse("2021-12-09");
        List<Cookie> cookieList = cookieProcessor.getLatestCookie(inputFile, cDate);
        Assertions.assertEquals(0, cookieList.size());
    }
}