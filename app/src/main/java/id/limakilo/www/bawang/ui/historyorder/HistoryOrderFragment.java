package id.limakilo.www.bawang.ui.historyorder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderPresenter;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 1/9/15.
 */
public class HistoryOrderFragment extends Fragment implements APICallListener, HistoryOrderView {

    List<GetOrderResponseModel.GetOrderResponseData> orderList =
            new ArrayList<GetOrderResponseModel.GetOrderResponseData>();
    private HistoryOrderPresenter presenter;
    RecyclerView recyclerView;
    private MaterialDialog confirmDialog;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_history_order_list, container, false);
        ButterKnife.bind(this, recyclerView);

        setupOrderRecyclerView();
        retrieveOrderList();

        boolean wrapInScrollView = true;
        confirmDialog = new MaterialDialog.Builder(getActivity())
                .title("Konfirmasi Pembayaran")
                .customView(R.layout.dialog_confirm_payment_custom_view, wrapInScrollView)
                .positiveText("Transfer sudah dilakukan")
                .negativeText("Transfer nanti")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        confirmDialog.hide();
                    }
                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();

        return recyclerView;
    }

    public void setupOrderRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new HistoryOrderRecyclerViewAdapter(getActivity(), orderList));
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void retrieveOrderList(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETORDERS;
        APICallManager.getInstance().getOrders(new Callback<GetOrderResponseModel>() {
            @Override
            public void success(GetOrderResponseModel getOrderResponseModel, Response response) {
                orderList = getOrderResponseModel.getData();
                Collections.sort(orderList, new Comparator<GetOrderResponseModel.GetOrderResponseData>() {
                    @Override
                    public int compare(GetOrderResponseModel.GetOrderResponseData lhs, GetOrderResponseModel.GetOrderResponseData rhs) {
                        return lhs.getOrderDate().compareTo(rhs.getOrderDate());
                    }
                });
                setupOrderRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {

    }
}
