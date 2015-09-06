package com.limakilogram.www.bawang.ui.detailorder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.appyvet.rangebar.RangeBar;
import com.crashlytics.android.Crashlytics;
import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.confirmorder.ConfirmOrderActivity;
import com.limakilogram.www.bawang.ui.detailorder.mvp.DetailOrderPresenterImpl;
import com.limakilogram.www.bawang.ui.detailorder.mvp.DetailOrderView;
import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.stock.GetStockDetailResponseModel;

import org.w3c.dom.Text;

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
    private RangeBar rangebar;
    private TextView rangebarValue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_detail_order, container, false);

        rangebar = (RangeBar) view.findViewById(R.id.rangebar);
        rangebarValue = (TextView) view.findViewById(R.id.rangebar_value);

        final NestedScrollView scrollview = (NestedScrollView) view.findViewById(R.id.nested_scroll_view);

        final TextView orderAmount = (TextView) view.findViewById(R.id.order_amount);
        final TextView stockQuantity = (TextView) view.findViewById(R.id.stock_quantity);
        final TextView stockPrice = (TextView) view.findViewById(R.id.stock_price);
        final TextView totalPayment = (TextView) view.findViewById(R.id.total_payment);

        final TextView namaPenerima = (TextView) view.findViewById(R.id.edit_nama_penerima);
        final TextView alamatPengiriman = (TextView) view.findViewById(R.id.edit_alamat_penerima);
        final TextView nomorHandphone = (TextView) view.findViewById(R.id.edit_nomor_telepon);

        namaPenerima.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    scrollview.scrollTo(0, namaPenerima.getTop());
                    nomorHandphone.requestFocus();
                    handled = true;
                }
                return handled;
            }
        });

        nomorHandphone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    scrollview.scrollTo(0, nomorHandphone.getTop());
                    alamatPengiriman.requestFocus();
                    handled = true;
                }
                return handled;
            }
        });

        alamatPengiriman.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    ((DetailOrderActivity)getActivity()).showConfirmDialog();
                    ((DetailOrderActivity)getActivity()).hideSoftKeyboard();
                    handled = true;
                }
                return handled;
            }
        });

        Button buttonPesan = (Button) view.findViewById(R.id.btn_pesan);
        final Button buttonDetailPenerima = (Button) view.findViewById(R.id.btn_detail_penerima);

        rangebar.setSeekPinByIndex(0);

        stockQuantity.setText(((DetailOrderActivity) getActivity()).getModel().getStockQuantity().toString());
        stockPrice.setText(((DetailOrderActivity) getActivity()).getModel().getStockPrice().toString());

        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
                                              int rightPinIndex,
                                              String leftPinValue, String rightPinValue) {
                rangebarValue.setText(rightPinValue);
                orderAmount.setText(rightPinValue);
                int total = Integer.parseInt(rightPinValue.toString()) *
                        Integer.parseInt(stockQuantity.getText().toString()) *
                        Integer.parseInt(stockPrice.getText().toString());
                totalPayment.setText(((Integer) total).toString());
            }
        });

        buttonPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((DetailOrderActivity) getActivity()).showConfirmDialog();
            }
        });

        buttonDetailPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scrollview.scrollTo(0, buttonDetailPenerima.getTop());
                namaPenerima.requestFocus();
                ((DetailOrderActivity) getActivity()).showSoftKeyboard();
            }
        });

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
