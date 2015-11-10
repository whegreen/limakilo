package id.limakilo.www.bawang.ui.main.campaignfragment.mvp;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface CampaignFragmentView {

    public enum ViewState {
        IDLE, LOAD_DATA, SUCCESS, FAILURE, CANCEL, LOADING, ERROR_API_CALL, BLANK_STATE, ERROR
    }

    public void doSetAuthentification();

    void doUpdateModel(RootResponseModel responseModel);

    public void doRetrieveModel();

    String doRetrieveAuthentification();

    public void showState(ViewState state);

}
