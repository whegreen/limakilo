package com.limakilogram.www.bawang.ui.historyorder;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.historyorder.mvp.HistoryOrderPresenter;
import com.limakilogram.www.bawang.ui.historyorder.mvp.HistoryOrderView;
import com.limakilogram.www.bawang.ui.main.limakilofragment.OrderRecyclerViewAdapter;
import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;
import com.limakilogram.www.bawang.util.common.PreferencesManager;

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

    List<GetStockResponseModel.GetStockResponseData> stockList =
            new ArrayList<GetStockResponseModel.GetStockResponseData>();
    private HistoryOrderPresenter presenter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_history_order_list, container, false);

        setupChatRecyclerView();
        retrieveChatList();

        return recyclerView;
    }

    public void setupChatRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new OrderRecyclerViewAdapter(getActivity(), stockList));
    }

    public void retrieveChatList(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));

        APICallManager.getInstance().getStocks(new Callback<GetStockResponseModel>() {
            @Override
            public void success(GetStockResponseModel getStockResponseModel, Response response) {

                stockList = getStockResponseModel.getData();

//                for (Iterator<GetStockResponseModel.GetStockResponseData> i = stockList.iterator(); i.hasNext();){
//                    if (i.next().getAvaUrl() == null) i.remove();
//                }

                Collections.sort(stockList, new Comparator<GetStockResponseModel.GetStockResponseData>() {
                    @Override
                    public int compare(GetStockResponseModel.GetStockResponseData lhs, GetStockResponseModel.GetStockResponseData rhs) {
                        return lhs.getCategory().compareTo(rhs.getCategory());
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
    public void onAPICallSucceed() {

    }

    @Override
    public void onAPICallFailed() {

    }
}
