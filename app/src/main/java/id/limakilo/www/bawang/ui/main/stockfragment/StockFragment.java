package id.limakilo.www.bawang.ui.main.stockfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentPresenter;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;

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
public class StockFragment extends Fragment implements APICallListener, StockFragmentView {

    List<GetStockResponseModel.GetStockResponseData> stockList =
            new ArrayList<GetStockResponseModel.GetStockResponseData>();

    private StockFragmentPresenter presenter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_stock_list, container, false);

        setupStockRecyclerView();
        retrieveStockList();

        return recyclerView;
    }

    public void setupStockRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new StockRecyclerViewAdapter(getActivity(), stockList));
    }

    public void retrieveStockList(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));

        APICallManager.getInstance().getStocks(new Callback<GetStockResponseModel>() {
            @Override
            public void success(GetStockResponseModel getStockResponseModel, Response response) {

                stockList = getStockResponseModel.getData();

                Collections.sort(stockList, new Comparator<GetStockResponseModel.GetStockResponseData>() {
                    @Override
                    public int compare(GetStockResponseModel.GetStockResponseData lhs, GetStockResponseModel.GetStockResponseData rhs) {
                        return lhs.getSellerName().compareTo(rhs.getSellerName());
                    }
                });

                setupStockRecyclerView();
            }

            @Override
            public void failure(RetrofitError error) {
                Snackbar.make(recyclerView, "failed to get data", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onAPICallSucceed(String caller, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError retrofitError) {

    }
}
