package com.limakilogram.www.bawang.ui.detailorder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;
import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.detailorder.mvp.DetailOrderPresenterImpl;
import com.limakilogram.www.bawang.ui.detailorder.mvp.DetailOrderView;
import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.stock.GetStockDetailResponseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 30/8/15.
 */
public class DetailOrderFragment extends Fragment implements DetailOrderView, APICallListener {

    private View view;
    private DetailOrderPresenterImpl presenter;
    private MaterialDialog dialogProgress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detail_order, container, false);

        try{
            String stockId = ((DetailOrderActivity)getActivity()).getModel().getStockId().toString();
            retrieveDetailOrder(stockId);

        }catch (Exception e){
            Crashlytics.log(Log.ERROR, "api call", e.toString());
        }

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK != resultCode) {
            getActivity().finish();
            return;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_leave) {
//            leave();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAPICallSucceed() {
        Snackbar.make(view, "API call succeed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAPICallFailed() {
        Snackbar.make(view, "API call failed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showDialogProgress() {
        dialogProgress = new MaterialDialog.Builder(getActivity())
                .title("contacting server")
                .content("Please wait ...")
                .progress(true, 0)
                .show();
    }

    public void retrieveDetailOrder(String stockId){
        APICallManager.getInstance().getStocks(stockId, new Callback<GetStockDetailResponseModel>() {
            @Override
            public void success(GetStockDetailResponseModel getStockDetailResponseModel, Response response) {
                Snackbar.make(view, "API call succeed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Snackbar.make(view, "API call error", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void hideDialogProgress(){
        dialogProgress.hide();
    }

    public void showDialogAmount() {
        dialogProgress = new MaterialDialog.Builder(getActivity())
                .title("contacting server")
                .content("Please wait ...")
                .progress(true, 0)
                .show();
    }
}
