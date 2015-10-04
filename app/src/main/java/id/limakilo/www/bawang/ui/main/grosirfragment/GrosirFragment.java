package id.limakilo.www.bawang.ui.main.grosirfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.grosirfragment.mvp.GrosirFragmentPresenter;
import id.limakilo.www.bawang.ui.main.grosirfragment.mvp.GrosirFragmentView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import retrofit.RetrofitError;

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
//                TextView price = (TextView) v.findViewById(R.id.price);
//                TextView quantity = (TextView) v.findViewById(R.id.quantity);
//
//                String prc = "0";
//                String qty = "0";
//
//                if (price != null){
//                    prc = price.getText().toString();
//                }
//
//                if (quantity != null){
//                    qty = quantity.getText().toString();
//                }
//                postOrder(getRadioChoosen(), qty, prc);

                Snackbar.make(layoutView, "Fitur ini sedang dikembangkan", Snackbar.LENGTH_LONG).show();
            }
        });

        return layoutView;
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

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {

    }

    @Override
    public void showState(ViewState state) {

    }
}
