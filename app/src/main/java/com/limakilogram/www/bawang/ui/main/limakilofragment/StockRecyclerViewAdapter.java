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
import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;

import java.util.List;
import java.util.Random;

/**
 * Created by walesadanto on 26/7/15.
 */
public class StockRecyclerViewAdapter extends RecyclerView.Adapter<StockRecyclerViewAdapter.StockItemChatViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetStockResponseModel.GetStockResponseData> mStocks;

    public GetStockResponseModel.GetStockResponseData getValueAt(int position){
        return mStocks.get(position);
    }

    public StockRecyclerViewAdapter(Context context, List<GetStockResponseModel.GetStockResponseData> chats){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mStocks = chats;
    }

    @Override
    public StockItemChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new StockItemChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StockItemChatViewHolder holder, int position) {

        holder.mTextView.setText(mStocks.get(position).getStockCommodity()
                +" "+ mStocks.get(position).getStockCategory());
        holder.mTextView2.setText(mStocks.get(position).getSellerName());
        holder.mTextView3.setText(mStocks.get(position).getStockPrice());
        holder.mTextView4.setText(mStocks.get(position).getSellerReputation());
        holder.mTextView5.setText(mStocks.get(position).getSellerCity());

        holder.mBoundStockId = mStocks.get(position).getStockId().toString();
        holder.mBoundStockName = mStocks.get(position).getStockName();
        holder.mBoundStockQuantity = mStocks.get(position).getStockQuantity().toString();
        holder.mBoundStockPrice = mStocks.get(position).getStockPrice();
        holder.mBoundSellerId = mStocks.get(position).getSellerId().toString();
        holder.mBoundSellerName = mStocks.get(position).getSellerName();
        holder.mBoundSellerAvaUrl = mStocks.get(position).getSellerAvatarUrl();
        holder.mBoundSellerAddress = mStocks.get(position).getSellerAddress();
        holder.mBoundSellerReputation = mStocks.get(position).getSellerReputation();
        holder.mBoundSellerCity = mStocks.get(position).getSellerCity();


        int[] randomAva = {R.drawable.onion2,
                R.drawable.onion3,
                R.drawable.onion4,
                R.drawable.onion5,
                R.drawable.onion1};

        Random rand = new Random();
        int  n = rand.nextInt(5);

        holder.mBoundSellerAvaUrl = ((Integer)randomAva[n]).toString();

        Glide.with(holder.mImageView.getContext())
        .load(randomAva[n])
        .fitCenter()
        .into(holder.mImageView);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, DetailOrderActivity.class);

                intent.putExtra(DetailOrderActivity.STOCKID, holder.mBoundStockId);
                intent.putExtra(DetailOrderActivity.STOCKNAME, holder.mBoundStockName);
                intent.putExtra(DetailOrderActivity.STOCKQUANTITY, holder.mBoundStockQuantity);
                intent.putExtra(DetailOrderActivity.STOCKPRICE, holder.mBoundStockPrice);
                intent.putExtra(DetailOrderActivity.SELLERID, holder.mBoundSellerId);
                intent.putExtra(DetailOrderActivity.SELLERNAME, holder.mBoundSellerName);
                intent.putExtra(DetailOrderActivity.SELLERAVAURL, holder.mBoundSellerAvaUrl);
                intent.putExtra(DetailOrderActivity.SELLERADDRESS, holder.mBoundSellerAddress);
                intent.putExtra(DetailOrderActivity.SELLERREPUTATION, holder.mBoundSellerReputation);
                intent.putExtra(DetailOrderActivity.SELLERCITY, holder.mBoundSellerCity);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }


    public static class StockItemChatViewHolder extends RecyclerView.ViewHolder{

        // element to be passed in intent later
        public String mBoundStockId;
        public String mBoundStockName;
        public String mBoundStockQuantity;
        public String mBoundStockPrice;
        public String mBoundSellerId;
        public String mBoundSellerName;
        public String mBoundSellerAvaUrl;
        public String mBoundSellerAddress;
        public String mBoundSellerReputation;
        public String mBoundSellerCity;

        // ui element
        public final View mView;
        public final ImageView mImageView;
        public final TextView mTextView;
        public final TextView mTextView2;
        public final TextView mTextView3;
        public final TextView mTextView4;
        public final TextView mTextView5;

        public StockItemChatViewHolder(View itemView) {
            super(itemView);
            // todo : do some mapping data here
            mView = itemView;
            mImageView = (ImageView) itemView.findViewById(R.id.avatar);
            mTextView = (TextView) itemView.findViewById(android.R.id.text1);
            mTextView2 = (TextView) itemView.findViewById(android.R.id.text2);
            mTextView3 = (TextView) itemView.findViewById(R.id.harga);
            mTextView4 = (TextView) itemView.findViewById(R.id.reputation);
            mTextView5 = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
