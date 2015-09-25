package id.limakilo.www.bawang.ui.order;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.order.mvp.OrderPresenterImpl;
import id.limakilo.www.bawang.ui.order.mvp.OrderView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.widget.CustomNumberPicker;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 30/8/15.
 */
public class OrderFragment extends Fragment implements OrderView, APICallListener {

    private static final String TAG = "OrderFragment";
    private View view;
    private OrderPresenterImpl presenter;
    private MaterialDialog dialogProgress;
    private static MaterialDialog confirmDialog;
    public CardInputOrderViewHolder cardInputOrderViewHolder;
    public CardInputShipmentViewHolder cardInputShipmentViewHolder;
    public CardOrderResumeViewHolder cardOrderResumeViewHolder;

    @Bind(R.id.nested_scroll_view) NestedScrollView scrollView;

    public static class CardInputOrderViewHolder implements NumberPicker.OnValueChangeListener {
        @Bind(R.id.card_input_order) View cardInputOrder;
        @Bind(R.id.numberPicker) CustomNumberPicker numberPicker;
        @Bind(R.id.order_amount) TextView orderAmount;
        @Bind(R.id.stock_quantity) TextView stockQuantity;
        @Bind(R.id.stock_price) TextView stockPrice;
        @Bind(R.id.total_payment) TextView totalPayment;
        @Bind(R.id.btn_detail_penerima) Button buttonDetailPenerima;

        @OnClick(R.id.btn_detail_penerima)
        public void OnBtnDetailPenerimaClick(View view){
            OrderActivity orderActivity = (OrderActivity) view.getContext();
            orderActivity.getOrderFragment().cardInputShipmentViewHolder.loadUserInfo(view.getContext());
            orderActivity.getOrderFragment().cardInputShipmentViewHolder.cardDetailOrder.setVisibility(View.VISIBLE);
            cardInputOrder.setVisibility(View.GONE);
            orderActivity.getOrderFragment().cardInputShipmentViewHolder.namaPenerima.requestFocus();
            if (!orderActivity.getOrderFragment().cardInputShipmentViewHolder.namaPenerima.getText().toString().isEmpty())
                ((OrderActivity)view.getContext()).showSoftKeyboard();
        }

        public CardInputOrderViewHolder(View view){
            ButterKnife.bind(this, view);
            OrderActivity orderActivity = (OrderActivity) view.getContext();
            stockQuantity.setText(orderActivity.getStockModel().getStockQuantity().toString());
            stockPrice.setText(orderActivity.getStockModel().getStockPrice().toString());
            int total = Integer.parseInt(String.valueOf(Integer.parseInt(stockQuantity.getText().toString()) *
                    Integer.parseInt(stockPrice.getText().toString())));
            totalPayment.setText(((Integer) total).toString());
            numberPicker.setOnValueChangedListener(this);
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

    public static class CardInputShipmentViewHolder{
        @Bind(R.id.card_detail_order) View cardDetailOrder;
        @Bind(R.id.edit_nama_penerima) EditText namaPenerima;
        @Bind(R.id.edit_alamat_penerima) EditText alamatPengiriman;
        @Bind(R.id.edit_nomor_telepon) EditText nomorHandphone;
        @Bind(R.id.spinner_kota_penerima) Spinner kotaPenerima;
        @Bind(R.id.btn_pesan) Button buttonPesan;

        @OnClick(R.id.btn_pesan)
        public void OnBtnPesanClick(View view){

            OrderActivity orderActivity = (OrderActivity) view.getContext();
            if (checkBlankOrderForm(namaPenerima) &&
                    checkBlankOrderForm(nomorHandphone) && checkBlankOrderForm(alamatPengiriman)){
                GetOrderDetailResponseModel.GetOrderDetailResponseData model = new GetOrderDetailResponseModel().new GetOrderDetailResponseData();
                cardDetailOrder.setVisibility(View.GONE);
                orderActivity.getOrderFragment().cardInputOrderViewHolder.cardInputOrder.setVisibility(View.GONE);
                try{
                    //save user
                    saveUserInfo((OrderActivity) view.getContext());
                    //make order
                    saveOrderInfo((OrderActivity) view.getContext());
                }
                catch (Exception e){
                    Crashlytics.logException(e);
                }
            }
        }

        public CardInputShipmentViewHolder(View view){
            ButterKnife.bind(this, view);

            namaPenerima.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        if (checkBlankOrderForm(namaPenerima)) {
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
                        if (checkBlankOrderForm(nomorHandphone)) {
                            alamatPengiriman.requestFocus();
                            handled = true;
                        }
                    }
                    return handled;
                }
            });
        }

