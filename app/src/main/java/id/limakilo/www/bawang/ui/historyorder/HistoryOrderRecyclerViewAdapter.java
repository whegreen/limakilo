package id.limakilo.www.bawang.ui.historyorder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;

import java.util.List;

/**
 * Created by walesadanto on 26/7/15.
 */
public class HistoryOrderRecyclerViewAdapter extends RecyclerView.Adapter<HistoryOrderRecyclerViewAdapter.OrderItemViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetOrderResponseModel.GetOrderResponseData> historyOrder;

//    private

    public GetOrderResponseModel.GetOrderResponseData getValueAt(int position){
        return historyOrder.get(position);
    }

    public HistoryOrderRecyclerViewAdapter(Context context, List<GetOrderResponseModel.GetOrderResponseData> historyOrder){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.historyOrder = historyOrder;
    }

    @Override
    public OrderItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new OrderItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final OrderItemViewHolder holder, int position) {

        GetOrderResponseModel.GetOrderResponseData item = historyOrder.get(position);
        holder.mTextView.setText("Paket "+item.getStockName()
                +" | "+ item.getOrderQuantity()+" x "+item.getStockQuantity()+"kg");
        holder.mTextView2.setText(item.getOrderStatus().toString());

        holder.mTextView3.setText(historyOrder.get(position).getStockPrice());
        holder.mTextView4.setText(historyOrder.get(position).getSellerName().toString());

        holder.orderId = item.getOrderId().toString();


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                ((HistoryOrderActivity)context).retrieveOrderDetail(holder.orderId);
            }
        });

        Glide.with(holder.mImageView.getContext())
//                .load(historyOrder.get(position).getAvaUrl())
                .load(id.limakilo.www.bawang.R.drawable.onion3)
                .fitCenter()
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return historyOrder.size();
    }


    public static class OrderItemViewHolder extends RecyclerView.ViewHolder{

        // todo : put some field to be viewed here
        public String text;
        public String commodityName;
        public String orderId;
        public String commodityPrice;
        public String mBoundAvatar;
        public int mBoundStockId;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public final TextView mTextView2;
        public final TextView mTextView3;
        public final TextView mTextView4;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            // todo : do some mapping data here
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.order_list_avatar);
            mTextView = (TextView) itemView.findViewById(R.id.order_list_paket);
            mTextView2 = (TextView) itemView.findViewById(R.id.order_list_status);
            mTextView3 = (TextView) itemView.findViewById(R.id.order_list_pembayaran);
            mTextView4 = (TextView) itemView.findViewById(R.id.order_list_seller);
        }
    }
}
