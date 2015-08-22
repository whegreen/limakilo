package com.limakilogram.www.bawang.util.api.stock;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface StockService {

    @GET("/stocks")
    void getStocks(@Header("authentification") String authentification,
                  Callback<GetStockResponseModel> callback);

}
