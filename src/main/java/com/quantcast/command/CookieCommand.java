package com.quantcast.command;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import picocli.CommandLine;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CommandLine.Command(
        name = "./find"
)
public class CookieCommand implements Runnable {

    Logger log = Logger.getLogger(CookieCommand.class);

    @CommandLine.Option(names = "-f", description = " input cookie file name", required = true)
    public String fileName;

    @CommandLine.Option(names = "-d", description = " input date for cookie search", required = true)
    public Date date;


    @Override
    public void run() {
        if (fileName != null) {
            List<Cookie> cookieList = CookieProcessor.getProcessCookie().getLatestCookie(fileName, date);
            List<String> response = new ArrayList<>();
            if(cookieList.isEmpty()){
                log.info(" There is no active cookie for the given date" + date);
                return;
            }
            response.add(cookieList.get(0).getCookieName());
            compareAndResultCookies(cookieList, response);
            response.stream().forEach(System.out::println);
        }
    }

    private void compareAndResultCookies(List<Cookie> cookieList, List<String> response) {
        for (int i = 0; i < cookieList.size() - 1; i++) {
            if (cookieList.get(i).getDate().compareTo(cookieList.get(i + 1).getDate()) == 0) {
                response.add(cookieList.get(i + 1).getCookieName());
            }
        }
    }
}