        private boolean checkBlankOrderForm(EditText editText){
            if (!editText.getText().toString().isEmpty()){
                editText.setError(null);
                return true;
            }else{
                editText.setError("harap isi data ini");
                return false;
            }
        }

        public void loadUserInfo(Context context){
            try{
                namaPenerima.setText(PreferencesManager.getAsString(context, PreferencesManager.NAME));
                nomorHandphone.setText(PreferencesManager.getAsString(context, PreferencesManager.HANDPHONE));
                alamatPengiriman.setText(PreferencesManager.getAsString(context, PreferencesManager.ADDRESS));
                if ("Bandung".equalsIgnoreCase(PreferencesManager.getAsString(context, PreferencesManager.ADDRESS)))
                {
                    kotaPenerima.setSelection(1, true);
                }
            }
            catch (Exception e){
                Crashlytics.logException(e);
            }
        }

        public void saveUserInfo(OrderActivity orderActivity){
            orderActivity.saveUserInfo(
                    namaPenerima.getText().toString(),
                    nomorHandphone.getText().toString(),
                    alamatPengiriman.getText().toString(),
                    kotaPenerima.getSelectedItem().toString()
            );

            updateUserInfo(orderActivity);
        }

        public void updateUserInfo(final OrderActivity orderActivity) {
            final OrderFragment orderFragment = orderActivity.getOrderFragment();
            APICallManager.getInstance(PreferencesManager.getAuthToken(orderActivity)).putUsers(
                    namaPenerima.getText().toString(),
                    nomorHandphone.getText().toString(),
                    orderActivity.getUserModel().getUserEmail(),
                    kotaPenerima.getSelectedItem().toString(),
                    new retrofit.Callback<PutUserResponseModel>() {
                        String caller = "putUsers";

                        @Override
                        public void success(PutUserResponseModel putUserResponseModel, Response response) {
                            orderFragment.onAPICallSucceed(caller, putUserResponseModel);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            orderFragment.onAPICallFailed(caller, error);
                        }
                    }
            );
        }

        public void saveOrderInfo(OrderActivity orderActivity){
            PostOrderResponseModel.PostOrderResponseData orderModel = orderActivity.getOrderModel();
            orderModel.setOrderQuantity(orderActivity.getOrderFragment().cardInputOrderViewHolder.orderAmount.getText().toString());
            makeOrder(orderActivity);
        }

