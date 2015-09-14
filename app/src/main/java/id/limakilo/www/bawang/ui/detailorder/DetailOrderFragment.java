package id.limakilo.www.bawang.ui.detailorder;

import android.app.Activity;
import android.app.Fragment;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.appyvet.rangebar.RangeBar;
import com.crashlytics.android.Crashlytics;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.detailorder.mvp.DetailOrderPresenterImpl;
import id.limakilo.www.bawang.ui.detailorder.mvp.DetailOrderView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.widget.CustomNumberPicker;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 30/8/15.
 */
public class DetailOrderFragment extends Fragment implements DetailOrderView, APICallListener, NumberPicker.OnValueChangeListener {

    private static final String TAG = "DetailOrderFragment";
    private View view;
    private DetailOrderPresenterImpl presenter;
    private MaterialDialog dialogProgress;
//    private RangeBar rangebar;
    private NumberPicker numberPicker;
//    private TextView rangebarValue;

    EditText namaPenerima;
    EditText alamatPengiriman;
    EditText nomorHandphone;

    TextView orderAmount;
    TextView totalPayment;
    TextView stockQuantity;
    TextView stockPrice;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_detail_order, container, false);

        final View cardDetailOrder = view.findViewById(id.limakilo.www.bawang.R.id.card_detail_order);
        final View cardInputOrder = view.findViewById(id.limakilo.www.bawang.R.id.card_input_order);

//        rangebar = (RangeBar) view.findViewById(id.limakilo.www.bawang.R.id.rangebar);
//        rangebarValue = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.rangebar_value);

        numberPicker = (CustomNumberPicker) view.findViewById(R.id.numberPicker);

        final NestedScrollView scrollview = (NestedScrollView) view.findViewById(id.limakilo.www.bawang.R.id.nested_scroll_view);

        orderAmount = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.order_amount);
        stockQuantity = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.stock_quantity);
        stockPrice = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.stock_price);
        totalPayment = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.total_payment);

        namaPenerima = (EditText) view.findViewById(id.limakilo.www.bawang.R.id.edit_nama_penerima);
        alamatPengiriman = (EditText) view.findViewById(id.limakilo.www.bawang.R.id.edit_alamat_penerima);
        nomorHandphone = (EditText) view.findViewById(id.limakilo.www.bawang.R.id.edit_nomor_telepon);

        namaPenerima.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (checkBlankOrderForm(namaPenerima)){
//                        scrollview.scrollTo(0, namaPenerima.getTop());
                        nomorHandphone.requestFocus();
                        handled = true;
                    }
                }
                return handled;
            }
        });

        nomorHandphone.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (checkBlankOrderForm(nomorHandphone)){
//                        scrollview.scrollTo(0, nomorHandphone.getTop());
                        alamatPengiriman.requestFocus();
                        handled = true;
                    }
                }
                return handled;
            }
        });

//        alamatPengiriman.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                boolean handled = false;
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    ((DetailOrderActivity)getActivity()).showConfirmDialog();
//                    ((DetailOrderActivity)getActivity()).hideSoftKeyboard();
//                    handled = true;
//                }
//                return handled;
//            }
//        });

        Button buttonPesan = (Button) view.findViewById(id.limakilo.www.bawang.R.id.btn_pesan);
        final Button buttonDetailPenerima = (Button) view.findViewById(id.limakilo.www.bawang.R.id.btn_detail_penerima);

//        rangebar.setSeekPinByIndex(0);

        stockQuantity.setText(((DetailOrderActivity) getActivity()).getStockModel().getStockQuantity().toString());
        stockPrice.setText(((DetailOrderActivity) getActivity()).getStockModel().getStockPrice().toString());
        int total = Integer.parseInt(String.valueOf(Integer.parseInt(stockQuantity.getText().toString()) *
                Integer.parseInt(stockPrice.getText().toString())));
        totalPayment.setText(((Integer) total).toString());

        numberPicker.setOnValueChangedListener(this);

//        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
//            @Override
//            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex,
//                                              int rightPinIndex,
//                                              String leftPinValue, String rightPinValue) {
//                rangebarValue.setText(rightPinValue);
//                orderAmount.setText(rightPinValue);
//                int total = Integer.parseInt(rightPinValue.toString()) *
//                        Integer.parseInt(stockQuantity.getText().toString()) *
//                        Integer.parseInt(stockPrice.getText().toString());
//                totalPayment.setText(((Integer) total).toString());
//            }
//        });

        buttonPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBlankOrderForm(namaPenerima) &&
                        checkBlankOrderForm(nomorHandphone) && checkBlankOrderForm(alamatPengiriman)){
                    //passing data to confirm dialog
                    GetOrderDetailResponseModel.GetOrderDetailResponseData model = new GetOrderDetailResponseModel().new GetOrderDetailResponseData();

                    ((DetailOrderActivity) getActivity()).showConfirmDialog();
                    cardDetailOrder.setVisibility(View.GONE);
                    cardInputOrder.setVisibility(View.VISIBLE);
                }
            }
        });

        buttonDetailPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardDetailOrder.setVisibility(View.VISIBLE);
                cardInputOrder.setVisibility(View.GONE);
//                scrollview.scrollTo(0, buttonDetailPenerima.getTop());
                namaPenerima.requestFocus();
                ((DetailOrderActivity) getActivity()).showSoftKeyboard();
            }
        });

        try{
            String stockId = ((DetailOrderActivity)getActivity()).getStockModel().getStockId().toString();
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
    public void onAPICallSucceed(String caller, RootResponseModel responseModel) {
        Snackbar.make(view, "API call succeed", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError error) {
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
//        APICallManager.getInstance().getStocks(stockId, new Callback<GetStockDetailResponseModel>() {
//            @Override
//            public void success(GetStockDetailResponseModel getStockDetailResponseModel, Response response) {
////                Snackbar.make(view, "API call succeed", Snackbar.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
////                Snackbar.make(view, "API call error", Snackbar.LENGTH_LONG).show();
//            }
//        });
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

    public boolean checkBlankOrderForm(EditText editText){
        if (!editText.getText().toString().isEmpty()){
            editText.setError(null);
            return true;
        }else{
            editText.setError("harap isi data ini");
            return false;
        }
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
            try {
                orderAmount.setText(String.valueOf(newVal));
                int total = newVal * Integer.parseInt(stockQuantity.getText().toString()) *
                        Integer.parseInt(stockPrice.getText().toString());
                totalPayment.setText(((Integer) total).toString());
            }
            catch (Exception e){
                Crashlytics.log(Log.ERROR, TAG, e.getMessage().toString());
            }
    }
}
