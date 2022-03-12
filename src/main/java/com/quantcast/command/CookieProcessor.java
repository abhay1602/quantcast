package com.quantcast.command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class CookieProcessor {

    private static CookieProcessor cookieProcessor = null;
    private static final String PATTERN = "yyyy-MM-dd'T'hh:mm:ssZ";

    private CookieProcessor() {

    }

    public static CookieProcessor getProcessCookie() {
        if (cookieProcessor == null) {
            synchronized (CookieProcessor.class) {
                if (cookieProcessor == null) {
                    cookieProcessor = new CookieProcessor();
                }
            }
        }
        return cookieProcessor;
    }

    public synchronized List<Cookie> getLatestCookie(String fileName, Date date) {
        List<Cookie> cookieList = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(fileName))) {
            String str;
            while ((str = in.readLine()) != null) {
                String[] record = str.split(",");
                if (record[1].equals("timestamp")) {
                    continue;
                }
                DateFormat df = new SimpleDateFormat(PATTERN);
                df.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date result = df.parse(record[1]);
                Cookie cookie = getCookie(record[0], result);
                cookieList.add(cookie);
            }
        } catch (IOException | ParseException e) {
            System.out.println("File Read Error");
        }
        return cookieList.stream()
                .filter(u -> compareDate(u.getDate(), date))
                .collect(Collectors.toList());
    }

    private Cookie getCookie(String cookieName, Date result) {
        Cookie cookie = new Cookie();
        cookie.setCookieName(cookieName);
        cookie.setDate(result);
        return cookie;
    }

    private boolean compareDate(Date date1, Date date2) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));

    }
}
