package id.limakilo.www.bawang.ui.historyorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;
import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 23/8/15.
 */
public class HistoryOrderActivity extends AppCompatActivity implements APICallListener {

    GetOrderDetailResponseModel.GetOrderDetailResponseData orderModel;
    MaterialDialog detailOrderDialog;

    @Nullable
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(id.limakilo.www.bawang.R.layout.activity_history_order);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(id.limakilo.www.bawang.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = "Riwayat Pemesanan";
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
        // show detail order with set custom view

        try{
            String[] timestamp = orderModel.getOrderDate().toString().split("T");
            int totalTransfer = Integer.parseInt(orderModel.getOrderAmount());

            View dialogView = detailOrderDialog.getCustomView();
            ((TextView)dialogView.findViewById(R.id.dialog_id_pesanan)).setText(orderModel.getOrderId().toString());
            ((TextView)dialogView.findViewById(R.id.dialog_tanggal_pesanan)).setText(timestamp[0]+" "+timestamp[1].substring(0,5));
            ((TextView)dialogView.findViewById(R.id.dialog_nama_penerima)).setText(PreferencesManager.getAsString(this, PreferencesManager.NAME).toString());
            ((TextView)dialogView.findViewById(R.id.dialog_jumlah_pesanan)).setText(orderModel.getOrderQuantity().toString());
            ((TextView)dialogView.findViewById(R.id.dialog_harga_pesanan)).setText(orderModel.getStockPrice().toString());
            ((TextView)dialogView.findViewById(R.id.dialog_kode_transfer)).setText(orderModel.getOrderPaymentCode().toString());
            ((TextView)dialogView.findViewById(R.id.dialog_ongkos_kirim)).setText("gratis");
            ((TextView)dialogView.findViewById(R.id.dialog_total)).setText(totalTransfer+"");
            ((TextView)dialogView.findViewById(R.id.dialog_status_pesanan)).setText(orderModel.getOrderStatus().toString());

            detailOrderDialog.show();
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

    public void retrieveOrderDetail(String orderId){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(this));
        APICallManager.getInstance().getOrders(orderId, new retrofit.Callback<GetOrderDetailResponseModel>() {
            String caller = "getOrder";
            @Override
            public void success(GetOrderDetailResponseModel getOrderDetailResponseModel, Response response) {
                onAPICallSucceed(caller, getOrderDetailResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                onAPICallFailed(caller, error);
            }
        });
    }

    @Override
    public void onAPICallSucceed(String caller, RootResponseModel responseModel) {
        if (caller.equalsIgnoreCase("getOrder")){
            orderModel = ((GetOrderDetailResponseModel)responseModel).getData().get(0);
            showDetailOrderDialog();
        }else{

        }

    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError retrofitError) {

    }
}
