package id.limakilo.www.bawang.ui.historyorder;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.confirmorder.ConfirmOrderActivity;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;

import java.util.List;

/**
 * Created by walesadanto on 26/7/15.
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.OrderItemViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetOrderResponseModel.GetOrderResponseData> mOrders;

//    private

    public GetOrderResponseModel.GetOrderResponseData getValueAt(int position){
        return mOrders.get(position);
    }

    public OrderRecyclerViewAdapter(Context context, List<GetOrderResponseModel.GetOrderResponseData> chats){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mOrders = chats;
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

//        holder.mTextView.setText(mOrders.get(position).getCommodity()
//                +" "+ mOrders.get(position).getCategory());
//        holder.mTextView2.setText(mOrders.get(position).getPrice());
//        holder.mTextView3.setText(mOrders.get(position).getFirstName()+" "+mOrders.get(position).getLastName());
////        holder.mTextView4.setText(mOrders.get(position).getStock());
//
//        holder.mBoundAvatar = mOrders.get(position).getAvaUrl();
//        holder.mBoundStockId = mOrders.get(position).getId();
//        holder.commodityPrice = mOrders.get(position).getPrice();
//        holder.commodityName = mOrders.get(position).getCommodity()+" "+mOrders.get(position).getCategory();;


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ConfirmOrderActivity.class);
//                intent.putExtra(HistoryOrderActivity.EXTRA_NAME, holder.commodityName);
//                intent.putExtra(HistoryOrderActivity.EXTRA_AVATAR, holder.mBoundAvatar);
//                intent.putExtra(HistoryOrderActivity.EXTRA_QTY, holder.mTextView2.getText());
//                intent.putExtra(HistoryOrderActivity.EXTRA_STOCK, holder.mBoundStockId);
//                intent.putExtra(HistoryOrderActivity.EXTRA_PRICE, holder.commodityPrice);
                context.startActivity(intent);


            }
        });

        Glide.with(holder.mImageView.getContext())
//                .load(mOrders.get(position).getAvaUrl())
                .load(R.drawable.onion3)
                .fitCenter()
                .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mOrders.size();
    }


    public static class OrderItemViewHolder extends RecyclerView.ViewHolder{

        // todo : put some field to be viewed here
        public String text;
        public String commodityName;
        public String commodityId;
        public String commodityPrice;
        public String mBoundAvatar;
        public int mBoundStockId;

        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public final TextView mTextView2;
//        public final TextView mTextView3;
//        public final TextView mTextView4;

        public OrderItemViewHolder(View itemView) {
            super(itemView);
            // todo : do some mapping data here
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.avatar);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
            mTextView2 = (TextView) itemView.findViewById(android.R.id.text2);
//            mTextView3 = (TextView) itemView.findViewById(R.id.text3);
        }
    }
}
