package com.limakilogram.www.bawang.ui.historyorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.order.PostOrderResponseModel;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 23/8/15.
 */
public class HistoryOrderActivity extends AppCompatActivity {
    public static final String EXTRA_STOCK = "stock_id";
    public static final String EXTRA_NAME = "stock_name";
    public static final String EXTRA_PRICE = "price";
    public static final String EXTRA_QTY = "quantity";

    private String stockId;
    private EditText editText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        stockId = intent.getStringExtra(EXTRA_STOCK);
        String name= intent.getStringExtra(EXTRA_NAME);
        String price = intent.getStringExtra(EXTRA_PRICE);

        setContentView(R.layout.activity_history_order);

        TextView view1 = (TextView) findViewById(R.id.confirm_text_1);
        TextView view3 = (TextView) findViewById(R.id.confirm_text_3);
        TextView view4 = (TextView) findViewById(R.id.confirm_text_4);

        editText = (EditText) findViewById(R.id.editText);

        view1.setText(name);
        view3.setText(price);
        view4.setText("paket");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Konfirmasi Order");

        findViewById(R.id.confirm_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postOrder();
            }
        });
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

    public void postOrder(){
        String stockId = "0";
        String quantity = "0";

        if (!editText.getText().toString().equals("")){
            quantity = editText.getText().toString();
        }

        APICallManager.getInstance().postOrder5kg(stockId, quantity, new Callback<PostOrderResponseModel>() {
            @Override
            public void success(PostOrderResponseModel postOrderResponseModel, Response response) {
                Toast.makeText(getBaseContext(), "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(getBaseContext(), "failed"+error.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
