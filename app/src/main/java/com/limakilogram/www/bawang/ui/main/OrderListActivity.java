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
        setContentView(R.layout.activity_5kg);

        ListView lv = (ListView) findViewById(R.id.list_order);


    }
}
