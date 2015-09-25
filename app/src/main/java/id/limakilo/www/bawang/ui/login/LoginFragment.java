package id.limakilo.www.bawang.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsSession;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.login.mvp.LoginInteractorImpl;
import id.limakilo.www.bawang.ui.login.mvp.LoginListener;
import id.limakilo.www.bawang.ui.login.mvp.LoginPresenterImpl;
import id.limakilo.www.bawang.ui.login.mvp.LoginView;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;

import id.limakilo.www.bawang.util.social.SupportkitKit;
import io.supportkit.ui.ConversationActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements LoginView, APICallListener, LoginListener {

    private static final String TAG = "Login Fragment";

    private View view;
    private LoginPresenterImpl presenter;
    private LoginInteractorImpl interactor;

    private GetUserResponseModel.GetUserResponseData userModel;

    public State state = State.IDLE;
    public StateInfo stateInfo = StateInfo.AUTO_LOGIN;

    MaterialDialog popupDialog;

    //binding view
    @Bind(R.id.loading_bar) View loadingBar;
    @Bind(R.id.login_button) LoginButton facebookLoginButton;
    @Bind(R.id.btn_login_later) TextView loginLater;
    @Bind(R.id.btn_digit_login) DigitsAuthButton digitsButton;

    //facebook stuffs
    private FacebookCallback facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // App code
            final String userId = loginResult.getAccessToken().getUserId();
            onCallback(ListenerCaller.FACEBOOK, SUCCESS, loginResult);
            interactor.callAsync(LoginListener.ListenerCaller.FACEBOOK, LoginListener.ListenerAction.FACEBOOK_AUTHORIZATION);
        }

        @Override
        public void onCancel() {
            // App code
            onCallback(ListenerCaller.FACEBOOK, SUCCESS, null);
        }

        @Override
        public void onError(FacebookException exception) {
            // App code
            onCallback(ListenerCaller.FACEBOOK, ListenerResult.FAILURE, exception);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        presenter = new LoginPresenterImpl(this);
        interactor = new LoginInteractorImpl(this);

        if (AccessToken.getCurrentAccessToken() != null){
            return view;
        }

        initPopupDialog();

        facebookLoginButton.setReadPermissions("user_friends");
        facebookLoginButton.setReadPermissions("email");
        facebookLoginButton.setFragment(this);
        facebookLoginButton.registerCallback(((LoginActivity) getActivity()).getCallbackManager(), facebookCallback);

        getLoginActivity().setTwitterAuthCallback(interactor.getDigitCallback());
        digitsButton.setCallback(((LoginActivity) getActivity()).getTwitterAuthCallback());

        stateLoggingIn();

        return view;
    }

    public LoginActivity getLoginActivity(){
        return (LoginActivity) getActivity();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_login_later)
    public void loginLater(){
        PreferencesManager.saveAuthToken(getActivity(), APICallManager.DEMO_AUTH);
        openMainActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getLoginActivity().getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    public void openMainActivity() {
        presenter.showState(State.LOADING);
        final Activity mainActivity = getActivity();
        final Intent mainIntent = new Intent(mainActivity, MainActivity.class);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                presenter.showState(State.IDLE);
                mainActivity.finish();
            }
        }, 300L);
    }

    @Override
    public void showState(State state, StateInfo info) {

    }

    @Override
    public void showIdle() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void stateLoggingIn() {
        loadingBar.setVisibility(View.VISIBLE);
        String auth = PreferencesManager.getAuthToken(getContext());
        if (auth != null && auth.equalsIgnoreCase("")){
            showSuccess();
        }
        else {
            showIdle();
        }
    }

    @Override
    public void showSuccess() {
        showIdle();
        openMainActivity();
    }

    @Override
    public void showFailure(Exception e) {
        showIdle();
        Crashlytics.log(Log.ERROR, TAG, "login failure : ");
        Snackbar.make(view, "maaf kami mengalami masalah...", Snackbar.LENGTH_SHORT).setAction("Bantuan", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConversationActivity.show(getActivity());
            }
        }).show();
    }

    @Override
    public void showCancel() {
        showIdle();
    }

    @Override
    public void stateError(String message) {
        statePopupDialogContentShown(message);
    }

    @Override
    public void stateIdle() {

    }

    @Override
    public void stateLoading() {

    }


    public void saveUserAuthentification(String auth){
        PreferencesManager.saveAsString(getContext(), PreferencesManager.AUTH_TOKEN, auth);
    }

    public void saveUserData(){

        PreferencesManager.saveAsString(getContext(), PreferencesManager.USER_ID, userModel.getUserId().toString());
        PreferencesManager.saveAsString(getContext(), PreferencesManager.HANDPHONE, userModel.getUserPhone());
        PreferencesManager.saveAsString(getContext(), PreferencesManager.LAST_LOGIN_TS, String.valueOf(System.currentTimeMillis()));

        if (userModel.getUserFirstName() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.NAME, userModel.getUserFirstName()+" "+userModel.getUserLastName());

        if (userModel.getUserAddress() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.ADDRESS, userModel.getUserAddress());

        if (userModel.getUserAvatarUrl() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.AVATAR_URL, userModel.getUserAvatarUrl());

        if (userModel.getUserCoverUrl() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.COVER_URL, userModel.getUserCoverUrl());

        APICallManager.getInstance().putUsers(userModel.getUserAddress(), userModel.getUserPhone(),
                userModel.getUserEmail(), null,
                new Callback<PutUserResponseModel>() {
            @Override
            public void success(PutUserResponseModel putUserResponseModel, Response response) {

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });

        //supportkit user
        SupportkitKit supportkitKit = new SupportkitKit();
        supportkitKit.setupUser(getActivity());
    }

    public void getUserData(){
        APICallManager.getInstance().getUsers(new Callback<GetUserResponseModel>() {
            String caller = "getUserData()";

            @Override
            public void success(GetUserResponseModel getUserResponseModel, Response response) {
                try {
                    if (getUserResponseModel.getData() != null && !getUserResponseModel.getData().isEmpty()) {
                        userModel = getUserResponseModel.getData().get(0);
                    }
                    saveUserData();
                } catch (Exception e) {
                    Crashlytics.logException(e);
                }
                onAPICallSucceed(caller, getUserResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                onAPICallFailed(caller, error);
            }
        });
    }

    public void registerLimakiloUser(DigitsSession digitsSession){
        APICallManager.getInstance().loginDigit(((Long) digitsSession.getId()).toString(),
                digitsSession.getPhoneNumber(), new Callback<LoginResponseModel>() {
                    String caller = "registerLimakiloUser(DigitsSession digitsSession)";

                    @Override
                    public void success(LoginResponseModel loginResponseModel, Response response) {
                        getUserData();
                        saveUserAuthentification(loginResponseModel.getData().get(0).getAuth());
                        onAPICallSucceed(caller, loginResponseModel);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        onAPICallFailed(caller, error);
                    }
                });
    }

    @Override
    public void onAPICallSucceed(String caller, RootResponseModel loginResponseModel) {
        showSuccess();
    }

    @Override
    public void onAPICallFailed(String caller, RetrofitError error) {
        if (caller.equalsIgnoreCase(StateInfo.DIGIT.toString())){
            stateInfo = StateInfo.DIGIT;
        }
        showFailure(error);
    }

    @Override
    public void onCallback(ListenerCaller listenerCaller, ListenerResult listenerResult, Object result) {
        switch (listenerCaller){
            case DIGIT:
                onDigitCallback(listenerResult, result);
                break;
            default:
                break;
        }
    }

    public void onDigitCallback(ListenerResult listenerResult, Object result){
        switch (listenerResult){
            case SUCCESS:
                registerLimakiloUser((DigitsSession) result);
                break;
            default:
                break;
        }
    }

    private void initPopupDialog(){
        popupDialog = new MaterialDialog.Builder(getActivity())
            .positiveText("Bantuan")
            .negativeText("Tutup")
            .callback(new MaterialDialog.ButtonCallback() {
                @Override
                public void onPositive(MaterialDialog dialog) {
                    ConversationActivity.show(getActivity());
                }
                @Override
                public void onNegative(MaterialDialog dialog) {
                    dialog.hide();
                }
            })
            .build();
    }

    private void statePopupDialogContentShown(String message){
        popupDialog.setContent(message);
        popupDialog.show();
    }
}
