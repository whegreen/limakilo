package id.limakilo.www.bawang.ui.main.campaignfragment.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 22/8/15.
 */
public class CampaignFragmentPresenterImpl implements CampaignFragmentPresenter, APICallListener {
    public CampaignFragmentView view;
    public CampaignFragmentInteractorImpl interactor;

    public CampaignFragmentPresenterImpl(CampaignFragmentView view){
        this.view = view;
        this.interactor = new CampaignFragmentInteractorImpl(this);
    }


    @Override
    public void presentState(CampaignFragmentView.ViewState state) {
        switch (state){
            case IDLE :
                view.showState(CampaignFragmentView.ViewState.IDLE);
                break;
            case LOADING:
                view.showState(CampaignFragmentView.ViewState.LOADING);
                break;
            case LOAD_DATA:
                view.doSetAuthentification();
                presentState(CampaignFragmentView.ViewState.LOADING);
                interactor.callAPIGetCampaigns(view.doRetrieveAuthentification());
                break;
            case ERROR:
                view.showState(CampaignFragmentView.ViewState.ERROR);
                break;
            case ERROR_API_CALL:
                view.showState(CampaignFragmentView.ViewState.ERROR_API_CALL);
                break;
            case BLANK_STATE:
                view.showState(CampaignFragmentView.ViewState.BLANK_STATE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        if (endPoint == APICallManager.APIRoute.GETSTOCKS) {
            view.doUpdateModel(responseModel);
            presentState(CampaignFragmentView.ViewState.IDLE);
        } else if (endPoint == APICallManager.APIRoute.GETCAMPAIGNS) {
            view.doUpdateModel(responseModel);
            presentState(CampaignFragmentView.ViewState.IDLE);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
//        if (endPoint == APICallManager.APIRoute.GETSTOCKS){
            presentState(CampaignFragmentView.ViewState.ERROR_API_CALL);
            presentState(CampaignFragmentView.ViewState.ERROR);
//        }
//        else{
//
//        }
    }
}

