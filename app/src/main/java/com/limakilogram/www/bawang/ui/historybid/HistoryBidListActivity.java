package com.limakilogram.www.bawang.ui.historybid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.bid.GetMyBidResponseModel;
import com.limakilogram.www.bawang.util.api.order.GetMyOrderResponseModel;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 22/8/15.
 */
public class HistoryBidListActivity extends AppCompatActivity {

    public static final String EXTRA_HISTORY = "history";

    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String history = intent.getStringExtra(EXTRA_HISTORY);

        setContentView(R.layout.activity_history_bid);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        final ListView lv = (ListView) findViewById(R.id.list_order);

        context = getApplicationContext();

        if (history.equals("order")){
            collapsingToolbar.setTitle("Riwayat Paket");
            APICallManager.getInstance().getMyOrders(new Callback<GetMyOrderResponseModel>() {
                @Override
                public void success(GetMyOrderResponseModel getMyOrderResponseModel, Response response) {
                    Toast.makeText(getBaseContext(), "success", Toast.LENGTH_SHORT).show();

                    HistoryBidModel order_data[];
                    order_data = convertToOrderModel(getMyOrderResponseModel.getData(), 0);
                    HistoryBidListAdapter adapter = new HistoryBidListAdapter(context,
                            R.layout.list_item_order, order_data);//
                    lv.setAdapter(adapter);

                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getBaseContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            collapsingToolbar.setTitle("Riwayat Grosir");
            APICallManager.getInstance().getMyBids(new Callback<GetMyBidResponseModel>() {
                @Override
                public void success(GetMyBidResponseModel getMyBidResponseModel, Response response) {
                    Toast.makeText(getBaseContext(), "success", Toast.LENGTH_SHORT).show();

                    HistoryBidModel order_data[];
                    order_data = convertToOrderModel(getMyBidResponseModel.getData());
                    HistoryBidListAdapter adapter = new HistoryBidListAdapter(context,
                            R.layout.list_item_order, order_data);//
                    lv.setAdapter(adapter);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(getBaseContext(), "failed : "+error.getMessage().toString(), Toast.LENGTH_SHORT).show();

                }
            });
        }


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

    public HistoryBidModel[] convertToOrderModel(List<GetMyBidResponseModel.GetMyBidResponseData> orderData){
        HistoryBidModel order_data[] = new HistoryBidModel[orderData.size()];
        int i = 0;
        for (GetMyBidResponseModel.GetMyBidResponseData element : orderData) {
            order_data[i] = new HistoryBidModel(orderData.get(0).getOrderId()+" x "+orderData.get(0).getStatus(),
                    orderData.get(0).getDate());
        }

        return order_data;
    }

    public HistoryBidModel[] convertToOrderModel(List<GetMyOrderResponseModel.GetMyOrderResponseData> orderData, int dummy){
        HistoryBidModel order_data[] = new HistoryBidModel[orderData.size()];
        int i = 0;
        for (GetMyOrderResponseModel.GetMyOrderResponseData element : orderData) {
            order_data[i] = new HistoryBidModel(orderData.get(0).getQuantity()+" x "+orderData.get(0).getPrice(),
                    orderData.get(0).getDate());
        }
        return order_data;
    }

}
