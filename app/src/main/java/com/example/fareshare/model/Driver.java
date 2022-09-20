package com.example.fareshare.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Driver {
    @SerializedName("d_id")
    @Expose
private int d_id;
    @SerializedName("d_name")
    @Expose
private String d_name;
    @SerializedName("d_email")
    @Expose
private String	d_email;
    @SerializedName("d_password")
    @Expose
private String d_password;
    @SerializedName("d_cnic")
    @Expose
private String d_cnic;
    @SerializedName("d_cellno")
    @Expose
private String d_cellno;
    @SerializedName("d_gender")
    @Expose
    private String d_gender;
    @SerializedName("d_vehicaltype")
    @Expose
    private String d_vehicaltype;
    @SerializedName("d_vehical_numplate")
    @Expose
    private String d_vehical_numplate;
    @SerializedName("d_licence")
    @Expose
    private String d_licence;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private int code;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("d_status")

    private String d_status;
    public Driver(){

    }
    public Driver(int d_id, String d_name, String d_email, String d_gender,String d_licence,
                  String d_cellno,String d_vehical_numplate,String d_vehicaltype,
                  String d_cnic, String message, int code, boolean error) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_email = d_email;
        this.d_cnic=d_cnic;
        this.d_cellno = d_cellno;
        this.d_gender = d_gender;
        this.d_licence=d_licence;
        this.d_vehicaltype=d_vehicaltype;
        this.d_vehical_numplate=d_vehical_numplate;
        this.message = message;
        this.code = code;
        this.error = error;
    }

    public Driver(int d_id, String d_name, String d_email, String d_cnic, String d_cellno, String d_vehicaltype, String d_vehical_numplate, String d_licence) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_email = d_email;
        this.d_cnic = d_cnic;
        this.d_cellno = d_cellno;
        this.d_vehicaltype = d_vehicaltype;
        this.d_vehical_numplate = d_vehical_numplate;
        this.d_licence = d_licence;
    }
    public Driver(int d_id, String d_name, String d_email, String d_cnic,
                  String d_cellno, String d_vehicaltype, String d_vehical_numplate, String d_licence,String d_status) {
        this.d_id = d_id;
        this.d_name = d_name;
        this.d_email = d_email;
        this.d_cnic = d_cnic;
        this.d_cellno = d_cellno;
        this.d_vehicaltype = d_vehicaltype;
        this.d_vehical_numplate = d_vehical_numplate;
        this.d_licence = d_licence;
        this.d_status=d_status;
    }



    public int getD_id() {
        return d_id;
    }

    public String getD_name() {
        return d_name;
    }

    public String getD_email() {
        return d_email;
    }

    public String getD_password() {
        return d_password;
    }

    public void setD_password(String d_password) {
        this.d_password = d_password;
    }

    public String getD_cnic() {
        return d_cnic;
    }

    public String getD_cellno() {
        return d_cellno;
    }

    public String getD_gender() {
        return d_gender;
    }

    public String getD_vehicaltype() {
        return d_vehicaltype;
    }

    public String getD_vehical_numplate() {
        return d_vehical_numplate;
    }

    public String getD_licence() {
        return d_licence;
    }

    public boolean isError() {
        return error;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    public boolean isStatus() {
        return error;
    }

    public String getD_status() {
        return d_status;
    }

    public void setD_status(String d_status) {
        this.d_status = d_status;
    }
}

