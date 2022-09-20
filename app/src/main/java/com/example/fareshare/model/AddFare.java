package com.example.fareshare.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFare {
    @SerializedName("base_fare")
    @Expose
    private int base_fare;
    @SerializedName("km_fare")
    @Expose
    private int km_fare;
    @SerializedName("message")
    @Expose
    private String message;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;


    public AddFare(int base_fare) {
        this.base_fare = base_fare;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setKm_fare(int km_fare) {
        this.km_fare = km_fare;
    }

    public void setBase_fare(int base_fare) {
        this.base_fare = base_fare;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public int getKm_fare() {
        return km_fare;
    }

    public int getBase_fare() {
        return base_fare;
    }

    public void isStatus() {
    }
}
