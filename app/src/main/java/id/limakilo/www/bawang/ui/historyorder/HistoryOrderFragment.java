package id.limakilo.www.bawang.ui.historyorder;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderModel;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderPresenter;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderPresenterImpl;
import id.limakilo.www.bawang.ui.historyorder.mvp.HistoryOrderView;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import io.supportkit.ui.ConversationActivity;

/**
 * Created by walesadanto on 1/9/15.
 */
public class HistoryOrderFragment extends Fragment implements HistoryOrderView, SwipeRefreshLayout.OnRefreshListener {

    private HistoryOrderPresenter presenter;

    MaterialDialog detailOrderDialog;
    MaterialDialog confirmPaymentDialog;
    MaterialDialog finishOrderDialog;
    MaterialDialog shipmentOrderDialog;
    MaterialDialog discardOrderDialog;

    private HistoryOrderModel model;
    private View view;

    @Bind(R.id.loading_bar) View loadingView;
    @Bind(R.id.blank_state) View blankStateView;
    @Bind(R.id.error_state) View errorStateView;
    @Bind(R.id.btn_error_refresh) Button buttonErrorRefresh;
    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.swipe_container) SwipeRefreshLayout swipeView;

    @OnClick(R.id.btn_error_refresh)
    public void OnClickButtonErrorRefresh(){
        presenter.presentState(ViewState.LOAD_ORDERS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(id.limakilo.www.bawang.R.layout.fragment_history_order_list, container, false);
        ButterKnife.bind(this, view);

        presenter = new HistoryOrderPresenterImpl(this);
        model = new HistoryOrderModel();

        setupOrderRecyclerView();

        boolean wrapInScrollView = true;

        detailOrderDialog = new MaterialDialog.Builder(getActivity())
                .title("Pesanan Anda")
                .customView(R.layout.dialog_confirm_order_custom_view, wrapInScrollView)
                .positiveText("Tutup")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                presenter.presentState(ViewState.IDLE);
                                showDetailOrderDialog(false);
                            }
                        }, 300L);
                    }
                })
                .build();

        confirmPaymentDialog = new MaterialDialog.Builder(getActivity())
                .title("Pesanan Anda")
                .customView(R.layout.dialog_confirm_payment_custom_view, wrapInScrollView)
                .positiveText("Transfer Sudah Dilakukan")
                .negativeText("Transfer Nanti")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                presenter.presentState(ViewState.CONFIRM_ORDER);
                                confirmPaymentDialog.hide();
                            }
                        }, 300L);
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                confirmPaymentDialog.hide();
                            }
                        }, 300L);
                    }
                })
                .build();

        finishOrderDialog = new MaterialDialog.Builder(getActivity())
                .content("Terima kasih sudah berbelanja, mari ramaikan gerakan #yukbelanjakepetani bersama #limakilo :)")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.hide();
                        presenter.presentState(ViewState.LOAD_ORDERS);
                    }
                }).build();

        shipmentOrderDialog= new MaterialDialog.Builder(getActivity())
                .content("Pesanan dalam proses pengiriman")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.hide();
                    }
                }).build();

        discardOrderDialog = new MaterialDialog.Builder(getActivity())
                .content("Pesanan telah dibatalkan")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.hide();
                    }
                }).build();


        swipeView.setOnRefreshListener(this);
        swipeView.setColorSchemeColors(R.color.color_primary_dark, R.color.color_primary, R.color.color_accent);

        return view;
    }

    public void setupOrderRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
        recyclerView.setAdapter(new HistoryOrderRecyclerViewAdapter(getActivity(), model.getOrderList(), presenter, model));
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.presentState(ViewState.LOAD_ORDERS);
    }

    @Override
    public void showState(ViewState state) {
        switch (state){
            case LOAD_ORDERS:
                setupOrderRecyclerView();
                break;
            case SHOW_ORDERS:
                setupOrderRecyclerView();
                showIdleState();
                break;
            case BLANK_STATE:
                showBlankState();
                break;
            case API_ERROR:
                showAPIError();
                showErrorState();
                break;
            case SHOW_DETAIL_ORDER:
                showDetailOrderDialog(true);
                break;
            case SHOW_CONFIRM_DIALOG:
                showConfirmOrderDialog();
                break;
            case SHOW_FINISH_DIALOG:
                finishOrderDialog.show();
                break;
            case SHOW_SHIPMENT_DIALOG:
                shipmentOrderDialog.show();
                break;
            case SHOW_DISCARD_DIALOG:
                discardOrderDialog.show();
                break;
            case IDLE:
                showIdleState();
                break;
            case LOADING:
                showLoadingView();
                break;
            default:

                break;
        }
    }

    private void showLoadingView(){
        loadingView.setVisibility(View.VISIBLE);
        errorStateView.setVisibility(View.GONE);
        blankStateView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showBlankState(){
        loadingView.setVisibility(View.GONE);
        errorStateView.setVisibility(View.GONE);
        blankStateView.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showErrorState(){
        loadingView.setVisibility(View.GONE);
        errorStateView.setVisibility(View.VISIBLE);
        blankStateView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
    }

    private void showIdleState(){
        loadingView.setVisibility(View.GONE);
        errorStateView.setVisibility(View.GONE);
        blankStateView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        if (swipeView.isRefreshing()){
            swipeView.setRefreshing(false);
        }
    }

    @Override
    public String doRetrieveAuthentification(){
        return PreferencesManager.getAuthToken(getActivity());
    }

//    private void showLoadingState(boolean state){
//        if (state){
//            ((CampaignDetailActivity)getActivity()).showLoadingBar();
//        } else{
//            ((CampaignDetailActivity)getActivity()).hideLoadingBar();
//        }
//    }

    private void showAPIError(){
        Snackbar.make(recyclerView, "Server kami bermasalah...", Snackbar.LENGTH_LONG)
                .setAction("Lapor", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showSupportKit();
                    }
                }).show();
    }

    public void showSupportKit(){
        ConversationActivity.show(getActivity());
    }

    @Override
    public HistoryOrderModel doRetrieveModel() {
        return model;
    }

    public void showDetailOrderDialog(boolean state){
        presenter.presentState(ViewState.IDLE);

        if (state){
            try{
                String[] timestamp = model.getOrderDetailModel().getOrderDate().toString().split("T");

                View dialogView = detailOrderDialog.getCustomView();
                ((TextView)dialogView.findViewById(R.id.dialog_id_pesanan)).setText(model.getOrderDetailModel().getOrderId().toString());
                ((TextView)dialogView.findViewById(R.id.dialog_tanggal_pesanan)).setText(timestamp[0]+" "+timestamp[1].substring(0, 5));
                ((TextView)dialogView.findViewById(R.id.dialog_nama_penerima)).setText(
                        doRetrieveFullname()
                );
                ((TextView)dialogView.findViewById(R.id.dialog_jumlah_pesanan)).setText(model.getOrderDetailModel().getOrderQuantity().toString() + " x "
                                +model.getOrderDetailModel().getStockQuantity().toString()+" kg"
                );
                ((TextView)dialogView.findViewById(R.id.dialog_harga_pesanan)).setText(
                        "Rp. "+model.decimalFormat(Double.valueOf(
                                Float.valueOf(model.getOrderDetailModel().getStockPrice().toString())
                                        * Float.valueOf(model.getOrderDetailModel().getStockQuantity().toString())))+",-"
                );
                ((TextView)dialogView.findViewById(R.id.dialog_kode_transfer)).setText("Rp. "+model.getOrderDetailModel().getOrderPaymentCode().toString()+",-");
                ((TextView)dialogView.findViewById(R.id.dialog_ongkos_kirim)).setText(
                        "Rp. "+model.decimalFormat(Double.valueOf(model.getOrderDetailModel().getOrderShipmentCost()))+",-"
                );
                ((TextView)dialogView.findViewById(R.id.dialog_total)).setText("Rp. "+model.getTotalPayment()+",-");

                String status = "dalam pengiriman";
                if (model.getOrderDetailModel().getOrderStatus().toString().equalsIgnoreCase("order_processed")){
                    status = "pesanan diproses";
                } else if (model.getOrderDetailModel().getOrderStatus().toString().equalsIgnoreCase("order_paid")){
                    status = "konfirmasi pembayaran";
                } else if (model.getOrderDetailModel().getOrderStatus().toString().equalsIgnoreCase("order_verified")){
                    status = "verifikasi pembayaran";
                } else if (model.getOrderDetailModel().getOrderStatus().toString().equalsIgnoreCase("order_shipment")){
                    status = "pesanan dikirim";
                } else if (model.getOrderDetailModel().getOrderStatus().toString().equalsIgnoreCase("order_discard")){
                    status = "pesanan dibatalkan";
                }

                ((TextView)dialogView.findViewById(R.id.dialog_status_pesanan)).setText(status);

                detailOrderDialog.show();

            }
            catch(Exception e){
                Crashlytics.logException(e);
            }
        }else{
            detailOrderDialog.hide();
        }

    }

    public void showConfirmOrderDialog(){
        try{
            View dialogView = confirmPaymentDialog.getCustomView();

            ((TextView)dialogView.findViewById(R.id.dialog_total_payment)).setText(model.getTotalPayment());
            ((TextView)dialogView.findViewById(R.id.dialog_nama_rekening)).setText(model.getStockDetailModel().getSellerBankAccountName());
            ((TextView)dialogView.findViewById(R.id.dialog_bank_rekening)).setText(model.getStockDetailModel().getSellerBankName());
            ((TextView)dialogView.findViewById(R.id.dialog_nomor_rekening)).setText(model.getStockDetailModel().getSellerBankAccount());

            confirmPaymentDialog.show();
        }
        catch(Exception e){
            Crashlytics.logException(e);
        }
    }

    @Override
    public String doRetrieveFullname(){
        String firstName = PreferencesManager.getAsString(getActivity(), PreferencesManager.FIRST_NAME);
        String lastName = PreferencesManager.getAsString(getActivity(), PreferencesManager.LAST_NAME);
        if (firstName == null){
            firstName = "";
        }
        if (lastName == null){
            lastName = "";
        }
        return firstName+" "+lastName;
    }

    @Override
    public int doRetrieveColor(int color){
        return getResources().getColor(color);
    }


    @Override
    public void onRefresh() {
        presenter.presentState(ViewState.LOAD_ORDERS);
//        new Handler().postDelayed(new Runnable() {
//            @Override public void run() {
//                swipeView.setRefreshing(false);
//            }
//        }, 5000);

    }
}
