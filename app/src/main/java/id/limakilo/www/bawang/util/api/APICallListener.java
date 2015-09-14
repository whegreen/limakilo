package id.limakilo.www.bawang.util.api;

import retrofit.RetrofitError;

/**
 * Created by walesadanto on 15/7/15.
 */
public interface APICallListener {
    public void onAPICallSucceed(String caller, RootResponseModel responseModel);
    public void onAPICallFailed(String caller, RetrofitError retrofitError);
}
