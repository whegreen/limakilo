package com.limakilogram.www.bawang.ui.main.grosirfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

        layoutView.findViewById(R.id.btn_choice).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView price = (TextView) v.findViewById(R.id.price);
                TextView quantity = (TextView) v.findViewById(R.id.quantity);

                String prc = "0";
                String qty = "0";

                if (price != null){
                    prc = price.getText().toString();
                }

                if (quantity != null){
                    qty = quantity.getText().toString();
                }
                postOrder(getRadioChoosen(), qty, prc);
            }
        });

        return layoutView;
    }

    @Override
    public void onAPICallSucceed() {

    }

    @Override
    public void onAPICallFailed() {

    }

    public void postOrder(int type, String qty, String prc){
        String quantity = qty;
        String price = prc;

//        APICallManager.getInstance().postOrderGrosir(quantity, price, new Callback<PostOrderResponseModel>() {
//            @Override
//            public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
//                Toast.makeText(getActivity().getBaseContext(), "success", Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getActivity().getBaseContext(), "failed"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    public int getRadioChoosen(){
        RadioButton radioChoosen = (RadioButton) layoutView.findViewById(R.id.radioButton);

        if (radioChoosen != null && radioChoosen.isChecked()){
            return 0;
        }else{
            return 1;
        }

    }
}
