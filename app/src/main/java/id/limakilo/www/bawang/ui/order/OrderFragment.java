package id.limakilo.www.bawang.ui.order;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.NestedScrollView;
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
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.ui.order.mvp.OrderModel;
import id.limakilo.www.bawang.ui.order.mvp.OrderPresenterImpl;
import id.limakilo.www.bawang.ui.order.mvp.OrderView;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.common.TextFormatter;
import id.limakilo.www.bawang.util.widget.CustomNumberPicker;

/**
 * Created by walesadanto on 30/8/15.
 */

public class OrderFragment extends Fragment implements OrderView{

    private static final String TAG = "OrderFragment";
    private View view;
    private OrderPresenterImpl presenter;

    private MaterialDialog confirmDialog;
    private MaterialDialog dialogFinishOrder;
    private MaterialDialog outOfStockDialog;

    private boolean isKeyboardShown;
    public OrderModel model;

    public CardInputOrderViewHolder cardInputOrderViewHolder;
    public CardInputShipmentViewHolder cardInputShipmentViewHolder;
    public CardOrderResumeViewHolder cardOrderResumeViewHolder;

    @Bind(R.id.nested_scroll_view) NestedScrollView scrollView;
    @Bind(R.id.loading_bar) View loadingView;

    @Override
    public void showState(ViewState state) {
        switch (state){
            case LOADING:
                loadingView.setVisibility(View.VISIBLE);
                break;
            case IDLE:
                loadingView.setVisibility(View.GONE);
                ((OrderActivity)getActivity()).hideLoadingView();
                break;
            case ORDER_PAID:
                showConfirmDialog(false);
                showFinishOrderDialog();
                break;
            case SHOW_STOCK_LIST:
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().getApplicationContext().startActivity(intent);
                        ((OrderActivity)getActivity()).hideLoadingView();
                        handler.removeCallbacks(this);
                        getActivity().finish();
                    }
                }, 300L);
                break;
            case POST_ORDER:
                showPostOrder();
                break;
            case ORDER_PROCESSED:
                showOrderProcessed();
                break;
            case LOAD_STOCK:
                showOrderProcessed();
                break;
            case SHOW_DETAIL_ORDER:
                showDetailOrder();
                break;
            case STOCK_NOT_AVAILABLE:
                showOutOfStockDialog(true);
                break;
            case ERROR_CONFIRM_ORDER:
                showReconfirmOrder();
                break;
            case TOGGLE_KEYBOARD:
                if (isKeyboardShown){
                    showSoftKeyboard(false);
                }else{
                    showSoftKeyboard(true);
                }
                break;
            default:
                break;
        }
    }

    private void showDetailOrder(){
        OrderActivity orderActivity = (OrderActivity) getActivity();
        orderActivity.getOrderFragment().cardInputShipmentViewHolder.loadUserInfo(view.getContext());
        orderActivity.getOrderFragment().cardInputOrderViewHolder.cardInputOrder.setVisibility(View.GONE);
        orderActivity.getOrderFragment().cardInputShipmentViewHolder.cardDetailOrder.setVisibility(View.VISIBLE);
        orderActivity.getOrderFragment().cardInputShipmentViewHolder.namaDepanPenerima.requestFocus();
        if (!orderActivity.getOrderFragment().cardInputShipmentViewHolder.namaDepanPenerima.getText().toString().isEmpty())
            ((OrderActivity)view.getContext()).showSoftKeyboard();
    }

    private void showSoftKeyboard(boolean state){
        if (state){
            ((OrderActivity)view.getContext()).showSoftKeyboard();
        }
        else{
            ((OrderActivity)view.getContext()).hideSoftKeyboard();
        }
        isKeyboardShown = !state;
    }

    private void showPostOrder(){
        presenter.presentState(ViewState.POST_ORDER);
    }

    private void showOrderProcessed(){
        cardInputOrderViewHolder.cardInputOrder.setVisibility(View.GONE);
        cardOrderResumeViewHolder.cardResumeOrder.setVisibility(View.VISIBLE);
        cardOrderResumeViewHolder.updateOrderResume((OrderActivity) getActivity());
    }


    private void showFinishOrderDialog(){
        dialogFinishOrder.show();
    }

    private void showOutOfStockDialog(boolean state){
        if (state){
            outOfStockDialog.show();
        }else{
            outOfStockDialog.hide();
        }
    }

    private void showConfirmDialog(boolean state){
        if (state){
            confirmDialog.show();
        }else{
            confirmDialog.hide();
        }
    }
    private void showReconfirmOrder(){
        Snackbar.make(view, "Konfirmasi pembayaran gagal", Snackbar.LENGTH_LONG)
                .setAction("Ulangi", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.presentState(OrderView.ViewState.CONFIRM_PAYMENT);
                    }
                }).show();
    }

    @Override
    public void doUpdateModel(RootResponseModel responseModel) {

    }

    @Override
    public OrderModel doRetrieveModel() {
        return model;
    }

    @Override
    public String doRetrieveUserAuth(){
        return PreferencesManager.getAuthToken(getActivity());
    }

    @Override
    public String doRetrieveUserFullname(){
        return PreferencesManager.getAsString(getActivity(), PreferencesManager.FIRST_NAME) + " " + PreferencesManager.getAsString(getActivity(), PreferencesManager.LAST_NAME);
    }

    public class CardInputOrderViewHolder implements NumberPicker.OnValueChangeListener {
        @Bind(R.id.card_input_order) View cardInputOrder;
        @Bind(R.id.numberPicker) CustomNumberPicker numberPicker;
        @Bind(R.id.order_amount) TextView orderAmount;
        @Bind(R.id.stock_quantity) TextView stockQuantity;
        @Bind(R.id.stock_price) TextView stockPrice;
        @Bind(R.id.total_payment) TextView totalPayment;
        @Bind(R.id.btn_detail_penerima) Button buttonDetailPenerima;

        @OnClick(R.id.btn_detail_penerima)
        public void OnBtnDetailPenerimaClick(View view){
            presenter.presentState(ViewState.LOAD_STOCK);
            presenter.presentState(ViewState.LOAD_USER);
        }

        public CardInputOrderViewHolder(View view){
            ButterKnife.bind(this, view);
            OrderActivity orderActivity = (OrderActivity) view.getContext();
            stockQuantity.setText(orderActivity.getStockModel().getStockQuantity().toString());
            stockPrice.setText(orderActivity.getStockModel().getStockPrice().toString());
            Double total = Double.parseDouble(stockQuantity.getText().toString()) *
                    Integer.parseInt(stockPrice.getText().toString());

            totalPayment.setText(TextFormatter.decimalFormat(total));

            numberPicker.setOnValueChangedListener(this);
        }

        @Override
        public void onValueChange(NumberPicker numberPicker, int oldVal, int newVal) {
            try {
                orderAmount.setText(String.valueOf(newVal));
                Double total = newVal * Double.parseDouble(stockQuantity.getText().toString()) *
                        Integer.parseInt(stockPrice.getText().toString());

                totalPayment.setText(TextFormatter.decimalFormat(total));
            }
            catch (Exception e){
                Crashlytics.logException(e);
            }
        }
    }

    public class CardInputShipmentViewHolder{

        @Bind(R.id.card_detail_order) View cardDetailOrder;
        @Bind(R.id.edit_nama_depan_penerima) EditText namaDepanPenerima;
        @Bind(R.id.edit_nama_belakang_penerima) EditText namaBelakangPenerima;
        @Bind(R.id.edit_alamat_penerima) EditText alamatPengiriman;
        @Bind(R.id.edit_nomor_telepon) EditText nomorHandphone;
        @Bind(R.id.edit_email) EditText email;
        @Bind(R.id.spinner_kota_penerima) Spinner kotaPenerima;
        @Bind(R.id.btn_pesan) Button buttonPesan;

        @OnClick(R.id.btn_pesan)
        public void OnBtnPesanClick(View view){
            OrderActivity orderActivity = (OrderActivity) view.getContext();
            if (checkBlankOrderForm(namaDepanPenerima) && checkBlankOrderForm(namaBelakangPenerima) &&
                    checkBlankOrderForm(nomorHandphone) && checkBlankOrderForm(alamatPengiriman)
                    && checkBlankOrderForm(email)){
                try{
                    cardDetailOrder.setVisibility(View.GONE);
                    orderActivity.getOrderFragment().cardInputOrderViewHolder.cardInputOrder.setVisibility(View.GONE);

                    model.getUserModel().setUserFirstName(namaDepanPenerima.getText().toString());
                    model.getUserModel().setUserLastName(namaBelakangPenerima.getText().toString());
                    model.getUserModel().setUserPhone(nomorHandphone.getText().toString());
                    model.getUserModel().setUserEmail(email.getText().toString());
                    model.getUserModel().setUserAddress(alamatPengiriman.getText().toString());
                    model.getUserModel().setUserCity(kotaPenerima.getSelectedItem().toString());

                    presenter.presentState(ViewState.SAVE_USER_INFO);

                    model.getPostOrderModel().setOrderQuantity(cardInputOrderViewHolder.orderAmount.getText().toString());
                    presenter.presentState(ViewState.POST_ORDER);
                }
                catch (Exception e){
                    presenter.presentState(ViewState.IDLE);
                    Crashlytics.logException(e);
                }
            }
        }

        @OnClick(R.id.card_detail_order)
        public void OnCardInputClick(View view){
            showSoftKeyboard(false);
        }

        public CardInputShipmentViewHolder(View view){
            ButterKnife.bind(this, view);

            namaDepanPenerima.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        if (checkBlankOrderForm(namaDepanPenerima)) {
                            namaBelakangPenerima.requestFocus();
                            handled = true;
                        }
                    }
                    return handled;
                }
            });

            namaBelakangPenerima.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        if (checkBlankOrderForm(namaBelakangPenerima)) {
                            email.requestFocus();
                            handled = true;
                        }
                    }
                    return handled;
                }
            });

            email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    boolean handled = false;
                    if (actionId == EditorInfo.IME_ACTION_NEXT) {
                        if (checkBlankOrderForm(email)) {
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
                namaDepanPenerima.setText(model.getUserModel().getUserFirstName());
                namaBelakangPenerima.setText(model.getUserModel().getUserLastName());
                nomorHandphone.setText(model.getUserModel().getUserPhone());
                email.setText(model.getUserModel().getUserEmail());
                alamatPengiriman.setText(model.getUserModel().getUserAddress());

                if ("Bandung".equalsIgnoreCase(model.getUserModel().getUserCity()))
                {
                    kotaPenerima.setSelection(1, true);
                }
            }
            catch (Exception e){
                Crashlytics.logException(e);
            }
        }

    }

    public class CardOrderResumeViewHolder{

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
        @Bind(R.id.btn_confirm_payment) Button buttonConfirmPayment;

        @OnClick(R.id.btn_confirm_payment)
        public void onClick(View view) {
            showConfirmDialog(true);
        }

        public CardOrderResumeViewHolder(View view){
            ButterKnife.bind(this, view);
        }

        public void updateOrderResume(OrderActivity orderActivity){
            try{
                PostOrderResponseModel.PostOrderResponseData order = model.getPostOrderModel();
                GetStockDetailResponseModel.GetStockDetailResponseData stock = model.getStockDetailModel();

                String[] timestamp = order.getOrderTimestamp().toString().split("T");

                orderId.setText(order.getOrderId().toString());
                orderDate.setText(timestamp[0]+" "+timestamp[1].substring(0,5));
                orderShipmentReceiver.setText(
                        TextFormatter.capitalize(PreferencesManager.getAsString(orderActivity, PreferencesManager.FIRST_NAME).toString()) +
                        " " + TextFormatter.capitalize(PreferencesManager.getAsString(orderActivity, PreferencesManager.LAST_NAME).toString()));
                orderQuantity.setText((Integer.parseInt(order.getOrderQuantity()) * stock.getStockQuantity()) + " kg");

                Double hargaPesanan = (Double.valueOf(orderActivity.getOrderFragment().cardInputOrderViewHolder.stockPrice
                        .getText().toString())*(Integer.parseInt(order.getOrderQuantity()) * stock.getStockQuantity()));

                Double shipmentCost = Double.valueOf(0);
                if (order.getOrderShipmentCost() != null){
                    shipmentCost = Double.valueOf(order.getOrderShipmentCost());
                }

                stockPrice.setText("Rp. "+TextFormatter.decimalFormat(hargaPesanan)+ ",-");
                orderPaymentCode.setText("Rp. "+order.getOrderPaymentCode().toString()+ ",-");
                orderShipmentFee.setText("Rp. "+TextFormatter.decimalFormat(shipmentCost)+ ",-");

                Double totalTransfer = hargaPesanan
                        +Double.valueOf(order.getOrderPaymentCode())
                        +shipmentCost;

                String formattedTotal = TextFormatter.decimalFormat(totalTransfer);

                model.getPostOrderModel().setOrderAmount(String.valueOf(totalTransfer.intValue()));

                orderAmount.setText("Rp. "+formattedTotal + ",-");
                orderStatus.setText(order.getOrderStatus().toString());

                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_total_payment)).setText("Rp. " +formattedTotal+",-");
                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_nama_rekening)).setText(stock.getSellerBankAccountName().toString());
                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_bank_rekening)).setText(stock.getSellerBankName().toString());
                ((TextView)confirmDialog.getCustomView().findViewById(R.id.dialog_nomor_rekening)).setText(stock.getSellerBankAccount().toString());
            }
            catch (Exception e){
                Crashlytics.logException(e);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_order, container, false);
        ButterKnife.bind(this, view);

        presenter = new OrderPresenterImpl(this);
        model = new OrderModel();

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
                        presenter.presentState(ViewState.CONFIRM_PAYMENT);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();

        ((OrderActivity) view.getContext()).setOrderFragment(this);

        dialogFinishOrder = new MaterialDialog.Builder(getActivity())
                .content("Terima kasih sudah berbelanja, mari ramaikan gerakan #yukbelanjakepetani bersama #limakilo :)")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        ((OrderActivity)getActivity()).showLoadingView();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().getApplicationContext().startActivity(intent);
                                ((OrderActivity)getActivity()).hideLoadingView();
                                handler.removeCallbacks(this);
                                getActivity().finish();
                            }
                        }, 300L);
                    }
                }).build();

        outOfStockDialog = new MaterialDialog.Builder(getActivity())
                .content("Mohon maaf, saat ini stock bawang kami sudah habis :( tapi anda masih bisa meramaikan gerakan #yukbelanjakepetani di pre-order #limakilo selanjutnya")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                getActivity().getApplicationContext().startActivity(intent);
                                ((OrderActivity) getActivity()).hideLoadingView();
                                handler.removeCallbacks(this);
                                getActivity().finish();
                            }
                        }, 300L);
                        dialog.hide();
                        presenter.presentState(ViewState.LOADING);
                    }
                })
                .build();

        model.setStockDetailModel(((OrderActivity)getActivity()).getStockModel());
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

}
