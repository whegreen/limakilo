package id.limakilo.www.bawang.util.api.user;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.PUT;

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

    @FormUrlEncoded
    @POST("/login")
    void login(@Header("authentification") String authentification,
               @Field("app_version") String appVersion,
               Callback<LoginResponseModel> callback);

    @FormUrlEncoded
    @POST("/login")
    void loginEmail(
            @Field("username") String username,
            @Field("password") String password,
            Callback<LoginEmailResponseModel> callback);

    @GET("/users")
    void getUsers(@Header("authentification") String authentification,
                  Callback<GetUserResponseModel> callback);

    @FormUrlEncoded
    @PUT("/users")
    void putUsers(@Header("authentification") String authentification,
                  @Field("address") String address,
                  @Field("phone_number") String phone,
                  @Field("email") String email,
                  @Field("city") String city,
                  Callback<PutUserResponseModel> callback);

}
