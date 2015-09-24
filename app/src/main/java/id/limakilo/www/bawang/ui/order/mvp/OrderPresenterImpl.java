package id.limakilo.www.bawang.ui.order.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;

/**
 * Created by walesadanto on 1/9/15.
 */
public class OrderPresenterImpl implements OrderPresenter {

    OrderView view;
    APICallListener apiCallListener;


    public OrderPresenterImpl(OrderView view, APICallListener listener){
        this.view = view;
        this.apiCallListener = listener;
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
}
