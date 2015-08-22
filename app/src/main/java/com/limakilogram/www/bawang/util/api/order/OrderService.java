package com.limakilogram.www.bawang.util.api.order;

import com.limakilogram.www.bawang.util.api.bid.GetMyBidResponseModel;
import com.limakilogram.www.bawang.util.api.user.LoginResponseModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;

/**
 * Created by walesadanto on 23/8/15.
 */
public interface OrderService {

    @FormUrlEncoded
    @POST("/orders")
    void postOrder(@Field("stock_id") String stockId,
                       @Field("quantity") String quantity,
                       @Field("price") String price,
                       @Field("type") String type,
                       Callback<PostOrderResponseModel> callback);

    @GET("/myorders")
    void getMyOrder(@Header("authentification") String authentification,
                  Callback<GetMyOrderResponseModel> callback);

}
