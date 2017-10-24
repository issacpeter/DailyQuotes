package com.issacpeter.dailyquotes;


import com.issacpeter.dailyquotes.remote.QuotesService;
import com.issacpeter.dailyquotes.remote.RetrofitClient;


/**
 * Created by MyPc on 26-04-2017.
 */

public class ApiUtils {

    public static final String BASE_URL = "https://andruxnet-random-famous-quotes.p.mashape.com";

    public static QuotesService getQuotesService() {
        return RetrofitClient.getClient(BASE_URL).create(QuotesService.class);
    }
}