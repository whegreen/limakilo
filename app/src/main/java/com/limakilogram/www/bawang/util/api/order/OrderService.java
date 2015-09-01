package com.limakilogram.www.bawang.util.api.order;

import com.limakilogram.www.bawang.util.api.bid.GetMyBidResponseModel;
import com.limakilogram.www.bawang.util.api.user.LoginResponseModel;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by walesadanto on 23/8/15.
 */
public interface OrderService {

    @FormUrlEncoded
    @POST("/orders")
    void postOrders(@Header("authentification") String authentification,
                    @Field("stock_id") String stockId,
                    @Field("order_quantity") String orderQuantity,
                    @Field("order_address") String orderAddress,
                    @Field("order_price") String orderPrice,
                    Callback<PostOrderResponseModel> callback);

    @FormUrlEncoded
    @POST("/orders/{order_id}/confirm")
    void confirmOrder(@Header("authentification") String authentification,
                   @Field("order_id") String orderId,
                   @Field("order_payment_amount") String orderPaymentAmount,
                   @Field("order_name") String orderName,
                   Callback<PostOrderConfirmResponseModel> callback);


    @GET("/orders/{id}")
    void getOrderDetail(@Header("authentification") String authentification,
                  @Path("id") String orderId,
                  Callback<GetOrderResponseModel> callback);

    @GET("/orders")
    void getOrders(@Header("authentification") String authentification,
                   Callback<GetOrderResponseModel> callback);
}
