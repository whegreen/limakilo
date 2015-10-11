package id.limakilo.www.bawang.ui.historyorder.mvp;

import id.limakilo.www.bawang.ui.login.mvp.LoginView;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 1/9/15.
 */
public class HistoryOrderPresenterImpl implements HistoryOrderPresenter, APICallListener{
    @Override
    public void presentState(LoginView.ViewState state) {

    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {

    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {

    }
}
