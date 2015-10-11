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

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentModel;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentPresenter;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentPresenterImpl;
import id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentView;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import io.supportkit.ui.ConversationActivity;

/**
 * Created by walesadanto on 22/8/15.
 */
public class StockFragment extends Fragment implements StockFragmentView {

//    List<GetStockResponseModel.GetStockResponseData> stockList =
//            new ArrayList<GetStockResponseModel.GetStockResponseData>();

    private StockFragmentPresenter presenter;
    private StockFragmentModel model;

    @Bind(R.id.loading_bar) View viewLoading;
    @Bind(R.id.error_state) View viewErrorState;
    @Bind(R.id.blank_state) View viewBlankState;
    @Bind(R.id.recyclerview) RecyclerView recyclerView;


    @OnClick(R.id.btn_error_refresh)
    public void OnBtnErrorRefresh(){
        presenter.presentState(ViewState.LOAD_DATA);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_stock_list, container, false);
        ButterKnife.bind(this, view);
        presenter = new StockFragmentPresenterImpl(this);
        model = new StockFragmentModel();
        setupStockRecyclerView();
        presenter.presentState(ViewState.LOAD_DATA);
        return view;
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    //tambah method untuk load selain onviewcreated
    public void setupStockRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new StockRecyclerViewAdapter(getActivity(), model.getStockList()));
    }

    @Override
    public void doSetAuthentification(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));
    }

    @Override
    public void doUpdateModel(RootResponseModel responseModel) {
        model.setStockList(((GetStockResponseModel) responseModel).getData());
        if (model.isModelReady()){
            model.sortModel();
            setupStockRecyclerView();
        }else {
            presenter.presentState(ViewState.BLANK_STATE);
        }
    }

    @Override
    public void doRetrieveModel() {

    }

    public void showStockList(){
        try{
            recyclerView.setVisibility(View.VISIBLE);
            viewLoading.setVisibility(View.GONE);
            viewBlankState.setVisibility(View.GONE);
            viewErrorState.setVisibility(View.GONE);

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
            viewErrorState.setVisibility(View.GONE);
        }catch(Exception e){
            Crashlytics.logException(e);
        }


    }

    public void showBlankState(){
        recyclerView.setVisibility(View.GONE);
        viewBlankState.setVisibility(View.VISIBLE);
        viewLoading.setVisibility(View.GONE);
        viewErrorState.setVisibility(View.GONE);

    }

    public void showErrorState(){
        recyclerView.setVisibility(View.GONE);
        viewBlankState.setVisibility(View.GONE);
        viewLoading.setVisibility(View.GONE);
        viewErrorState.setVisibility(View.VISIBLE);
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
    public void showState(ViewState state) {
        switch (state){
            case LOADING:
                showLoading();
                break;
            case LOAD_DATA:
                setupStockRecyclerView();
                break;
            case IDLE:
                showStockList();
                break;
            case ERROR:
                showErrorSnackbar();
                break;
            case ERROR_API_CALL:
                showErrorState();
                break;
            case BLANK_STATE:
                showBlankState();
                break;
            default:
                break;
        }
    }
}
