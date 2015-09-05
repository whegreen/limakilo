package com.limakilogram.www.bawang.ui.detailorder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.confirmorder.ConfirmOrderActivity;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;
import com.limakilogram.www.bawang.util.api.stock.GetStockDetailResponseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 30/8/15.
 */
public class DetailOrderActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static final String STOCKID = "stock_id";
    public static final String STOCKNAME = "stock_name";
    public static final String STOCKQUANTITY = "stock_quantity";
    public static final String STOCKPRICE = "stock_price";
    public static final String SELLERID = "seller_id";
    public static final String SELLERNAME = "seller_name";
    public static final String SELLERADDRESS = "seller_address";
    public static final String SELLERAVAURL = "seller_avatar_url";
    public static final String SELLERREPUTATION = "seller_reputation";
    public static final String SELLERCITY = "seller_city";

    private GetStockDetailResponseModel model;

    private MaterialDialog confirmDialog;
    ImageView backdrop;
    private int orderQuantity = 0;

    private MaterialDialog dialogProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new GetStockDetailResponseModel();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            //
        } else {
            model.setStockId(Integer.parseInt(extras.getString(STOCKID)));
            model.setStockName(extras.getString(STOCKNAME));
            model.setStockQuantity(Integer.parseInt(extras.getString(STOCKQUANTITY)));
            model.setStockPrice(extras.getString(STOCKPRICE));
            model.setSellerId(Integer.parseInt(extras.getString(SELLERID)));
            model.setSellerName(extras.getString(SELLERNAME));
            model.setSellerAvatarUrl(extras.getString(SELLERAVAURL));
            model.setSellerAddress(extras.getString(SELLERADDRESS));
            model.setSellerReputation(extras.getString(SELLERREPUTATION));
            model.setSellerCity(extras.getString(SELLERCITY));
        }

        setContentView(R.layout.activity_detail_order);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        String activityTitle = model.getStockName()+" | Rp."+model.getStockPrice()+"/kg";
        String activityTitle = model.getStockName()+" | "+model.getSellerName();
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(activityTitle);

        backdrop = (ImageView) findViewById(R.id.backdrop);

        confirmDialog = new MaterialDialog.Builder(this)
                .title("Confirm Order")
                .content("Proses pembelian bawang anda akan diproses. \nMari bantu petani bawang indonesia")
                .positiveText("Lanjut")
                .negativeText("Batal")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        setOrderQuantity(1);
                        makeOrder();
                        confirmDialog.hide();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                confirmDialog.show();
            }
        });

        Glide.with(this)
                .load(Integer.parseInt(model.getSellerAvatarUrl()))
                .fitCenter()
                .into(backdrop);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_confirm_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public GetStockDetailResponseModel getModel() {
        return model;
    }

    public void makeOrder(){
        APICallManager.getInstance().postOrders(model.getStockId().toString(), ((Integer)orderQuantity).toString(),
                model.getSellerAddress()+"address", model.getStockPrice(), new Callback<PostOrderResponseModel>() {
            @Override
            public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
                Snackbar.make(findViewById(R.id.main_content), "api call succeed", Snackbar.LENGTH_LONG).show();
                PostOrderResponseModel.PostOrderResponseData data = postOrderResponseModel.getData().get(0);
                openConfirmOrderActivity(data.getOrderId().toString(), data.getOrderQuantity(), data.getOrderAmount(),
                        data.getOrderStatus(), data.getOrderTimestamp(), data.getOrderPaymentCode());
            }

            @Override
            public void failure(RetrofitError error) {
                Snackbar.make(findViewById(R.id.main_content), "api call failed", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void openConfirmOrderActivity(String orderId, String orderQuantity, String orderAmount, String orderStatus,
                                         String orderTimestamp, String orderPaymentCode){

        showDialogProgress();

        Intent intent = new Intent(getBaseContext(), ConfirmOrderActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra(ConfirmOrderActivity.ORDERID, orderId);
        intent.putExtra(ConfirmOrderActivity.ORDERQUANTITY, orderQuantity);
        intent.putExtra(ConfirmOrderActivity.ORDERAMOUNT, orderAmount);
        intent.putExtra(ConfirmOrderActivity.ORDERSTATUS, orderStatus);
        intent.putExtra(ConfirmOrderActivity.ORDERTIMESTAMP, orderTimestamp);
        intent.putExtra(ConfirmOrderActivity.ORDERPAYMENTCODE, orderPaymentCode);
        intent.putExtra(ConfirmOrderActivity.BACKDROP, model.getSellerAvatarUrl());

        getBaseContext().startActivity(intent);
    }

    public void showDialogProgress() {
        dialogProgress = new MaterialDialog.Builder(this)
                .title("contacting server")
                .content("Please wait ...")
                .progress(true, 0)
                .show();
    }

}
