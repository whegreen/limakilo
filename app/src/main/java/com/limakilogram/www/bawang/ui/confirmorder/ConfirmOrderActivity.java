package com.limakilogram.www.bawang.ui.confirmorder;

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
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.order.PostOrderConfirmResponseModel;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel.*;

/**
 * Created by walesadanto on 30/8/15.
 */
public class ConfirmOrderActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    public static String ORDERID = "order_id";
    public static String ORDERQUANTITY = "order_quantity";
    public static String ORDERAMOUNT = "order_amount";
    public static String ORDERSTATUS = "order_status";
    public static String ORDERTIMESTAMP = "order_timestamp";
    public static String ORDERPAYMENTCODE = "order_payment_code";
    public static String BACKDROP = "backdrop";

    private PostOrderResponseModel.PostOrderResponseData model;
    private MaterialDialog confirmDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new PostOrderResponseModel().new PostOrderResponseData();

        Bundle extras = getIntent().getExtras();

        int backdropResourceId = R.drawable.onion1;
        if(extras == null) {
            //
        } else {
            model.setOrderId(Integer.parseInt(extras.getString(ORDERID)));
            model.setOrderQuantity(extras.getString(ORDERQUANTITY));
            model.setOrderAmount(extras.getString(ORDERAMOUNT));
            model.setOrderStatus(extras.getString(ORDERSTATUS));
            model.setOrderTimestamp(extras.getString(ORDERTIMESTAMP));
            model.setOrderPaymentCode(extras.getString(ORDERPAYMENTCODE));

            backdropResourceId = Integer.parseInt(extras.getString(BACKDROP));

        }

        setContentView(R.layout.activity_confirm_order);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView backdrop = (ImageView) findViewById(R.id.backdrop);

        String itemTitle = "Konfirmasi Pembayaran";
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(itemTitle);

        confirmDialog = new MaterialDialog.Builder(this)
                .title("Confirm Order")
                .content("amount")
                .inputType(InputType.TYPE_CLASS_NUMBER)
                .input("jumlah order", "5", new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        // Do something
                        confirmOrder("80000", "walesa danto");
                        confirmDialog.hide();
                    }
                }).build();

        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                confirmDialog.show();
            }
        });

        Glide.with(this)
                .load(backdropResourceId)
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

    public void confirmOrder(String transferAmount, String accountName){
        APICallManager.getInstance().postOrders(model.getOrderId().toString(), transferAmount, accountName, new Callback<PostOrderConfirmResponseModel>() {
            @Override
            public void success(PostOrderConfirmResponseModel postOrderConfirmResponseModel, Response response) {
                Snackbar.make(findViewById(R.id.main_content), "API call succeed", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Snackbar.make(findViewById(R.id.main_content), "API call failed", Snackbar.LENGTH_LONG).show();
            }
        });
    }
}
