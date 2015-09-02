package com.limakilogram.www.bawang.util.api;

import com.limakilogram.www.bawang.util.api.order.GetMyOrderResponseModel;
import com.limakilogram.www.bawang.util.api.order.GetOrderResponseData;
import com.limakilogram.www.bawang.util.api.order.GetOrderResponseModel;
import com.limakilogram.www.bawang.util.api.order.OrderService;
import com.limakilogram.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;
import com.limakilogram.www.bawang.util.api.stock.GetStockDetailResponseModel;
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
    private String endPoint = "http://limakilo.id";
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

    public boolean getStocks(String stockId, Callback<GetStockDetailResponseModel> callback) {
        StockService stockService = restAdapter.create(StockService.class);
        stockService.getStockDetail(getAuthentification(), stockId, callback);
        return true;
    }

    // ORDERS
    public boolean postOrders(String orderId, String orderQuantity, String orderAddress, String orderPrice,
                              Callback<PostOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.postOrders(getAuthentification(), orderId, orderQuantity, orderAddress, orderPrice, callback);
        return true;
    }

    public boolean postOrders(String orderId, String orderPaymentAmount, String orderName,
                              Callback<PostOrderConfirmResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.confirmOrder(getAuthentification(), orderId, orderPaymentAmount, orderName, callback);
        return true;
    }

    public boolean getOrders(Callback<GetOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getOrders(getAuthentification(), callback);
        return true;
    }

    public boolean getOrders(String orderId, Callback<GetOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getOrderDetail(getAuthentification(), orderId, callback);
        return true;
    }







}
