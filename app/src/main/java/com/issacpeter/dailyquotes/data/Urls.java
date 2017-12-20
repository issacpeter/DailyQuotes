package com.issacpeter.dailyquotes.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Admin on 25-10-2017.
 */

public class Urls {
    @SerializedName("custom")
    @Expose
    private String custom;

    public String getCustom() {
        return custom;
    }

    public void setCustom(String custom) {
        this.custom = custom;
    }
}
