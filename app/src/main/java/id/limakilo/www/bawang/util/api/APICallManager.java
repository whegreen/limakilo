package id.limakilo.www.bawang.util.api;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import id.limakilo.www.bawang.util.api.campaign.CampaignService;
import id.limakilo.www.bawang.util.api.campaign.GetCampaignDetailResponseModel;
import id.limakilo.www.bawang.util.api.campaign.GetCampaignResponseModel;
import id.limakilo.www.bawang.util.api.ecash.ECashService;
import id.limakilo.www.bawang.util.api.ecash.GetAccountHistory;
import id.limakilo.www.bawang.util.api.ecash.GetBalanceInquiry;
import id.limakilo.www.bawang.util.api.ecash.GetInquiryTransferMember;
import id.limakilo.www.bawang.util.api.ecash.GetLogin;
import id.limakilo.www.bawang.util.api.ecash.GetLogout;
import id.limakilo.www.bawang.util.api.ecash.GetPurchase;
import id.limakilo.www.bawang.util.api.ecash.GetTransferMember;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.api.order.OrderService;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import id.limakilo.www.bawang.util.api.stock.StockService;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginEmailResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.api.user.UserService;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by walesadanto on 2/7/15.
 */
public class APICallManager {

    public enum APIRoute {
        LOGINDIGIT, LOGINFACEBOOK, LOGIN, LOGINEMAIL,
        GETUSERS, GETUSER, POSTUSER, CONFIRMORDER,
        GETORDERS, GETORDER, POSTORDER,
        GETSTOCKS, GETSTOCK, POSTSTOCK, PUTUSER, GETCAMPAIGN, GETCAMPAIGNS,

        GETACCOUNTHISTORY, GETBALANCEINQUIRY, GETINQUIRYTRANSFERMEMBER, GETLOGIN, GETLOGOUT,
        GETPURCHASE, GETTRANSFERMEMBER
    }

    private static APICallManager instance;
    private RestAdapter restAdapter;
    private RestAdapter eCashRestAdapter;
    private RestAdapter blueMixRestAdapter;

    private String endPoint = "http://api.limakilo.id";
    private String blueMixEndPoint = "http://limakilo.mybluemix.net";
    private String eCashEndPoint = "https://api.apim.ibmcloud.com/ex-icha-fmeirisidibmcom-ecash-be/sb/emoney/v1";
//    public static final String DEMO_AUTH = "EkhZMUG0";
    public static final String DEMO_AUTH = "VygphmZh";
    public APIRoute apiRoute;
//    public static Boolean usingMock = true;

    private static String authentification;
    private static String appVersion;

    private static String msisdn = "08562999635";
    private static String uid = "22";
    private static String token = "892210";
    private static String credentials;

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
        return getInstance(authentification);
    }

    public APICallManager() {
        OkHttpClient client = new OkHttpClient();
        client.setReadTimeout(30, TimeUnit.SECONDS);
        client.setConnectTimeout(30, TimeUnit.SECONDS);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(endPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(client))
                .build();

        blueMixRestAdapter = new RestAdapter.Builder()
                .setEndpoint(blueMixEndPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(client))
                .build();

        eCashRestAdapter = new RestAdapter.Builder()
                .setEndpoint(eCashEndPoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setClient(new OkClient(client))
                .build();
    }

    public String getAuthentification() {
        return authentification;
    }

    public void setAuthentification(String authentification) {
        this.authentification = authentification;
    }

    public static void setAppVersion(String appVersion) {
        APICallManager.appVersion = appVersion;
    }

    public static String getAppVersion() {
        return appVersion;
    }

    // USERS
    public boolean loginFacebook(String facebookId, String firstName, String lastName,
                                 String email, Callback<LoginResponseModel> callback) {
        UserService userService = restAdapter.create(UserService.class);
        userService.loginFacebook(facebookId, firstName, lastName, email, callback);
        return true;
    }

    public boolean loginDigit(String digitId, String phone, Callback<LoginResponseModel> callback) {
        UserService userService = restAdapter.create(UserService.class);
        userService.loginDigit(digitId, phone, callback);
        return true;
    }

    public boolean login(Callback<LoginResponseModel> callback) {
        UserService userService = restAdapter.create(UserService.class);
        userService.login(getAuthentification(), getAppVersion(), callback);
        return true;
    }

    public boolean loginEmail(Callback<LoginEmailResponseModel> callback) {
        UserService userService = restAdapter.create(UserService.class);
        userService.loginEmail(getAuthentification(), getAppVersion(), callback);
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
    public boolean postOrders(String stockId, String orderQuantity, String orderAddress, String orderPrice, String orderCity, String userEmail,
                              Callback<PostOrderResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.postOrders(getAuthentification(), stockId, orderQuantity, orderAddress, orderPrice, orderCity, userEmail, callback);
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

    public boolean getOrders(String orderId, Callback<GetOrderDetailResponseModel> callback){
        OrderService orderService = restAdapter.create(OrderService.class);
        orderService.getOrderDetail(getAuthentification(), orderId, callback);
        return true;
    }


    //Campaign

    public boolean getCampaigns(Callback<GetCampaignResponseModel> callback){
        CampaignService campaignService= blueMixRestAdapter.create(CampaignService.class);
        campaignService.getCampaign(getAuthentification(), callback);
        return true;
    }

    public boolean getCampaignDetail(String campaignId, Callback<GetCampaignDetailResponseModel> callback){
        CampaignService campaignService= blueMixRestAdapter.create(CampaignService.class);
        campaignService.getCampaignDetail(getAuthentification(), campaignId, callback);
        return true;
    }


    // E-CASH

    public boolean getAccountHistory(String onpage, Callback<GetAccountHistory> callback){
        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getAccountHistory(token, msisdn, "5", onpage, callback);
        return true;
    }

    public boolean getBalanceInquiry(Callback<GetBalanceInquiry> callback){
        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getbalanceInquiry(token, msisdn, credentials, callback);
        return true;
    }

    public boolean getInquiryTransfer(String amount, String to, Callback<GetInquiryTransferMember> callback){
        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getInquiryTransferMember(token, msisdn, amount, to, callback);
        return true;
    }

    public boolean getTransfer(String amount, String to, String from, String description,
                               Callback<GetTransferMember> callback){
        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getTransferMember(token, credentials, amount, to, from, description, callback);
        return true;
    }

    public boolean getLogin(String msisdn, String credentials, Callback<GetLogin> callback){

        this.msisdn = msisdn;
        this.credentials = credentials;

        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getLogin(uid, msisdn, credentials, callback);
        return true;
    }

    public boolean getLogout(Callback<GetLogout> callback){
        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getLogoutMember(token, msisdn, callback);
        return true;
    }

    public boolean getPurchase(String amount, Callback<GetPurchase> callback){
        ECashService eCashService = eCashRestAdapter.create(ECashService.class);
        eCashService.getPurchase(token, msisdn, credentials, amount, callback);
        return true;
    }

    public static void setToken(String token) {
        APICallManager.token = token;
    }
}
