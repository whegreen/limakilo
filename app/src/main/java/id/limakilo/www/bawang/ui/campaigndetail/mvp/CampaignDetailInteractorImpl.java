package id.limakilo.www.bawang.ui.campaigndetail.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.campaign.GetCampaignDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 10/13/15.
 */
public class CampaignDetailInteractorImpl implements CampaignDetailInteractor {

    APICallListener listener;

    public CampaignDetailInteractorImpl(APICallListener listener){
        this.listener = listener;
    }

    @Override
    public void callAPIGetCampaignDetail(String authentification, String campaignId){
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETCAMPAIGN;
        APICallManager.getInstance(authentification).getCampaignDetail(campaignId, new Callback<GetCampaignDetailResponseModel>() {
            @Override
            public void success(GetCampaignDetailResponseModel getOrderResponseModel, Response response) {
                listener.onAPICallSucceed(route, getOrderResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

    @Override
    public void callAPIGetOrders(String authentification){
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETORDERS;
        APICallManager.getInstance(authentification).getOrders(new Callback<GetOrderResponseModel>() {
            @Override
            public void success(GetOrderResponseModel getOrderResponseModel, Response response) {
                listener.onAPICallSucceed(route, getOrderResponseModel);
            }
            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

    @Override
    public void callAPIStockDetail(String authentification, String stockId){
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETSTOCK;
        APICallManager.getInstance(authentification).getStocks(stockId, new Callback<GetStockDetailResponseModel>() {
            @Override
            public void success(GetStockDetailResponseModel getStockDetailResponseModel, Response response) {
                listener.onAPICallSucceed(route, getStockDetailResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

    @Override
    public void callAPIOrderDetail(String authentification, String orderId){
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETORDER;
        APICallManager.getInstance(authentification).getOrders(orderId, new Callback<GetOrderDetailResponseModel>() {
            @Override
            public void success(GetOrderDetailResponseModel getOrderDetailResponseModel, Response response) {
                listener.onAPICallSucceed(route, getOrderDetailResponseModel);
            }
            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

    @Override
    public void callAPIConfirmOrder(String authentification, String orderId, String totalPayment, String fullName){
        final APICallManager.APIRoute route = APICallManager.APIRoute.CONFIRMORDER;
        APICallManager.getInstance(authentification).postOrders(
                orderId,
                totalPayment,
                fullName,
                new Callback<PostOrderConfirmResponseModel>() {
                    @Override
                    public void success(PostOrderConfirmResponseModel getStockDetailResponseModel, Response response) {
                        listener.onAPICallSucceed(route, getStockDetailResponseModel);
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        listener.onAPICallFailed(route, error);
                    }
                });
    }


}
