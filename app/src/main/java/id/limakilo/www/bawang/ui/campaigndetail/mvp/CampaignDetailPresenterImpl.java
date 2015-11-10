package id.limakilo.www.bawang.ui.campaigndetail.mvp;

import android.widget.TextView;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.campaign.GetCampaignDetailResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 1/9/15.
 */
public class CampaignDetailPresenterImpl implements CampaignDetailPresenter, APICallListener{

    private CampaignDetailView view;
    private CampaignDetailInteractorImpl interactor;

    public CampaignDetailPresenterImpl(CampaignDetailView view){
        this.view = view;
        this.interactor = new CampaignDetailInteractorImpl(this);
    }

    @Override
    public void presentState(CampaignDetailView.ViewState state) {
        switch (state){
            case LOADING:
                view.showState(CampaignDetailView.ViewState.LOADING);
                break;
            case IDLE:
                view.showState(CampaignDetailView.ViewState.IDLE);
                break;
            case CONFIRM_ORDER:
//                interactor.callAPIConfirmOrder(
//                        view.doRetrieveAuthentification(),
////                        view.doRetrieveModel().getOrderDetailModel().getOrderId().toString(),
////                        view.doRetrieveModel().getOrderDetailModel().getOrderAmount(),
//                        view.doRetrieveFullname()
//                );
                break;
            case LOAD_DATA:
                interactor.callAPIGetCampaignDetail(view.doRetrieveAuthentification(), "1");
                view.showState(CampaignDetailView.ViewState.LOADING);
                break;
            case LOAD_ORDERS:
                interactor.callAPIGetOrders(view.doRetrieveAuthentification());
                view.showState(CampaignDetailView.ViewState.LOADING);
                break;
            case LOAD_STOCK:
//                interactor.callAPIStockDetail(
//                        view.doRetrieveAuthentification(),
//                        view.doRetrieveModel().getOrderDetailModel().getStockId().toString()
//                );
                break;
            case SHOW_ORDERS:
                view.showState(CampaignDetailView.ViewState.SHOW_ORDERS);
                break;
            case SHOW_DETAIL_ORDER:
                view.showState(CampaignDetailView.ViewState.SHOW_DETAIL_ORDER);
                break;
            case SHOW_CONFIRM_DIALOG:
                view.showState(CampaignDetailView.ViewState.SHOW_CONFIRM_DIALOG);
                break;
            case SHOW_FINISH_DIALOG:
                view.showState(CampaignDetailView.ViewState.SHOW_FINISH_DIALOG);
                break;
            case SHOW_DISCARD_DIALOG:
                view.showState(CampaignDetailView.ViewState.SHOW_DISCARD_DIALOG);
                break;
            case SHOW_SHIPMENT_DIALOG:
                view.showState(CampaignDetailView.ViewState.SHOW_SHIPMENT_DIALOG);
                break;
            case API_ERROR:
                view.showState(CampaignDetailView.ViewState.API_ERROR);
                break;
            case BLANK_STATE:
                view.showState(CampaignDetailView.ViewState.BLANK_STATE);
                break;
            case SHOW_CAMPAIGN_DETAIL:
                view.showState(CampaignDetailView.ViewState.SHOW_CAMPAIGN_DETAIL);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        presentState(CampaignDetailView.ViewState.IDLE);if (endPoint == APICallManager.APIRoute.GETCAMPAIGN){
            view.doRetrieveModel().setCampaignDetailModel(((GetCampaignDetailResponseModel)responseModel).getData().get(0));
            presentState(CampaignDetailView.ViewState.SHOW_CAMPAIGN_DETAIL);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
        presentState(CampaignDetailView.ViewState.IDLE);
        if (endPoint == APICallManager.APIRoute.GETCAMPAIGN){
            presentState(CampaignDetailView.ViewState.API_ERROR);
        }
    }

    @Override
    public void doUpdateTextColor(TextView textView, int color){
        textView.setTextColor(view.doRetrieveColor(color));
    }

}