        public void makeOrder(final OrderActivity orderActivity){
            APICallManager.getInstance(PreferencesManager.getAuthToken(orderActivity)).postOrders(
                    orderActivity.getStockModel().getStockId().toString(),
                    orderActivity.getOrderFragment().cardInputOrderViewHolder.orderAmount.getText().toString(),
                    alamatPengiriman.getText().toString(),
                    orderActivity.getStockModel().getStockPrice(),
                    new retrofit.Callback<PostOrderResponseModel>() {
                        String caller = "postOrder";

                        @Override
                        public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
                            orderActivity.getOrderFragment().onAPICallSucceed(caller, postOrderResponseModel);
                        }

                        @Override
                        public void failure(RetrofitError error) {
                            orderActivity.getOrderFragment().onAPICallFailed(caller, error);
                        }
                    });
        }

    }

    public static class CardOrderResumeViewHolder{

        @Bind(R.id.card_resume_order) View cardResumeOrder;
        @Bind(R.id.dialog_id_pesanan) TextView orderId;
        @Bind(R.id.dialog_tanggal_pesanan) TextView orderDate;
        @Bind(R.id.dialog_nama_penerima) TextView orderShipmentReceiver;
        @Bind(R.id.dialog_jumlah_pesanan) TextView orderQuantity;
        @Bind(R.id.dialog_harga_pesanan) TextView stockPrice;
        @Bind(R.id.dialog_kode_transfer) TextView orderPaymentCode;
        @Bind(R.id.dialog_ongkos_kirim) TextView orderShipmentFee;
        @Bind(R.id.dialog_total) TextView orderAmount;
        @Bind(R.id.dialog_status_pesanan) TextView orderStatus;

        public CardOrderResumeViewHolder(View view){
            ButterKnife.bind(this, view);
        }

        public void updateOrderResume(OrderActivity orderActivity){
            try{
                PostOrderResponseModel.PostOrderResponseData order = orderActivity.getOrderModel();
                GetStockDetailResponseModel.GetStockDetailResponseData stock = orderActivity.getStockModel();

                String[] timestamp = order.getOrderTimestamp().toString().split("T");
                int totalTransfer = (Integer.parseInt(order.getOrderQuantity().toString())
                        *Integer.parseInt(orderActivity.getOrderFragment().cardInputOrderViewHolder.stockPrice
                        .getText().toString()))
                        +Integer.parseInt(order.getOrderPaymentCode());

                orderId.setText(order.getOrderId().toString());
                orderDate.setText(timestamp[0]+" "+timestamp[1].substring(0,5));
                orderShipmentReceiver.setText(PreferencesManager.getAsString(orderActivity, PreferencesManager.NAME).toString());
                orderQuantity.setText(order.getOrderQuantity().toString());
                stockPrice.setText(orderActivity.getOrderFragment().cardInputOrderViewHolder.stockPrice
                        .getText().toString());
                orderPaymentCode.setText(order.getOrderPaymentCode().toString());
                orderShipmentFee.setText("gratis");
                orderAmount.setText(totalTransfer + "");
                orderStatus.setText(order.getOrderStatus().toString());

                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_total_payment)).setText(totalTransfer+"");
                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_nama_rekening)).setText(stock.getSellerBankAccountName().toString());
                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_bank_rekening)).setText(stock.getSellerBankName().toString());
                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_nomor_rekening)).setText(stock.getSellerBankAccount().toString());
            }
            catch (Exception e){
                Crashlytics.logException(e);
            }
        }
    }

    public static class DialogConfirmPaymentViewHolder{
        @Bind(R.id.btn_confirm_payment) Button buttonConfirmPayment;

        @OnClick(R.id.btn_confirm_payment)
        public void onClick(View view) {
            confirmDialog.show();
        }
    }

    public static class DialogFinishPaymentViewHolder{

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        cardInputOrderViewHolder = new CardInputOrderViewHolder(view.findViewById(R.id.card_input_order));
        cardInputShipmentViewHolder = new CardInputShipmentViewHolder(view.findViewById(R.id.card_detail_order));
        cardOrderResumeViewHolder = new CardOrderResumeViewHolder(view.findViewById(R.id.card_resume_order));

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

        ((OrderActivity)view.getContext()).setOrderFragment(this);

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

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        ((OrderActivity)view.getContext()).setOrderFragment(null);
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
            cardInputOrderViewHolder.cardInputOrder.setVisibility(View.GONE);
            cardOrderResumeViewHolder.cardResumeOrder.setVisibility(View.VISIBLE);

            cardOrderResumeViewHolder.updateOrderResume((OrderActivity) getActivity());
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

    public void showDialogProgress() {
        dialogProgress = new MaterialDialog.Builder(getActivity())
                .title("contacting server")
                .content("Please wait ...")
                .progress(true, 0)
                .show();
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




}
