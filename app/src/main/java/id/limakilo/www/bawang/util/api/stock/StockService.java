package id.limakilo.www.bawang.util.api.stock;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface StockService {

    @GET("/stocks")
    void getStocks(@Header("authentification") String authentification,
                  Callback<GetStockResponseModel> callback);

    @GET("/stocks/{stock_id}")
    void getStockDetail(@Header("authentification") String authentification,
                   @Path("stock_id") String stockId,
                   Callback<GetStockDetailResponseModel> callback);
}
