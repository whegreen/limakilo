package id.limakilo.www.bawang.util.api;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.api.order.OrderService;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import id.limakilo.www.bawang.util.api.stock.StockService;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.api.user.UserService;

import id.limakilo.www.bawang.util.common.PreferencesManager;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by walesadanto on 2/7/15.
 */
public class APICallManager {

    private static APICallManager instance;
    private RestAdapter restAdapter;
    private String endPoint = "http://limakilo.id";
    private static final String DEMO_AUTH = "EkhZMUG0";
//    public static Boolean usingMock = true;

    private String authentification;

    /**
     * Returns singleton class instance
     */
    public static APICallManager getInstance(String authentification) {
        if (instance == null) {
            synchronized (APICallManager.class) {
                if (instance == null) {
                    instance = new APICallManager();
                }
                instance.setAuthentification(authentification);
            }
        }
        return instance;
    }

    /**
     * Returns singleton class instance
     */
    public static APICallManager getInstance() {
        return getInstance(DEMO_AUTH);
    }

    public APICallManager() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setConnectTimeout(30, TimeUnit.SECONDS);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
//                .setClient(new OkClient(client))
                .build();
    }

    public String getAuthentification() {
        return authentification;
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

    public boolean getUsers(Callback<GetUserResponseModel> callback){
        UserService userService = restAdapter.create(UserService.class);
        userService.getUsers(getAuthentification(), callback);
        return true;
    }

    public boolean putUsers(String address, String phone, String email, String city, Callback<PutUserResponseModel> callback){
        UserService userService = restAdapter.create(UserService.class);
        userService.putUsers(getAuthentification(), address, phone, email, city, callback);
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
    public boolean postOrders(String stockId, String orderQuantity, String orderAddress, String orderPrice,
                              Callback<PostOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.postOrders(getAuthentification(), stockId, orderQuantity, orderAddress, orderPrice, callback);
        return true;
    }

    public boolean postOrders(String orderId, String orderPaymentAmount, String orderName,
                              Callback<PostOrderConfirmResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.confirmOrder(getAuthentification(), orderId, orderId, orderPaymentAmount, orderName, callback);
        return true;
    }

    public boolean getOrders(Callback<GetOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getOrders(getAuthentification(), callback);
        return true;
    }

    public boolean getOrders(String orderId, Callback<GetOrderDetailResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getOrderDetail(getAuthentification(), orderId, callback);
        return true;
    }









}
