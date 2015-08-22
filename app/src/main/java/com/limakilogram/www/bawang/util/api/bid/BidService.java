package com.limakilogram.www.bawang.util.api.bid;

import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by walesadanto on 23/8/15.
 */
public interface BidService {

    @GET("/mybiddings")
    void getMyBid(@Header("authentification") String authentification,
                   Callback<GetMyBidResponseModel> callback);
}
