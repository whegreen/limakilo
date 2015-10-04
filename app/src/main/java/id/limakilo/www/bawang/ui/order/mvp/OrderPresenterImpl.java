package id.limakilo.www.bawang.ui.order.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 1/9/15.
 */
public class OrderPresenterImpl implements OrderPresenter, APICallListener {

    OrderView view;


    public OrderPresenterImpl(OrderView view){
        this.view = view;
    }


    @Override
    public void presentState(OrderView.ViewState state) {
        switch (state){
            case LOADING:
                view.showState(OrderView.ViewState.LOADING);
                break;
            case IDLE:
                view.showState(OrderView.ViewState.IDLE);
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading(boolean state) {
        if (state){
//            view.showDialogProgress();
        }else{
//            view.hideDialogProgress();
        }
    }

    @Override
    public void refreshDetailOrder(String orderId) {
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {

    }
}
