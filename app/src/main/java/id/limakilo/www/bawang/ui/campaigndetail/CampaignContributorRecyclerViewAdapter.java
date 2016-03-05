package id.limakilo.www.bawang.ui.campaigndetail;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.campaigndetail.mvp.CampaignDetailModel;
import id.limakilo.www.bawang.ui.campaigndetail.mvp.CampaignDetailPresenter;
import id.limakilo.www.bawang.util.api.campaign.GetCampaignDetailResponseModel;

/**
 * Created by walesadanto on 26/7/15.
 */
public class CampaignContributorRecyclerViewAdapter extends RecyclerView.Adapter<CampaignContributorRecyclerViewAdapter.ContributorItemViewHolder> {

    private final TypedValue mTypedValue = new TypedValue();
    private int mBackground;
    private List<GetCampaignDetailResponseModel.GetCampaignDetailData> campaignContributorList;
    private CampaignDetailPresenter presenter;
    private CampaignDetailModel model;

    public GetCampaignDetailResponseModel.GetCampaignDetailData getValueAt(int position){
        return campaignContributorList.get(position);
    }

    public CampaignContributorRecyclerViewAdapter(Context context, List<GetCampaignDetailResponseModel.GetCampaignDetailData> campaignContributorList,
                                                  CampaignDetailPresenter presenter, CampaignDetailModel model){
        context.getTheme().resolveAttribute(android.support.design.R.attr.selectableItemBackground, mTypedValue, true);
        mBackground = mTypedValue.resourceId;
        this.campaignContributorList = campaignContributorList;
        this.presenter = presenter;
        this.model = model;
    }

    @Override
    public ContributorItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.campaign_contributor_list_item, parent, false);
        view.setBackgroundResource(mBackground);
        return new ContributorItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContributorItemViewHolder holder, int position) {
        GetCampaignDetailResponseModel.GetCampaignDetailData item = campaignContributorList.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return campaignContributorList.size();
    }

    public class ContributorItemViewHolder extends RecyclerView.ViewHolder{
        public String mContributorId;

        @Bind(R.id.contributor_list_avatar) ImageView mAvatar;
        @Bind(R.id.contributor_firstname) TextView mFirstname;
        @Bind(R.id.contributor_lastname)TextView mLastname;


        public ContributorItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(GetCampaignDetailResponseModel.GetCampaignDetailData item){

            mFirstname.setText(item.getCamTitle());
            mLastname.setText(item.getCamCreateDate());

            mContributorId = item.getCamDescription().toString();

            Glide.with(mAvatar.getContext())
                    .load(R.drawable.onion3)
                    .fitCenter()
                    .into(mAvatar);
        }

    }
}
