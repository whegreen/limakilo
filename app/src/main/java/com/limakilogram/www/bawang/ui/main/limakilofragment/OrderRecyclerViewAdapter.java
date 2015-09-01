package com.limakilogram.www.bawang.ui.main.limakilofragment;

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
import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.detailorder.DetailOrderActivity;
import com.limakilogram.www.bawang.ui.historyorder.HistoryOrderActivity;
import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;

import java.util.List;

/**
 * Created by walesadanto on 26/7/15.
 */
public class OrderRecyclerViewAdapter extends RecyclerView.Adapter<OrderRecyclerViewAdapter.StockItemChatViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetStockResponseModel.GetStockResponseData> mStocks;

//    private

    public GetStockResponseModel.GetStockResponseData getValueAt(int position){
        return mStocks.get(position);
    }

    public OrderRecyclerViewAdapter(Context context, List<GetStockResponseModel.GetStockResponseData> chats){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mStocks = chats;
    }

    @Override
    public StockItemChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new StockItemChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StockItemChatViewHolder holder, int position) {

        holder.mTextView.setText(mStocks.get(position).getCommodity()
                +" "+ mStocks.get(position).getCategory());
        holder.mTextView2.setText(mStocks.get(position).getPrice());
        holder.mTextView3.setText(mStocks.get(position).getFirstName()+" "+mStocks.get(position).getLastName());
//        holder.mTextView4.setText(mStocks.get(position).getStock());

        holder.mBoundAvatar = mStocks.get(position).getAvaUrl();
        holder.mBoundStockId = mStocks.get(position).getId();
        holder.commodityPrice = mStocks.get(position).getPrice();
        holder.commodityName = mStocks.get(position).getCommodity()+" "+mStocks.get(position).getCategory();;


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailOrderActivity.class);
//                intent.putExtra(HistoryOrderActivity.EXTRA_NAME, holder.commodityName);
//                intent.putExtra(HistoryOrderActivity.EXTRA_AVATAR, holder.mBoundAvatar);
//                intent.putExtra(HistoryOrderActivity.EXTRA_QTY, holder.mTextView2.getText());
//                intent.putExtra(HistoryOrderActivity.EXTRA_STOCK, holder.mBoundStockId);
//                intent.putExtra(HistoryOrderActivity.EXTRA_PRICE, holder.commodityPrice);
                context.startActivity(intent);
            }
        });


        Glide.with(holder.mImageView.getContext())
                .load(R.drawable.onion2)
                .fitCenter()
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }


    public static class StockItemChatViewHolder extends RecyclerView.ViewHolder{

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
        public final TextView mTextView3;
//        public final TextView mTextView4;

        public StockItemChatViewHolder(View itemView) {
            super(itemView);
            // todo : do some mapping data here
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.avatar);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
            mTextView2 = (TextView) itemView.findViewById(android.R.id.text2);
            mTextView3 = (TextView) itemView.findViewById(R.id.text3);
        }
    }
}
