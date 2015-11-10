package id.limakilo.www.bawang.ui.campaigndetail.mvp;

import android.widget.TextView;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface CampaignDetailPresenter {
    public void presentState(CampaignDetailView.ViewState state);

    void doUpdateTextColor(TextView textView, int color);
}
