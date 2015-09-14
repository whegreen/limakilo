package id.limakilo.www.bawang.ui.detailorder.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;

/**
 * Created by walesadanto on 1/9/15.
 */
public class DetailOrderPresenterImpl implements DetailOrderPresenter {

    DetailOrderView view;
    APICallListener apiCallListener;


    public DetailOrderPresenterImpl(DetailOrderView view, APICallListener listener){
        this.view = view;
        this.apiCallListener = listener;
    }


    @Override
    public void showLoading(boolean state) {
        if (state){
            view.showDialogProgress();
        }else{
            view.hideDialogProgress();
        }
    }

    @Override
    public void refreshDetailOrder(String orderId) {
    }
}
