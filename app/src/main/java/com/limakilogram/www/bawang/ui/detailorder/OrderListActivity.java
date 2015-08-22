package com.limakilogram.www.bawang.ui.detailorder;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.limakilogram.www.bawang.R;

/**
 * Created by walesadanto on 22/8/15.
 */
public class OrderListActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_order);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("Riwayat Order");

        ListView lv = (ListView) findViewById(R.id.list_order);

        OrderModel order_data[] = new OrderModel[]
                {
                        new OrderModel("Tanggal Order: ", "Keterangan Order: "),
                        new OrderModel("Tanggal Order: ", "Keterangan Order: "),
                        new OrderModel("Tanggal Order: ", "Keterangan Order: "),
                };


        OrderListAdapter adapter = new OrderListAdapter(this,
                R.layout.list_item_order, order_data);

        lv.setAdapter(adapter);
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
