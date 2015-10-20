package id.limakilo.www.bawang.ui.login.mvp;

import android.os.Bundle;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import id.limakilo.www.bawang.ui.login.mvp.LoginView.ViewState;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import retrofit.RetrofitError;

import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.FAILURE;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.SUCCESS;

/**
 * Created by walesadanto on 23/6/15.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginListener, APICallListener{

    private LoginView view;
    private LoginInteractor interactor;

    private ViewState currentState;

    public AuthCallback digitCallback;

    public LoginPresenterImpl (LoginView view){
        this.view = view;
        interactor = new LoginInteractorImpl(this);
    }

    @Override
    public void presentState(ViewState state) {
        ViewState prevState = currentState;
        currentState = state;
        switch (currentState) {
//            case LOGIN_DIGIT:
//                // handled natively in activity and fragment, bypass call from digitcallback
//                break;
            case LOGIN_LATER:
                presentState(ViewState.LOADING);
                view.doLoginLater();
                break;
            case LOGGING_IN:
                view.showState(ViewState.LOADING);
                interactor.callAPILogin(view.doGetAuthentification(), view.doGetAppVersion());
                break;
            case IDLE:
                view.showState(LoginView.ViewState.IDLE);
                break;
            case FAILURE:
                break;
            case CANCEL:
                break;
            case SUCCESS:
                view.showState(ViewState.SUCCESS);
                break;
            case LOADING:
                view.showState(ViewState.LOADING);
                break;
            case ERROR:
                view.showState(LoginView.ViewState.ERROR);
                break;
            default:
                break;
        }

    }

    @Override
    public void callAsync(ListenerCaller caller, ListenerAction action) {
        interactor.callAsync(caller, action);
    }

    @Override
    public void onCallback(ListenerCaller listenerType, ListenerResult listenerResult, Object result) {
        switch (listenerType) {
            case FACEBOOK:
                switch (listenerResult){
                    case SUCCESS:
                        presentState(ViewState.LOADING);
                        getFacebookUserData(((LoginResult) result).getAccessToken());
                        break;
                    case FAILURE:
                        presentState(ViewState.IDLE);
                        break;
                    case CANCEL:
                        presentState(ViewState.IDLE);
                        break;
                }
                break;
            case DIGIT:
                switch (listenerResult){
                    case SUCCESS:
                        presentState(ViewState.LOADING);
                        interactor.callAPIDigitLogin((DigitsSession) result);
                        break;
                    case FAILURE:
                        presentState(ViewState.IDLE);
                        break;
                    case CANCEL:
                        presentState(ViewState.IDLE);
                        break;
                }
                break;
            case LATER:
                    switch (listenerResult){
                        case SUCCESS:
//                            presentState(ViewState.SUCCESS, LOGIN_LATER);
                            break;
                        case FAILURE:
//                            presentState(ViewState.FAILURE, LOGIN_LATER);
                            break;
                        case CANCEL:
//                            presentState(ViewState.CANCEL, LOGIN_LATER);
                            break;
                    }
                break;
            default:
                break;
        }
    }

    public LoginInteractor getInteractor() {
        return interactor;
    }


    public void getFacebookUserData(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(
                accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {

                        JSONObject json = response.getJSONObject();

                        String firstName = "";
                        String id = "";
                        String lastName = "";
                        String email = "";
                        String avatarUrl = "";
                        String coverUrl = "";

                        try {
                            id = json.getString("id");
                            firstName = json.getString("first_name");
                            PreferencesManager.saveAsString(view.doGetActivity(), PreferencesManager.FIRST_NAME, firstName);

                            lastName = json.getString("last_name");
                            PreferencesManager.saveAsString(view.doGetActivity(), PreferencesManager.LAST_NAME, lastName);

                            email = json.getString("email");
                            PreferencesManager.saveAsString(view.doGetActivity(), PreferencesManager.EMAIL, email);

                            avatarUrl = json.getJSONObject("picture").getJSONObject("data").getString("url");
                            PreferencesManager.saveAsString(view.doGetActivity(), PreferencesManager.AVATAR_URL, avatarUrl);

                            coverUrl = json.getJSONObject("cover").getString("source");
                            PreferencesManager.saveAsString(view.doGetActivity(), PreferencesManager.COVER_URL, coverUrl);

                        } catch (JSONException e) {
                            Crashlytics.logException(e);
                        }


                        presentState(ViewState.LOADING);
                        interactor.callAPIFacebookLogin(id, firstName, lastName, email);
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields","id,first_name,last_name,email,picture,cover");
        request.setParameters(parameters);
        request.executeAsync();
    }


    // Digit stuffs
    public AuthCallback getDigitCallback() {
        return new AuthCallback() {
            @Override
            public void success(DigitsSession digitsSession, String s) {
                onCallback(ListenerCaller.DIGIT, SUCCESS, digitsSession);
            }
            @Override
            public void failure(DigitsException e) {
                onCallback(ListenerCaller.DIGIT, FAILURE, e);
            }
        };
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        presentState(ViewState.IDLE);
        if (endPoint == APICallManager.APIRoute.GETUSER){
            GetUserResponseModel getUserResponseModel = (GetUserResponseModel) responseModel;
            try {
                if (getUserResponseModel.getData() != null && !getUserResponseModel.getData().isEmpty()) {
                    view.doSaveUserData(getUserResponseModel.getData().get(0));
                }
            } catch (Exception e) {
                Crashlytics.logException(e);
                presentState(ViewState.ERROR);
            }
        } else if (endPoint == APICallManager.APIRoute.LOGINDIGIT){
            LoginResponseModel loginResponseModel= (LoginResponseModel) responseModel;
            view.doSaveUserAuthentification(loginResponseModel.getData().get(0).getAuth());
            presentState(ViewState.SUCCESS);
        } else if (endPoint == APICallManager.APIRoute.LOGINFACEBOOK){
            LoginResponseModel loginResponseModel= (LoginResponseModel) responseModel;
            view.doSaveUserAuthentification(loginResponseModel.getData().get(0).getAuth());
            presentState(ViewState.SUCCESS);
        } else if (endPoint == APICallManager.APIRoute.LOGIN){
            LoginResponseModel loginResponseModel= (LoginResponseModel) responseModel;
            if (loginResponseModel.getData().get(0).getAuth().isEmpty() || loginResponseModel.getData().get(0).getAuth() == null){
                view.doShowDialogUpdate();
            } else{
                view.doSaveUserAuthentification(loginResponseModel.getData().get(0).getAuth());
            }
            presentState(ViewState.SUCCESS);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
        presentState(ViewState.IDLE);
        presentState(ViewState.ERROR);
    }
}
