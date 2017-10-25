package com.issacpeter.dailyquotes;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.issacpeter.dailyquotes.data.QuotesListResponse;
import com.issacpeter.dailyquotes.remote.QuotesService;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {

    QuotesListResponse q;
    String cat;
    int count;
    boolean isPlaying=false;
    QuotesService mService, mServiceBG;
    String quote, author, category;
    static int r, g, b;

    private TextView mContentView, mBottomContent;
    private ImageView mPlayButton, icon;
    private View mContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);
        isPlaying = true;
        q = new QuotesListResponse();
        cat = "famous";
        count = 1;
        mService = ApiUtils.getQuotesService();
        mServiceBG = ApiUtils.getQuotesBGService();

        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        // Delayed removal of status and navigation bar

        // Note that some of these constants are new as of API 16 (Jelly Bean)
        // and API 19 (KitKat). It is safe to use them, as they are inlined
        // at compile-time and do nothing on earlier devices.
        mContainer = findViewById(R.id.container);
        mContentView = (TextView) findViewById(R.id.fullscreen_content);
        mPlayButton = (ImageView) findViewById(R.id.play);
        icon = (ImageView) findViewById(R.id.icon);

        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        mBottomContent = (TextView) findViewById(R.id.bottom_content);

//        getQuotes(cat, count);
        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                toggle();
                getQuotes(cat, count);
                getQuotesBG();
            }
        });

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAndPause();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
    }


    public QuotesListResponse getQuotes(String cat, int count){

        mService.getQuotes(cat, count).enqueue(new Callback<QuotesListResponse>() {
            @Override
            public void onResponse(Call<QuotesListResponse> call, Response<QuotesListResponse> response) {
                if(response.isSuccessful()) {
                    icon.setVisibility(View.GONE);
                    quote = response.body().getQuote();
                    author = response.body().getAuthor();
                    mContentView.setText("\""+quote+"\"");
                    mBottomContent.setText("-"+author);
                    mContainer.setBackgroundColor(getColor());
                    mContentView.setTextColor(getContrastColor());
                    mBottomContent.setTextColor(getContrastColor());
                    /*
                    if (page==1) {
                        mAdapter.updateProducts(response.body().getData());
                    } else {
                        mAdapter.addProducts(response.body().getData());
                    }*/
                    Log.d("FullScreenActivity", "quotes loaded from API\n");
//                    Toast.makeText(FullscreenActivity.this, "quotes loaded from API", Toast.LENGTH_SHORT).show();

                }else {
                    int statusCode  = response.code();
                    Log.v("Notsuccessful response", response.toString());
                    Toast.makeText(FullscreenActivity.this, "Notsuccessful response", Toast.LENGTH_SHORT).show();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<QuotesListResponse> call, Throwable t) {

                Log.d("FullScreenActivity ", "error loading from API "+t);
                t.printStackTrace();
            }
        });
        return q;
    }

    public QuotesListResponse getQuotesBG(){

        mServiceBG.getQuotesBG().enqueue(new Callback<QuotesListResponse>() {
            @Override
            public void onResponse(Call<QuotesListResponse> call, Response<QuotesListResponse> response) {
                if(response.isSuccessful()) {
//                    icon.setVisibility(View.GONE);
//                    quote = response.body().getQuote();
//                    author = response.body().getAuthor();
//                    mContentView.setText("\""+quote+"\"");
//                    mBottomContent.setText("-"+author);
//                    mContainer.setBackgroundColor(getColor());
//                    mContentView.setTextColor(getContrastColor());
//                    mBottomContent.setTextColor(getContrastColor());
                    /*
                    if (page==1) {
                        mAdapter.updateProducts(response.body().getData());
                    } else {
                        mAdapter.addProducts(response.body().getData());
                    }*/
                    Log.d("FullScreenActivity BG", "quotes loaded from API\n"+response.body());
//                    Toast.makeText(FullscreenActivity.this, "quotes loaded from API", Toast.LENGTH_SHORT).show();

                }else {
                    int statusCode  = response.code();
                    Log.v("Notsuccessful  BG ", response.toString());
                    Toast.makeText(FullscreenActivity.this, "Notsuccessful response BG "+response, Toast.LENGTH_SHORT).show();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<QuotesListResponse> call, Throwable t) {

                Log.d("FullScreenActivity BG ", "error loading from API "+t);
                t.printStackTrace();
            }
        });
        return q;
    }

    public int getColor(){
        // generate the random integers for r, g and b value
        Random rand = new Random();
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);

        int randomColor = Color.rgb(r,g,b);
        return randomColor;
    }

    public static int getContrastColor() {
        double y = (299 * r + 587 * g + 114 * b) / 1000;
        return y >= 128 ? R.color.colorBlack : R.color.colorWhite;
    }

    public void playAndPause(){
        if (isPlaying) {
            mContentView.setClickable(false);
            mPlayButton.setImageResource(android.R.drawable.ic_media_play);
            isPlaying = false;
        }else {
            mContentView.setClickable(true);
            mPlayButton.setImageResource(android.R.drawable.ic_media_pause);
            isPlaying = true;
        }
    }
}
