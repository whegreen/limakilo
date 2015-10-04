package id.limakilo.www.bawang.ui.login.mvp;

import com.digits.sdk.android.DigitsSession;

import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerAction;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerCaller;

/**
 * Created by walesadanto on 23/6/15.
 */
public class LoginInteractorImpl implements LoginInteractor  {

    public LoginListener listener;

    public LoginInteractorImpl(LoginListener loginListener){
        this.listener = loginListener;
    }

    @Override
    public void callAsync(ListenerCaller caller, ListenerAction action) {
        switch (caller) {
//            case FACEBOOK:
//                callAsyncFacebook(action);
//                break;
            case DIGIT:
                switch (action){
                    case DIGIT_LOGIN:
                        break;
                    default:
                        break;
                }

                break;
            case LATER :

                break;
            default:
                break;
        }
    }

//    public void callAsyncFacebook(ListenerAction action){
//        switch (action) {
//            case FACEBOOK_AUTHORIZATION:
//                callFacebookAuthorization();
//                break;
//            default:
//                break;
//        }
//    }

    //facebook stuffs
//    public void callFacebookAuthorization(){
//        Bundle params = new Bundle();
//        params.putString("fields", "first_name,last_name");
//        /* make the callAPIDigitLogin call */
//        new GraphRequest(
//                AccessToken.getCurrentAccessToken(),
//                "/me",
//                params,
//                HttpMethod.GET,
//                new GraphRequest.Callback() {
//                    public void onCompleted(GraphResponse response) {
////                        presenter.showLoginState(LoginView.ViewState.LOADING, LoginView.StateAction.FACEBOOK);
//                        listener.onCallback(FACEBOOK, SUCCESS, response);
//                    }
//                }
//        ).executeAsync();
//    }

    public void getActivationCode(){

    }

    @Override
    public void callAPIDigitLogin(DigitsSession digitsSession){
        final APICallManager.APIRoute route = APICallManager.APIRoute.LOGINDIGIT;
        APICallManager.getInstance().loginDigit(((Long) digitsSession.getId()).toString(),
                digitsSession.getPhoneNumber(), new Callback<LoginResponseModel>() {
                    @Override
                    public void success(LoginResponseModel loginResponseModel, Response response) {
                        listener.onAPICallSucceed(route, loginResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        listener.onAPICallFailed(route, error);
                    }
                });
    }

    @Override
    public void callAPIGetUserData(){
        APICallManager.getInstance().getUsers(new Callback<GetUserResponseModel>() {
            APICallManager.APIRoute route = APICallManager.getInstance().apiRoute;
            @Override
            public void success(GetUserResponseModel getUserResponseModel, Response response) {
                listener.onAPICallSucceed(route, getUserResponseModel);
            }
            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }
}
