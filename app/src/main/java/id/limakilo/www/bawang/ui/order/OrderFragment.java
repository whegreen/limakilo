package id.limakilo.www.bawang.ui.order;

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
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.confirmorder.ConfirmOrderActivity;
import id.limakilo.www.bawang.ui.order.mvp.OrderPresenterImpl;
import id.limakilo.www.bawang.ui.order.mvp.OrderView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.widget.CustomNumberPicker;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 30/8/15.
 */
public class OrderFragment extends Fragment implements OrderView, APICallListener, NumberPicker.OnValueChangeListener {

    private static final String TAG = "OrderFragment";
    private View view;
    private OrderPresenterImpl presenter;
    private MaterialDialog dialogProgress;
    private NumberPicker numberPicker;

    EditText namaPenerima;
    EditText alamatPengiriman;
    EditText nomorHandphone;
    Spinner kotaPenerima;

    TextView orderAmount;
    TextView totalPayment;
    TextView stockQuantity;
    TextView stockPrice;

    View cardResumeOrder;
    View cardInputOrder;
    private MaterialDialog confirmDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_order, container, false);

        final View cardDetailOrder = view.findViewById(id.limakilo.www.bawang.R.id.card_detail_order);
        cardInputOrder = view.findViewById(id.limakilo.www.bawang.R.id.card_input_order);
        cardResumeOrder = view.findViewById(R.id.card_resume_order);

        numberPicker = (CustomNumberPicker) view.findViewById(R.id.numberPicker);

        final NestedScrollView scrollview = (NestedScrollView) view.findViewById(id.limakilo.www.bawang.R.id.nested_scroll_view);

        orderAmount = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.order_amount);
        stockQuantity = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.stock_quantity);
        stockPrice = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.stock_price);
        totalPayment = (TextView) view.findViewById(id.limakilo.www.bawang.R.id.total_payment);

        namaPenerima = (EditText) view.findViewById(id.limakilo.www.bawang.R.id.edit_nama_penerima);
        alamatPengiriman = (EditText) view.findViewById(id.limakilo.www.bawang.R.id.edit_alamat_penerima);
        nomorHandphone = (EditText) view.findViewById(id.limakilo.www.bawang.R.id.edit_nomor_telepon);
        kotaPenerima = (Spinner) view.findViewById(R.id.spinner_kota_penerima);

        namaPenerima.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    if (checkBlankOrderForm(namaPenerima)){
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
//                    ((OrderActivity)getActivity()).showOrderDetail();
//                    ((OrderActivity)getActivity()).hidedetailKeyboard();
//                    handled = true;
//                }
//                return handled;
//            }
//        });

        Button buttonPesan = (Button) view.findViewById(id.limakilo.www.bawang.R.id.btn_pesan);
        final Button buttonDetailPenerima = (Button) view.findViewById(id.limakilo.www.bawang.R.id.btn_detail_penerima);
        Button buttonConfirmPayment = (Button) view.findViewById(R.id.btn_confirm_payment);

        stockQuantity.setText(((OrderActivity) getActivity()).getStockModel().getStockQuantity().toString());
        stockPrice.setText(((OrderActivity) getActivity()).getStockModel().getStockPrice().toString());
        int total = Integer.parseInt(String.valueOf(Integer.parseInt(stockQuantity.getText().toString()) *
                Integer.parseInt(stockPrice.getText().toString())));
        totalPayment.setText(((Integer) total).toString());

        numberPicker.setOnValueChangedListener(this);

        buttonPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBlankOrderForm(namaPenerima) &&
                        checkBlankOrderForm(nomorHandphone) && checkBlankOrderForm(alamatPengiriman)){
                    //passing data to confirm dialog
                    GetOrderDetailResponseModel.GetOrderDetailResponseData model = new GetOrderDetailResponseModel().new GetOrderDetailResponseData();

//                    ((OrderActivity) getActivity()).showOrderDetail();
                    cardDetailOrder.setVisibility(View.GONE);
                    cardInputOrder.setVisibility(View.GONE);

                    try{
                        //save user
                        saveUserInfo();

                        //make order
                        saveOrderInfo();
                    }
                    catch (Exception e){
                        Crashlytics.logException(e);
                    }

                }
            }
        });

        buttonDetailPenerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadUserInfo();
                cardDetailOrder.setVisibility(View.VISIBLE);
                cardInputOrder.setVisibility(View.GONE);
                namaPenerima.requestFocus();
                ((OrderActivity)getActivity()).showSoftKeyboard();
            }
        });

