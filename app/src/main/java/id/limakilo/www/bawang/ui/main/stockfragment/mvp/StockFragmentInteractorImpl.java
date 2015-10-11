package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 9/24/15.
 */
public class StockFragmentInteractorImpl implements StockFragmentInteractor {

    private APICallListener listener;

    public StockFragmentInteractorImpl(APICallListener listener){
        this.listener = listener;
    }

    @Override
    public void callAsync(StockFragmentListener.Listener caller, StockFragmentListener.ListenerAction action) {
        switch (caller){
            case FACEBOOK:

                break;

            default:
                break;
        }
    }

    @Override
    public void callAPIGetStocks(){
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETSTOCKS;
        APICallManager.getInstance().getStocks(new Callback<GetStockResponseModel>() {
            @Override
            public void success(GetStockResponseModel getStockResponseModel, Response response) {
                listener.onAPICallSucceed(route, getStockResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

}
