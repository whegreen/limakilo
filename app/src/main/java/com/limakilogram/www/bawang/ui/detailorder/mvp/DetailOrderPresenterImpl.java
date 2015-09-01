package com.limakilogram.www.bawang.ui.detailorder.mvp;

import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.order.GetOrderResponseData;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

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
        APICallManager.getInstance().getOrder(orderId, new Callback<GetOrderResponseData>() {
            @Override
            public void success(Result<GetOrderResponseData> result) {
//                result
                apiCallListener.onAPICallSucceed();
            }

            @Override
            public void failure(TwitterException e) {
                apiCallListener.onAPICallFailed();
            }
        });
    }
}
