package com.limakilogram.www.bawang.ui.confirmorder;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;

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
}
