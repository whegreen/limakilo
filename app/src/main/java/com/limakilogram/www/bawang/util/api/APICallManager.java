package com.limakilogram.www.bawang.util.api;

import com.limakilogram.www.bawang.util.api.bid.BidService;
import com.limakilogram.www.bawang.util.api.bid.GetMyBidResponseModel;
import com.limakilogram.www.bawang.util.api.commodity.CommodityService;
import com.limakilogram.www.bawang.util.api.commodity.GetCommodityCategoriesResponseModel;
import com.limakilogram.www.bawang.util.api.order.GetMyOrderResponseModel;
import com.limakilogram.www.bawang.util.api.order.GetOrderResponseData;
import com.limakilogram.www.bawang.util.api.order.OrderService;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;
import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;
import com.limakilogram.www.bawang.util.api.stock.StockService;
import com.limakilogram.www.bawang.util.api.user.LoginResponseModel;
import com.limakilogram.www.bawang.util.api.user.UserService;

import retrofit.Callback;
import retrofit.RestAdapter;

/**
 * Created by walesadanto on 2/7/15.
 */
public class APICallManager {

    private static APICallManager instance;
    private RestAdapter restAdapter;
    private String endPoint = "http://api.limakilo.id";
//    public static Boolean usingMock = true;

    private String authentification;

    /**
     * Returns singleton class instance
     */
    public static APICallManager getInstance() {
        if (instance == null) {
            synchronized (APICallManager.class) {
                if (instance == null) {
                    instance = new APICallManager();
                }
            }
        }
        return instance;
    }

    public APICallManager() {
//        OkHttpClient client = new OkHttpClient();
//        client.setReadTimeout(30, TimeUnit.SECONDS);
//        client.setConnectTimeout(30, TimeUnit.SECONDS);
//        restAdapter = new RestAdapter.Builder().setEndpoint(endPoint)
//                .setLogLevel(RestAdapter.LogLevel.FULL).setClient(new OkClient(client)).build();

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
    }

    public String getAuthentification() {
        return "NkxLsPb3";
//        return authentification;
    }

    public void setAuthentification(String authentification) {
        this.authentification = authentification;
    }

    // USERS
    public boolean loginFacebook(String facebookId, String firstName, String lastName,
                                 String email, Callback<LoginResponseModel> callback) {
        UserService userService = restAdapter.create(UserService.class);
        userService.loginFacebook(facebookId, firstName, lastName, email, callback);
        return true;
    }

    // STOCKS
    public boolean getStocks(Callback<GetStockResponseModel> callback) {
        StockService stockService = restAdapter.create(StockService.class);
        stockService.getStocks(getAuthentification(), callback);
        return true;
    }

    // ORDERS
    public boolean postOrder5kg(String stockId, String quantity, Callback<PostOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.postOrder(getAuthentification(), stockId, quantity, "0", "5kg", callback);
        return true;
    }

    public boolean postOrderGrosir(String quantity, String price, Callback<PostOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.postOrder(getAuthentification(), "1", quantity, price, "Grosir", callback);
        return true;
    }

    public boolean getMyOrders(Callback<GetMyOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getMyOrder(getAuthentification(), callback);
        return true;
    }

    public boolean getOrder(String orderId, Callback<GetOrderResponseData> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getOrder(getAuthentification(), orderId, callback);
        return true;
    }

    // COMMODITIES

    public boolean getCommoditiesCategory(Callback<GetCommodityCategoriesResponseModel> callback){
        CommodityService commodityService = restAdapter.create(CommodityService.class);
        commodityService.getCategory(getAuthentification(), callback);
        return true;
    }


    // BIDS

    public boolean getMyBids(Callback<GetMyBidResponseModel> callback){
        BidService bidService = restAdapter.create(BidService.class);
        bidService.getMyBid(getAuthentification(), callback);
        return true;
    }

}
