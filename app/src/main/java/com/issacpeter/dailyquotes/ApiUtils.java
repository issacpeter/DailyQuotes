package com.issacpeter.dailyquotes;


import com.issacpeter.dailyquotes.remote.QuotesService;
import com.issacpeter.dailyquotes.remote.RetrofitClient;


/**
 * Created by MyPc on 26-04-2017.
 */

public class ApiUtils {

    public static final String BASE_URL_QUOTES = "https://andruxnet-random-famous-quotes.p.mashape.com";

    public static QuotesService getQuotesService() {
        return RetrofitClient.getClient(BASE_URL_QUOTES).create(QuotesService.class);
    }
    public static final String BASE_URL_BG = "https://api.unsplash.com/";

    public static QuotesService getQuotesBGService() {
        return RetrofitClient.getClient(BASE_URL_BG).create(QuotesService.class);
    }
}