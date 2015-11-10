package id.limakilo.www.bawang.ui.main.campaignfragment.mvp;

/**
 * Created by walesadanto on 9/24/15.
 */
public interface CampaignFragmentInteractor {
    public void callAsync(CampaignFragmentListener.Listener caller, CampaignFragmentListener.ListenerAction action);

    void callAPIGetStocks();

    void callAPIGetCampaigns(String authentification);
}
