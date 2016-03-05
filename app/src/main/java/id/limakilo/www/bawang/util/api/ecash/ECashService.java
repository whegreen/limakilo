package id.limakilo.www.bawang.util.api.ecash;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by walesadanto on 23/8/15.
 */
public interface ECashService {

    @GET("/accountHistory")
    void getAccountHistory(
            @Query("token") String token,
            @Query("msisdn") String msisdn,
            @Query("pagesize") String pagesize,
            @Query("onpage") String onpage,
            Callback<GetAccountHistory> callback);

    @GET("/inquiryTransferMember")
    void getInquiryTransferMember(
            @Query("token") String token,
            @Query("msisdn") String msisdn,
            @Query("amount") String amount,
            @Query("to") String to,
            Callback<GetInquiryTransferMember> callback);

    @GET("/logoutMember")
    void getLogoutMember(
            @Query("token") String token,
            @Query("msisdn") String msisdn,
            Callback<GetLogout> callback);

    @GET("/transferMember")
    void getTransferMember(
            @Query("token") String token,
            @Query("credentials") String credentials,
            @Query("amount") String amount,
            @Query("to") String to,
            @Query("from") String from,
            @Query("description") String description,
            Callback<GetTransferMember> callback);

    @GET("/purchase")
    void getPurchase(
            @Query("token") String token,
            @Query("msisdn") String msisdn,
            @Query("credentials") String credentials,
            @Query("amount") String amount,
            Callback<GetPurchase> callback);

    @GET("/loginMember")
    void getLogin(
            @Query("uid") String uid,
            @Query("msisdn") String msisdn,
            @Query("credentials") String credentials,
            Callback<GetLogin> callback);

    @GET("/balanceInquiry")
    void getbalanceInquiry(
        @Query("token") String token,
        @Query("msisdn") String msisdn,
        @Query("credentials") String credentials,
        Callback<GetBalanceInquiry> callback);

}
