package com.example.fareshare.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
//passenger requests.
public class Requests {

    public Requests(int p_id, String p_name, String p_email, String p_cellno, String p_status) {
        this.p_id = p_id;
        this.p_name = p_name;
        this.p_email = p_email;
        this.p_cellno = p_cellno;
        this.p_status = p_status;
    }

    @SerializedName("p_id")
    @Expose
    private int p_id;
    @SerializedName("p_name")
    @Expose
    private String p_name;
    @SerializedName("p_email")
    @Expose
    private String p_email;
    @SerializedName("p_cellno")
    @Expose
    private String p_cellno;
    @SerializedName("p_status")
    @Expose
    private String p_status;

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

    public String getP_cellno() {
        return p_cellno;
    }

    public void setP_cellno(String p_cellno) {
        this.p_cellno = p_cellno;
    }

    public String getP_status() {
        return p_status;
    }

    public void setP_status(String p_status) {
        this.p_status = p_status;
    }
}
