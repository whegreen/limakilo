package id.limakilo.www.bawang.ui.login.mvp;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;

import id.limakilo.www.bawang.ui.login.mvp.LoginView.ViewState;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
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
                view.showState(ViewState.IDLE);
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
            case DIGIT:
                    switch (listenerResult){
                        case SUCCESS:
                            presentState(ViewState.LOADING);
                            interactor.callAPIDigitLogin((DigitsSession) result);
                            break;
                        case FAILURE:
//                            presentState(ViewState.FAILURE, DIGIT);
                            break;
                        case CANCEL:
//                            presentState(LoginView.ViewState.CANCEL, DIGIT);
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
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
//        onAPICallFailed(caller, retrofitError);
        presentState(ViewState.ERROR);
    }
}
