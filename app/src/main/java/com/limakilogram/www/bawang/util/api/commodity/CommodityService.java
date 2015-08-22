package com.limakilogram.www.bawang.util.api.commodity;

import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;

/**
 * Created by walesadanto on 23/8/15.
 */
public interface CommodityService {
    @GET("/categories/1")
    void getCategory(@Header("authentification") String authentification,
                   Callback<GetCommodityCategoriesResponseModel.GetCommodityCategoriesResponseData> callback);
}
