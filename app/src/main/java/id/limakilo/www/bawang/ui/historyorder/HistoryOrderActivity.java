package id.limakilo.www.bawang.ui.historyorder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 23/8/15.
 */
public class HistoryOrderActivity extends AppCompatActivity implements APICallListener {

    GetOrderDetailResponseModel.GetOrderDetailResponseData orderModel;
    GetStockDetailResponseModel.GetStockDetailResponseData stockModel;

    MaterialDialog detailOrderDialog;
    MaterialDialog confirmPaymentDialog;
    MaterialDialog finishOrderDialog;

    String totalPayment = "0";

    @Nullable
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.loading_bar) View loadingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(id.limakilo.www.bawang.R.layout.activity_history_order);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(id.limakilo.www.bawang.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = "Pesanan";
        collapsingToolbarLayout.setTitle(itemTitle);

        orderModel = new GetOrderDetailResponseModel().new GetOrderDetailResponseData();

        boolean wrapInScrollView = true;
        detailOrderDialog = new MaterialDialog.Builder(this)
                .title("Pesanan Anda")
                .customView(R.layout.dialog_confirm_order_custom_view, wrapInScrollView)
                .positiveText("Tutup")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        detailOrderDialog.hide();
                    }
                })
                .build();


        confirmPaymentDialog = new MaterialDialog.Builder(this)
                .title("Pesanan Anda")
                .customView(R.layout.dialog_confirm_payment_custom_view, wrapInScrollView)
                .positiveText("Transfer Sudah Dilakukan")
                .negativeText("Transfer Nanti")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.hide();
                        confirmPayment();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        super.onNegative(dialog);
                        dialog.hide();
                    }
                })

                .build();

        finishOrderDialog = new MaterialDialog.Builder(this)
                .content("Terima kasih sudah berbelanja, mari ramaikan gerakan #yukbelanjakepetani bersama #limakilo :)")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.hide();
                        showLoadingBar();
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(intent);
                                hideLoadingBar();
                                handler.removeCallbacks(this);
                                HistoryOrderActivity.this.finish();
                            }
                        }, 300L);
                    }
                }).build();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showDetailOrderDialog(){
        try{
            String[] timestamp = orderModel.getOrderDate().toString().split("T");

            View dialogView = detailOrderDialog.getCustomView();
            ((TextView)dialogView.findViewById(R.id.dialog_id_pesanan)).setText(orderModel.getOrderId().toString());
            ((TextView)dialogView.findViewById(R.id.dialog_tanggal_pesanan)).setText(timestamp[0]+" "+timestamp[1].substring(0, 5));
            ((TextView)dialogView.findViewById(R.id.dialog_nama_penerima)).setText(
                    PreferencesManager.getAsString(this, PreferencesManager.FIRST_NAME).toString()+" "+PreferencesManager.getAsString(this, PreferencesManager.LAST_NAME).toString());
            ((TextView)dialogView.findViewById(R.id.dialog_jumlah_pesanan)).setText(orderModel.getOrderQuantity().toString() + " x "
                +orderModel.getStockQuantity().toString()+" kg"
            );
            ((TextView)dialogView.findViewById(R.id.dialog_harga_pesanan)).setText(
                    "Rp. "+decimalFormat(Double.valueOf(
                            Float.valueOf(orderModel.getStockPrice().toString())
                                    * Float.valueOf(orderModel.getStockQuantity().toString())))+",-"
            );
            ((TextView)dialogView.findViewById(R.id.dialog_kode_transfer)).setText("Rp. "+orderModel.getOrderPaymentCode().toString()+",-");
            ((TextView)dialogView.findViewById(R.id.dialog_ongkos_kirim)).setText(
                    "Rp. "+decimalFormat(Double.valueOf(orderModel.getOrderShipmentCost()))+",-"
            );
            ((TextView)dialogView.findViewById(R.id.dialog_total)).setText("Rp. "+totalPayment+",-");
            ((TextView)dialogView.findViewById(R.id.dialog_status_pesanan)).setText(orderModel.getOrderStatus().toString());

            detailOrderDialog.show();
        }
        catch(Exception e){
            Crashlytics.logException(e);
        }
    }

    public void showConfirmOrderDialog(){
        try{
            View dialogView = confirmPaymentDialog.getCustomView();

            ((TextView)dialogView.findViewById(R.id.dialog_total_payment)).setText(totalPayment);
            ((TextView)dialogView.findViewById(R.id.dialog_nama_rekening)).setText(stockModel.getSellerBankAccountName());
            ((TextView)dialogView.findViewById(R.id.dialog_bank_rekening)).setText(stockModel.getSellerBankName());
            ((TextView)dialogView.findViewById(R.id.dialog_nomor_rekening)).setText(stockModel.getSellerBankAccount());

            confirmPaymentDialog.show();
        }
        catch(Exception e){
            Crashlytics.logException(e);
        }

    }

    public GetOrderDetailResponseModel.GetOrderDetailResponseData getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(GetOrderDetailResponseModel.GetOrderDetailResponseData orderModel) {
        this.orderModel = orderModel;
    }

    public void retrieveOrderDetail(String orderId, String totalPayment){
        this.totalPayment = String.valueOf(Double.parseDouble(totalPayment));
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(this));
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETORDER;
        APICallManager.getInstance().getOrders(orderId, new retrofit.Callback<GetOrderDetailResponseModel>() {
            @Override
            public void success(GetOrderDetailResponseModel getOrderDetailResponseModel, Response response) {
                onAPICallSucceed(route, getOrderDetailResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                onAPICallFailed(route, error);
            }
        });
    }

    public void retrievePaymentDetail(String orderId, String stockId, String totalPayment){
        this.totalPayment = totalPayment;
        orderModel.setOrderId(Integer.valueOf(orderId));
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(this));
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETSTOCK;
        APICallManager.getInstance().getStocks(stockId, new Callback<GetStockDetailResponseModel>() {
            @Override
            public void success(GetStockDetailResponseModel getStockDetailResponseModel, Response response) {
                onAPICallSucceed(route, getStockDetailResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                onAPICallFailed(route, error);
            }
        });
    }


    public void confirmPayment(){
        showLoadingBar();
        String orderId = orderModel.getOrderId().toString();
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(this));
        final APICallManager.APIRoute route = APICallManager.APIRoute.CONFIRMORDER;
        APICallManager.getInstance(PreferencesManager.getAuthToken(this)).postOrders(
                orderId, totalPayment.toString(),
                capitalize(PreferencesManager.getAsString(this, PreferencesManager.FIRST_NAME))
                        +" "+ capitalize(PreferencesManager.getAsString(this, PreferencesManager.LAST_NAME)),
                new Callback<PostOrderConfirmResponseModel>() {
                    @Override
                    public void success(PostOrderConfirmResponseModel getStockDetailResponseModel, Response response) {
                        onAPICallSucceed(route, getStockDetailResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        onAPICallFailed(route, error);
                    }
                });
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        hideLoadingBar();
        if (endPoint == APICallManager.APIRoute.GETORDER){
            orderModel = ((GetOrderDetailResponseModel)responseModel).getData().get(0);
            showDetailOrderDialog();
        }else if (endPoint == APICallManager.APIRoute.GETSTOCK){
            stockModel = ((GetStockDetailResponseModel) responseModel).getData().get(0);
            showConfirmOrderDialog();
        }else if (endPoint == APICallManager.APIRoute.CONFIRMORDER){
            finishOrderDialog.show();
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
        hideLoadingBar();
        if (endPoint == APICallManager.APIRoute.GETORDER){

        }else if (endPoint == APICallManager.APIRoute.GETSTOCK){

        }else if (endPoint == APICallManager.APIRoute.CONFIRMORDER){

        }
    }

    public void showLoadingBar(){
        loadingBar.setVisibility(View.VISIBLE);
    }
    public void hideLoadingBar(){
        loadingBar.setVisibility(View.GONE);
    }

    public String capitalize(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    public String decimalFormat(Double number){
        Locale locale  = new Locale("id", "ID");
        String pattern = "###,###.##";

        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        return decimalFormat.format(number);
    }
}
