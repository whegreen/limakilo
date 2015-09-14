package id.limakilo.www.bawang.ui.historyorder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderPresenter;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_history_order_list, container, false);

        setupChatRecyclerView();
        retrieveChatList();

        return recyclerView;
    }

    public void setupChatRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new OrderRecyclerViewAdapter(getActivity(), orderList));
    }

    public void retrieveChatList(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));

        APICallManager.getInstance().getOrders(new Callback<GetOrderResponseModel>() {
            @Override
            public void success(GetOrderResponseModel getOrderResponseModel, Response response) {

                orderList = getOrderResponseModel.getData();

//                for (Iterator<GetStockResponseModel.GetStockResponseData> i = orderList.iterator(); i.hasNext();){
//                    if (i.next().getAvaUrl() == null) i.remove();
//                }

                Collections.sort(orderList, new Comparator<GetOrderResponseModel.GetOrderResponseData>() {
                    @Override
                    public int compare(GetOrderResponseModel.GetOrderResponseData lhs, GetOrderResponseModel.GetOrderResponseData rhs) {
//                        return lhs.getCategory().compareTo(rhs.getCategory());
                        return 0;
                    }
                });
                setupChatRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getActivity(), "failed to get data", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAPICallSucceed(String caller, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError error) {

    }
}
