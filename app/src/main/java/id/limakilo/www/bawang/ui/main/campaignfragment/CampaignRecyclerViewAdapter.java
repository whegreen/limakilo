package id.limakilo.www.bawang.ui.main.campaignfragment;

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

import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.ui.register_phone.RegisterPhoneActivity;
import id.limakilo.www.bawang.util.api.campaign.GetCampaignResponseModel;

/**
 * Created by walesadanto on 26/7/15.
 */
public class CampaignRecyclerViewAdapter extends RecyclerView.Adapter<CampaignRecyclerViewAdapter.CampaignViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetCampaignResponseModel.GetCampaignResponseData> mStocks;

    public GetCampaignResponseModel.GetCampaignResponseData getValueAt(int position){
        return mStocks.get(position);
    }

    public CampaignRecyclerViewAdapter(Context context, List<GetCampaignResponseModel.GetCampaignResponseData> stocks){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        mStocks = stocks;
    }

    @Override
    public CampaignViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.stock_list_item, parent, false);
                .inflate(R.layout.card_campaign_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new CampaignViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CampaignViewHolder holder, int position) {
        GetCampaignResponseModel.GetCampaignResponseData stock = mStocks.get(position);
        holder.bindData(stock);
        int[] randomAva = {
                R.drawable.bg_microfund_header
        };

        Random rand = new Random();
        int  n = 1;

//        holder.mBoundSellerAvaUrl = ((Integer)randomAva[n]).toString();
//        Glide.with(holder.mImageView.getContext())
//        .load(randomAva[n])
//        .fitCenter()
//        .into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mStocks.size();
    }

    public static class CampaignViewHolder extends RecyclerView.ViewHolder{

//        // element to be passed in intent later
        private String mBoundCampaignId;

//        // ui element
        @Bind(R.id.card_campaign_title)
        TextView mTitle;
        @Bind(R.id.card_share_button)
        ImageView mShareButton;
        @Bind(R.id.card_cover_image)
        ImageView mCoverImage;
        @Bind(R.id.card_campaign_price)
        TextView mPrice;
        @Bind(R.id.card_campaign_ordered)
        TextView mOrdered;
        @Bind(R.id.card_campaign_end_time)
        TextView mEndTime;
        @Bind(R.id.card_campaign_progress)
        View mProgress;
        @Bind(R.id.card_campaign_progress_full)
        View mProgressFull;
        @Bind(R.id.card_campaign_commodity)
        TextView mCommodity;
        @Bind(R.id.card_campaign_farmer)
        TextView mFarmer;
        @Bind(R.id.card_campaign_location)
        TextView mLocation;

        @OnClick(R.id.card_campaign_item_view_holder)
        public void OnCampaignItemClick(View view){
            final Context context = view.getContext();
            ((MainActivity)context).showLoadingBar();

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    handler.removeCallbacks(this);
                    Intent intent = new Intent(context, RegisterPhoneActivity.class);
//                    intent.putExtra(CampaignDetailActivity.CAMPAIGN_ID, mBoundCampaignId);
                    context.startActivity(intent);
                    ((MainActivity)context).hideLoadingBar();
                }
            }, 300L);
        }

        public void bindData(GetCampaignResponseModel.GetCampaignResponseData stock){
            mBoundCampaignId = stock.getCamId().toString();
        }

        public CampaignViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
