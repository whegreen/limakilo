package id.limakilo.www.bawang.ui.main.stockfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.ui.order.OrderActivity;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;
import id.limakilo.www.bawang.util.common.TextFormatter;

/**
 * Created by walesadanto on 26/7/15.
 */
public class StockRecyclerViewAdapter extends RecyclerView.Adapter<StockRecyclerViewAdapter.StockViewHolder> {

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
    public StockViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stock_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new StockViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StockViewHolder holder, int position) {
        GetStockResponseModel.GetStockResponseData stock = mStocks.get(position);
        holder.bindData(stock);
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

    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }

    public static class StockViewHolder extends RecyclerView.ViewHolder{

        // element to be passed in intent later
        private String mBoundStockId;
        private String mBoundStockName;
        private String mBoundStockQuantity;
        private String mBoundStockPrice;
        private String mBoundSellerId;
        private String mBoundSellerName;
        private String mBoundSellerAvaUrl;
        private String mBoundSellerAddress;
        private String mBoundSellerReputation;
        private String mBoundSellerCity;

        // ui element
        @Bind(R.id.avatar) ImageView mImageView;
        @Bind(R.id.paket) TextView mTextView;
        @Bind(R.id.seller) TextView mTextView2;
        @Bind(R.id.harga) TextView mTextView3;
        @Bind(R.id.city) TextView mTextView5;
//        @Bind(R.id.stock_quota) TextView mTextView4;

        @OnClick(R.id.stock_item_view_holder)
        public void OnStockItemClick(View view){
            final Context context = view.getContext();
            ((MainActivity)context).showLoadingBar();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    handler.removeCallbacks(this);
                    Intent intent = new Intent(context, OrderActivity.class);
                    intent.putExtra(OrderActivity.STOCKID, mBoundStockId);
                    intent.putExtra(OrderActivity.STOCKNAME, mBoundStockName);
                    intent.putExtra(OrderActivity.STOCKQUANTITY, mBoundStockQuantity);
                    intent.putExtra(OrderActivity.STOCKPRICE, mBoundStockPrice);
                    intent.putExtra(OrderActivity.SELLERID, mBoundSellerId);
                    intent.putExtra(OrderActivity.SELLERNAME, mBoundSellerName);
                    intent.putExtra(OrderActivity.SELLERAVAURL, mBoundSellerAvaUrl);
                    intent.putExtra(OrderActivity.SELLERADDRESS, mBoundSellerAddress);
                    intent.putExtra(OrderActivity.SELLERREPUTATION, mBoundSellerReputation);
                    intent.putExtra(OrderActivity.SELLERCITY, mBoundSellerCity);
                    context.startActivity(intent);
                    ((MainActivity)context).hideLoadingBar();
                }
            }, 300L);
        }

        public void bindData(GetStockResponseModel.GetStockResponseData stock){
            mTextView.setText(stock.getStockName());
            mTextView2.setText(stock.getSellerName());

            Double stockPrice = Double.parseDouble(stock.getStockPrice());

            mTextView3.setText("Rp. "+TextFormatter.decimalFormat(stockPrice)+",-");
            mTextView5.setText(stock.getSellerCity());
//            mTextView4.setText(stock.getStockOrdered()+"/"+stock.getStockQuota()+"kg");

            mBoundStockId = stock.getStockId().toString();
            mBoundStockName = stock.getStockName();
            mBoundStockQuantity = stock.getStockQuantity().toString();
            mBoundStockPrice = stock.getStockPrice();
            mBoundSellerId = stock.getSellerId().toString();
            mBoundSellerName = stock.getSellerName();
            mBoundSellerAvaUrl = stock.getSellerAvatarUrl();
            mBoundSellerAddress = stock.getSellerAddress();
            mBoundSellerReputation = stock.getSellerReputation();
            mBoundSellerCity = stock.getSellerCity();
        }

        public StockViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
