package com.limakilogram.www.bawang.ui.main.grosirfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.main.grosirfragment.mvp.GrosirFragmentPresenter;
import com.limakilogram.www.bawang.ui.main.grosirfragment.mvp.GrosirFragmentView;
import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 22/8/15.
 */
public class GrosirFragment extends Fragment implements APICallListener, GrosirFragmentView {

    private GrosirFragmentPresenter presenter;

    View layoutView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        layoutView = inflater.inflate(R.layout.fragment_grosir, container, false);
        return layoutView;
    }

    @Override
    public void onAPICallSucceed() {

    }

    @Override
    public void onAPICallFailed() {

    }

    public void postOrder(){
        String quantity = "";
        String price = "";
        APICallManager.getInstance().postOrderGrosir(quantity, price, new Callback<PostOrderResponseModel>() {
            @Override
            public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
                Toast.makeText(getActivity().getBaseContext(), "success", Toast.LENGTH_SHORT);
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity().getBaseContext(), "failed", Toast.LENGTH_SHORT);
            }
        });
    }
}
