package com.limakilogram.www.bawang.util.api.user;

import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by walesadanto on 15/7/15.
 */
public interface UserService {

    @FormUrlEncoded
    @POST("/login/facebook")
    void loginFacebook(@Field("facebook_id") String facebookId,
                       @Field("first_name") String firstName,
                       @Field("last_name") String lastName,
                       @Field("email") String email,
                       Callback<LoginResponseModel> callback);

    @FormUrlEncoded
    @POST("/login/digit")
    void loginDigit(@Field("digit_id") String digitId,
                       @Field("phone") String phone,
                       Callback<LoginResponseModel> callback);

}
