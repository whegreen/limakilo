package id.limakilo.www.bawang.util.api;

import retrofit.RetrofitError;

/**
 * Created by walesadanto on 15/7/15.
 */
public interface APICallListener {

    void onAPICallSucceed(APICallManager.APIRoute route, RootResponseModel responseModel);
    void onAPICallFailed(APICallManager.APIRoute route, RetrofitError retrofitError);
}