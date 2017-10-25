package com.issacpeter.dailyquotes.remote;

/**
 * Created by MyPc on 26-04-2017.
 */

import com.issacpeter.dailyquotes.data.QuotesListResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface QuotesService {


    @Headers({"Accept: application/json",
            "X-Mashape-Key: YXt5iGQU5YmshgCX6pLJywhjM69hp1ezkHHjsnGGBX82z4H6xe"})
    @GET("/")
    Call<QuotesListResponse> getQuotes(@Query("cat") String cat, @Query("count") int count);


    @Headers({"Accept: application/json",
    "Accept-Version: v1"})
    @GET("/photos/random")
    Call<QuotesListResponse> getQuotesBG(@Query("client_id") String client_id, @Query("w") int w, @Query("h") int h);

}