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

import com.crashlytics.android.Crashlytics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentPresenter;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentView;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import io.supportkit.ui.ConversationActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 22/8/15.
 */
public class StockFragment extends Fragment implements StockFragmentView , StockFragmentListener {

    List<GetStockResponseModel.GetStockResponseData> stockList =
            new ArrayList<GetStockResponseModel.GetStockResponseData>();

    private StockFragmentPresenter presenter;

    @Bind(R.id.loading_bar) View viewLoading;
    @Bind(R.id.blank_state) View viewBlankState;
    @Bind(R.id.recyclerview) RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_stock_list, container, false);
        ButterKnife.bind(this, view);
        setupStockRecyclerView();
        apiCallGetStocks();
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //tambah method untuk load selain onviewcreated

    public void setupStockRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new StockRecyclerViewAdapter(getActivity(), stockList));
    }

    public void apiCallGetStocks(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));

        showLoading();

        APICallManager.getInstance().getStocks(new Callback<GetStockResponseModel>() {
            @Override
            public void success(GetStockResponseModel getStockResponseModel, Response response) {
                onAPICallSucceed("getStocks", getStockResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                onAPICallFailed("getStocks", error);
            }
        });
    }

    @Override
    public void onAPICallSucceed(String caller, RootResponseModel responseModel) {
        if (caller.equalsIgnoreCase("getStocks")) {
            stockList = ((GetStockResponseModel) responseModel).getData();
            if (stockList.isEmpty()){
                showBlankState();
            }else {
                Collections.sort(stockList, new Comparator<GetStockResponseModel.GetStockResponseData>() {
                    @Override
                    public int compare(GetStockResponseModel.GetStockResponseData lhs, GetStockResponseModel.GetStockResponseData rhs) {
                        return lhs.getSellerName().compareTo(rhs.getSellerName());
                    }
                });
                showStockList();
            }
            setupStockRecyclerView();
        }
    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError retrofitError) {

        showErrorSnackbar();

        if (caller.equalsIgnoreCase("getStocks")){
            showBlankState();
        }
        else{

        }
    }

    public void showStockList(){
        try{
            recyclerView.setVisibility(View.VISIBLE);
            viewLoading.setVisibility(View.GONE);
            viewBlankState.setVisibility(View.GONE);
        }
        catch(Exception e){
            Crashlytics.logException(e);
        }

    }

    public void showLoading(){
        try{
            recyclerView.setVisibility(View.GONE);
            viewLoading.setVisibility(View.VISIBLE);
            viewBlankState.setVisibility(View.GONE);
        }catch(Exception e){
            Crashlytics.logException(e);
        }


    }

    public void showBlankState(){
        recyclerView.setVisibility(View.GONE);
        viewBlankState.setVisibility(View.VISIBLE);
        viewLoading.setVisibility(View.GONE);
    }

    public void openSupportKit(){
        ConversationActivity.show(getActivity());
    }

    public void showErrorSnackbar(){
        Snackbar.make(recyclerView, "Server kami bermasalah...", Snackbar.LENGTH_LONG)
                .setAction("Lapor", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openSupportKit();
                    }
                }).show();
    }

    @Override
    public void showViewState(State state, StateInfo info) {

    }

    @Override
    public void onListenerCallback(Listener listenerType, ListenerResult listenerResult, Object result) {

    }
}
