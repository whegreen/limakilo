package com.limakilogram.www.bawang.ui.historybid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.limakilogram.www.bawang.R;

/**
 * Created by martinluter on 8/22/15.
 */
public class HistoryBidListAdapter extends ArrayAdapter<HistoryBidModel> {
    private static String TAG = "HistoryBidListAdapter";
    public HistoryBidModel order_data[] = null;

    int layoutResourceId;
    private Context context;

    public HistoryBidListAdapter(Context context, int layoutResourceId, HistoryBidModel[] order_data) {
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
            LayoutInflater inflaters = LayoutInflater.from(context);
            row = inflaters.inflate(layoutResourceId, parent, false);

//            LayoutInflater inflater = (LayoutInflater) getContext()
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            holder = new OrderModelHolder();

            holder.txtTitle1 = (TextView) row.findViewById(R.id.txt_title1);
            holder.txtTitle2 = (TextView) row.findViewById(R.id.txt_title2);
            Button btn_choice = (Button) row.findViewById(R.id.btn_choice);

            btn_choice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            row.setTag(holder);
        } else {
            holder = (OrderModelHolder) row.getTag();
        }

        try{
            HistoryBidModel orderData = order_data[position];
            holder.txtTitle1.setText(orderData.title1);
            holder.txtTitle2.setText(orderData.title2);
        }
        catch (Exception e){
//            Crashlytics.logException(e);
        }

        return row;
    }

    static class OrderModelHolder {
        TextView txtTitle1, txtTitle2;
    }
}