package com.limakilogram.www.bawang.ui.main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.limakilogram.www.bawang.R;

/**
 * Created by martinluter on 8/22/15.
 */
public class OrderListAdapter extends ArrayAdapter<OrderModel> {
    private static String TAG = "OrderListAdapter";
    public OrderModel order_data[] = null;
    int layoutResourceId;
    private Context context;

    public OrderListAdapter(Context context, int layoutResourceId, OrderModel[] order_data) {
        super(context, layoutResourceId, order_data);
        this.order_data = order_data;
        this.context = context;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        OrderModelHolder holder = null;

        if (row == null) {
            LayoutInflater inflaters = ((Activity) context).getLayoutInflater();
            row = inflaters.inflate(layoutResourceId, parent, false);

            LayoutInflater inflater = (LayoutInflater) getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new OrderModelHolder();

            holder.txtTitle1 = (TextView) row.findViewById(R.id.txt_title1);
            holder.txtTitle2 = (TextView) row.findViewById(R.id.txt_title2);
            Button btn_choice = (Button) row.findViewById(R.id.btn_choice);

            btn_choice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getContext(), DetailOrderActivity.class);
                    getContext().startActivity(i);
                }
            });

            row.setTag(holder);
        } else {
            holder = (OrderModelHolder) row.getTag();
        }

        OrderModel orderData = order_data[position];
        holder.txtTitle1.setText(orderData.title1);
        holder.txtTitle2.setText(orderData.title2);

        return row;
    }

    static class OrderModelHolder {
        TextView txtTitle1, txtTitle2;
    }
}