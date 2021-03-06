package com.ynwa.kdl.hosein.shopping.retrofit.Authentication_Service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginObject {

    @Expose
    @SerializedName("token_type")
    private String token_type;
    @Expose
    @SerializedName("expires_in")
    private long expires_in;
    @Expose
    @SerializedName("access_token")
    private String access_token;
    @Expose
    @SerializedName("refresh_token")
    private String refresh_token;



    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}