//        try{
//            String stockId = ((OrderActivity)getActivity()).getStockModel().getStockId().toString();
//            retrieveDetailOrder(stockId);
//
//        }catch (Exception e){
//            Crashlytics.log(Log.ERROR, "api call", e.toString());
//        }

        boolean wrapInScrollView = true;
        confirmDialog = new MaterialDialog.Builder(getActivity())
                .title("Konfirmasi Pembayaran")
                .customView(R.layout.dialog_confirm_payment_custom_view, wrapInScrollView)
                .positiveText("Transfer sudah dilakukan")
                .negativeText("Transfer nanti")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        confirmOrder();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();

        buttonConfirmPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.show();
            }
        });

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
        if (caller.equalsIgnoreCase("postConfirmOrders")){
            confirmDialog.hide();
            ((OrderActivity)getActivity()).showDialogFinishOrder();
        }
        else if (caller.equalsIgnoreCase("postOrder")){
            PostOrderResponseModel.PostOrderResponseData data = ((PostOrderResponseModel)responseModel).getData().get(0);
            ((OrderActivity)getActivity()).setOrderModel(data);
            cardInputOrder.setVisibility(View.GONE);
            cardResumeOrder.setVisibility(View.VISIBLE);

            updateOrderResume();
        }
    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError error) {
        if (caller.equalsIgnoreCase("postConfirmOrders")){
            Snackbar.make(view, "Konfirmasi pembayaran gagal", Snackbar.LENGTH_LONG)
                    .setAction("Ulangi", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            confirmOrder();
                        }
                    }).show();
        }
    }

    public void updateOrderResume(){
        try{
            PostOrderResponseModel.PostOrderResponseData order = ((OrderActivity) getActivity()).getOrderModel();

            String[] timestamp = order.getOrderTimestamp().toString().split("T");
            int totalTransfer = (Integer.parseInt(order.getOrderQuantity().toString())
                    *Integer.parseInt(stockPrice.getText().toString()))
                    +Integer.parseInt(order.getOrderPaymentCode());

            ((TextView)cardResumeOrder.findViewById(R.id.dialog_id_pesanan)).setText(order.getOrderId().toString());
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_tanggal_pesanan)).setText(timestamp[0]+" "+timestamp[1].substring(0,5));
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_nama_penerima)).setText(PreferencesManager.getAsString(getActivity(), PreferencesManager.NAME).toString());
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_jumlah_pesanan)).setText(order.getOrderQuantity().toString());
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_harga_pesanan)).setText(stockPrice.getText().toString());
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_kode_transfer)).setText(order.getOrderPaymentCode().toString());
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_ongkos_kirim)).setText("gratis");
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_total)).setText(totalTransfer+"");
            ((TextView)cardResumeOrder.findViewById(R.id.dialog_status_pesanan)).setText(order.getOrderStatus().toString());

            ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_total_payment))
                    .setText(totalTransfer+"");
        }
        catch (Exception e){
            Crashlytics.logException(e);
        }

    }

    public void showDialogProgress() {
        dialogProgress = new MaterialDialog.Builder(getActivity())
                .title("contacting server")
                .content("Please wait ...")
                .progress(true, 0)
                .show();
    }

    public void updateUserInfo() {
        OrderActivity activity = (OrderActivity) getActivity();
        APICallManager.getInstance(PreferencesManager.getAuthToken(activity)).putUsers(
                namaPenerima.getText().toString(),
                nomorHandphone.getText().toString(),
                ((OrderActivity) activity).getUserModel().getUserEmail(),
                kotaPenerima.getSelectedItem().toString(),
                new retrofit.Callback<PutUserResponseModel>() {
                    String caller = "putUsers";
                    @Override
                    public void success(PutUserResponseModel putUserResponseModel, Response response) {
                        onAPICallSucceed(caller, putUserResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        onAPICallFailed(caller, error);
                    }
                }
        );
    }


    public void saveUserInfo(){
        ((OrderActivity)getActivity()).saveUserInfo(
                namaPenerima.getText().toString(),
                nomorHandphone.getText().toString(),
                alamatPengiriman.getText().toString(),
                kotaPenerima.getSelectedItem().toString()
        );

        updateUserInfo();
    }

    public void saveOrderInfo(){
        PostOrderResponseModel.PostOrderResponseData orderModel = ((OrderActivity) getActivity()).getOrderModel();
        orderModel.setOrderQuantity(orderAmount.getText().toString());
        makeOrder();
    }

    public void makeOrder(){
        OrderActivity activity = (OrderActivity) getActivity();
        APICallManager.getInstance(PreferencesManager.getAuthToken(activity)).postOrders(
                ((OrderActivity) getActivity()).getStockModel().getStockId().toString(),
                orderAmount.getText().toString(),
                alamatPengiriman.getText().toString(),
                ((OrderActivity) getActivity()).getStockModel().getStockPrice(),
                new retrofit.Callback<PostOrderResponseModel>() {
                    String caller = "postOrder";

                    @Override
                    public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
                        onAPICallSucceed(caller, postOrderResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        onAPICallFailed(caller, error);
                    }
                });
    }

    public void confirmOrder(){
        OrderActivity activity = (OrderActivity) getActivity();
        APICallManager.getInstance(PreferencesManager.getAuthToken(activity)).postOrders(
                activity.getOrderModel().getOrderId().toString(), "amount transfer", PreferencesManager.getAsString(activity, PreferencesManager.NAME),
                new retrofit.Callback<PostOrderConfirmResponseModel>() {
                    String caller = "postConfirmOrders";

                    @Override
                    public void success(PostOrderConfirmResponseModel postOrderConfirmResponseModel, Response response) {
                        onAPICallSucceed(caller, postOrderConfirmResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        onAPICallFailed(caller, error);
                    }
                }
        );
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

    public void showOrderProcess(OrderProcessState state){
        switch (state) {
            case ORDER :

                break;
            case PAYMENT :

                break;
            default:

                break;
        }


    }

    public void loadUserInfo(){
        try{
            namaPenerima.setText(PreferencesManager.getAsString(getActivity(), PreferencesManager.NAME));
            nomorHandphone.setText(PreferencesManager.getAsString(getActivity(), PreferencesManager.HANDPHONE));
            alamatPengiriman.setText(PreferencesManager.getAsString(getActivity(), PreferencesManager.ADDRESS));
            if ("Bandung".equalsIgnoreCase(PreferencesManager.getAsString(getActivity(), PreferencesManager.ADDRESS)))
            {
                kotaPenerima.setSelection(1, true);
            }
        }
        catch (Exception e){
            Crashlytics.logException(e);
        }
    }
}
