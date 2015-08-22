package com.limakilogram.www.bawang.ui.main.limakilofragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.main.limakilofragment.mvp.LimakiloFragmentPresenter;
import com.limakilogram.www.bawang.ui.main.limakilofragment.mvp.LimakiloFragmentView;
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
 * Created by walesadanto on 22/8/15.
 */
public class LimakiloFragment extends Fragment implements APICallListener, LimakiloFragmentView {

    List<GetStockResponseModel.GetStockResponseData> stockList =
            new ArrayList<GetStockResponseModel.GetStockResponseData>();
    private LimakiloFragmentPresenter presenter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_limakilo_list, container, false);

        setupChatRecyclerView();
        retrieveChatList();

        return recyclerView;
    }

    public void setupChatRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new StockRecyclerViewAdapter(getActivity(), stockList));
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

    public void showSpinner(){
        AlertDialog.Builder builder;
        AlertDialog alertDialog;

        Context mContext = getActivity().getBaseContext();
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.spinner_order,null);

        String array_spinner[];
        array_spinner=new String[5];

        array_spinner[0]="1";
        array_spinner[1]="2";
        array_spinner[2]="3";
        array_spinner[3]="4";
        array_spinner[4]="5";

        Spinner s = (Spinner) layout.findViewById(R.id.Spinner01);

        ArrayAdapter adapter = new ArrayAdapter(getActivity().getBaseContext() ,android.R.layout.simple_spinner_item, array_spinner);

        s.setAdapter(adapter);

        builder = new AlertDialog.Builder(mContext);
        builder.setView(layout);
        alertDialog = builder.create();
    }
}
