package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 22/8/15.
 */
public class StockFragmentPresenterImpl implements StockFragmentPresenter, APICallListener {
    public StockFragmentView view;
    public StockFragmentInteractorImpl interactor;

    public StockFragmentPresenterImpl(StockFragmentView view){
        this.view = view;
        this.interactor = new StockFragmentInteractorImpl(this);
    }


    @Override
    public void presentState(StockFragmentView.ViewState state) {
        switch (state){
            case IDLE :
                view.showState(StockFragmentView.ViewState.IDLE);
                break;
            case LOADING:
                view.showState(StockFragmentView.ViewState.LOADING);
                break;
            case LOAD_DATA:
                view.doSetAuthentification();
                presentState(StockFragmentView.ViewState.LOADING);
                interactor.callAPIGetStocks();
                break;
            case ERROR:
                view.showState(StockFragmentView.ViewState.ERROR);
                break;
            case ERROR_API_CALL:
                view.showState(StockFragmentView.ViewState.ERROR_API_CALL);
                break;
            case BLANK_STATE:
                view.showState(StockFragmentView.ViewState.BLANK_STATE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        if (endPoint == APICallManager.APIRoute.GETSTOCKS) {
            view.doUpdateModel(responseModel);
            presentState(StockFragmentView.ViewState.IDLE);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
        if (endPoint == APICallManager.APIRoute.GETSTOCKS){
            presentState(StockFragmentView.ViewState.ERROR_API_CALL);
            presentState(StockFragmentView.ViewState.ERROR);
        }
        else{

        }
    }
}

