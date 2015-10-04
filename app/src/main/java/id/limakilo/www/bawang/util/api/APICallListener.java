package id.limakilo.www.bawang.util.api;

import retrofit.RetrofitError;

/**
 * Created by walesadanto on 15/7/15.
 */
public interface APICallListener {
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel);
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError);
}
