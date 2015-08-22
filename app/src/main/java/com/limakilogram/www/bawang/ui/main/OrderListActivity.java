package com.limakilogram.www.bawang.ui.main;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.limakilogram.www.bawang.R;

/**
 * Created by martinluter on 8/22/15.
 */
public class OrderListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);

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
}
