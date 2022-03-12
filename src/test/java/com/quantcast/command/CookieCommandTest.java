package com.quantcast.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


class CookieCommandTest {

    private CookieCommand command;

    @BeforeEach
    public void setUp() throws ParseException {
        command = new CookieCommand();
        command.fileName = "/Users/abhaykolapkar/Downloads/quantcast/src/main/resources/cookies.csv";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date cDate = df.parse("2018-12-09");
        command.date = cDate;
    }

    @Test
    void runTest() throws Exception {

        CookieProcessor cookieProcessor = Mockito.mock(CookieProcessor.class);
        command.run();
        Mockito.verify(cookieProcessor, Mockito.times(0)).getLatestCookie(command.fileName, command.date);
    }

    @Test
    void noActiveCookieTest() throws Exception {

        CookieProcessor cookieProcessor = Mockito.mock(CookieProcessor.class);
        command.date = new Date();
        command.run();
        Mockito.verify(cookieProcessor, Mockito.times(0)).getLatestCookie(command.fileName, command.date);
    }
}