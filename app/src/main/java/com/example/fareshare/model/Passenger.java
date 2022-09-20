package com.example.fareshare.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Passenger {

    @SerializedName("p_id")
    @Expose
    private int p_id;
    @SerializedName("p_name")
    @Expose
    private String p_name;
    @SerializedName("p_email")
    @Expose
    private String p_email;
    @SerializedName("p_password")
    @Expose
    private String p_password;
    @SerializedName("p_gender")
    @Expose
    private String p_gender;
    @SerializedName("p_cell_no")
    @Expose
    private String p_cellno;
    @SerializedName("p_status")
    @Expose
    private String p_status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;
    public Passenger()
    {

    }

    public Passenger( int p_id,String p_name, String p_email, String p_gender,
                     String p_cellno, String p_status, String message, int code, boolean error) {
       this.p_id=p_id;
        this.p_name = p_name;
        this.p_email = p_email;
        this.p_gender = p_gender;
        this.p_cellno = p_cellno;
        this.p_status=p_status;
        this.message = message;
        this.code = code;
        this.error = error;
    }
    public Passenger(int p_id, String p_name, String p_email, String p_cellno,String p_status) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_email = p_email;
        this.p_cellno = p_cellno;
        this.p_status =p_status;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getP_email() {
        return p_email;
    }

    public void setP_email(String p_email) {
        this.p_email = p_email;
    }

    public String getP_password() {
        return p_password;
    }

    public void setP_password(String p_password) {
        this.p_password = p_password;
    }

    public String getP_gender() {
        return p_gender;
    }

    public void setP_gender(String p_gender) {
        this.p_gender = p_gender;
    }

    public String getP_cellNo() {
        return p_cellno;
    }

    public void setP_cellNo(String p_cellno) {
        this.p_cellno = p_cellno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public boolean isStatus() {
        return error;
    }
    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }

}
