package id.limakilo.www.bawang.ui.order.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 10/11/15.
 */
public class OrderInteractorImpl implements OrderInteractor {

    private APICallListener listener;

    public OrderInteractorImpl(APICallListener listener){
        this.listener = listener;
    }

    @Override
    public void callAPIGetOrder(String authentification, String orderId, String orderAmount, String name) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.CONFIRMORDER;
        APICallManager.getInstance(authentification).postOrders(
                orderId,
                orderAmount,
                name,
                new retrofit.Callback<PostOrderConfirmResponseModel>() {
                    @Override
                    public void success(PostOrderConfirmResponseModel postOrderConfirmResponseModel, Response
                            response) {
                        listener.onAPICallSucceed(route, postOrderConfirmResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        listener.onAPICallFailed(route, error);
                    }
                }
        );
    }

    @Override
    public void callAPIPostOrder(String authentification, String stockId, String orderQuantity,
                                 String alamatPengiriman, String stockPrice, String kotaPenerima, String email){
        final APICallManager.APIRoute route = APICallManager.APIRoute.POSTORDER;
        APICallManager.getInstance(authentification).postOrders(
                stockId,
                orderQuantity,
                alamatPengiriman,
                stockPrice,
                kotaPenerima,
                email,
                new retrofit.Callback<PostOrderResponseModel>() {
                    @Override
                    public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
                        listener.onAPICallSucceed(route, postOrderResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        listener.onAPICallFailed(route, error);
                    }
                });
    }


    @Override
    public void callAPIPutUser(String authentification, String address, String phone, String email, String city){
        final APICallManager.APIRoute route = APICallManager.APIRoute.PUTUSER;
        APICallManager.getInstance(authentification).putUsers(
                address,
                phone,
                email,
                city,
                new retrofit.Callback<PutUserResponseModel>() {
                    @Override
                    public void success(PutUserResponseModel putUserResponseModel, Response response) {
                        listener.onAPICallSucceed(route, putUserResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        listener.onAPICallFailed(route, error);
                    }
                }
        );
    }

    @Override
    public void callAPIGetStockDetails(String authentification, String stockId){
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETSTOCK;
        APICallManager.getInstance(authentification).getStocks(
                stockId,
                new Callback<GetStockDetailResponseModel>() {
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
    public void callAPIGetUser(String authentification) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETUSER;
        APICallManager.getInstance(authentification).getUsers(
                new Callback<GetUserResponseModel>() {
                    @Override
                    public void success(GetUserResponseModel getUserResponseModel, Response response) {
                        listener.onAPICallSucceed(route, getUserResponseModel);
                    }
                    @Override
                    public void failure(RetrofitError error) {
                        listener.onAPICallFailed(route, error);
                    }
                });
    }
}
