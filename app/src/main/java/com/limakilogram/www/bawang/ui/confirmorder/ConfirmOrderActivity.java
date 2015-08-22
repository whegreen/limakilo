package com.limakilogram.www.bawang.ui.confirmorder;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.limakilogram.www.bawang.R;

/**
 * Created by walesadanto on 23/8/15.
 */
public class ConfirmOrderActivity extends AppCompatActivity {
    public static final String EXTRA_STOCK = "stock_id";
    public static final String EXTRA_NAME = "stock_name";
    public static final String EXTRA_PRICE = "price";

    private String stockId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        stockId = intent.getStringExtra(EXTRA_STOCK);
        final String name= intent.getStringExtra(EXTRA_NAME);
        final String price = intent.getStringExtra(EXTRA_PRICE);

        setContentView(R.layout.activity_confirm_order);

        TextView view1 = (TextView) findViewById(R.id.confirm_text_1);
        TextView view2 = (TextView) findViewById(R.id.confirm_text_2);
        TextView view3 = (TextView) findViewById(R.id.confirm_text_3);
        TextView view4 = (TextView) findViewById(R.id.confirm_text_4);

        view1.setText(name);
        view2.setText("input number");
        view3.setText(price);
        view4.setText("paket");

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Konfirmasi Order");

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

}
