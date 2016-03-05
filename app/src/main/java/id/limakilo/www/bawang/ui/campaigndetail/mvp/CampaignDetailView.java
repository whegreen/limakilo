package id.limakilo.www.bawang.ui.campaigndetail.mvp;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface CampaignDetailView {

    String doRetrieveFullname();

    int doRetrieveColor(int color);

    public enum ViewState {
        IDLE, LOADING, LOAD_ORDERS, API_ERROR, SHOW_ORDERS, CONFIRM_ORDER, SHOW_DETAIL_ORDER,
        SHOW_CONFIRM_DIALOG, BLANK_STATE, LOAD_ORDER, LOAD_STOCK, SHOW_SHIPMENT_DIALOG, SHOW_DISCARD_DIALOG,
        LOAD_DATA, SHOW_CAMPAIGN_DETAIL, SHOW_DATA, SHOW_FINISH_DIALOG
    }

    public void showState(ViewState state);

    String doRetrieveAuthentification();

    public CampaignDetailModel doRetrieveModel();

}
