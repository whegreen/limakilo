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
import android.widget.Button;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsSession;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.login.mvp.LoginListener;
import id.limakilo.www.bawang.ui.login.mvp.LoginPresenterImpl;
import id.limakilo.www.bawang.ui.login.mvp.LoginView;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.social.SupportkitKit;
import io.supportkit.ui.ConversationActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.CANCEL;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.FAILURE;
import static id.limakilo.www.bawang.ui.login.mvp.LoginListener.ListenerResult.SUCCESS;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements LoginView {

    private static final String TAG = "Login Fragment";

    private View view;
    private LoginPresenterImpl presenter;

    private GetUserResponseModel.GetUserResponseData userModel;

    public ViewState state = ViewState.IDLE;

    MaterialDialog popupDialog;

    //binding view
    @Bind(R.id.login_button) LoginButton facebookLoginButton;
    @Bind(R.id.login_button_limakilo) Button login_btn;
    @Bind(R.id.btn_login_later) TextView loginLater;
    @Bind(R.id.btn_digit_login) DigitsAuthButton digitsButton;
    @Bind(R.id.loading_bar) View loadingView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        facebookLoginButton.setCompoundDrawablesWithIntrinsicBounds(0,0,0,0);
        facebookLoginButton.setBackgroundResource(R.color.color_primary_dark);

        presenter = new LoginPresenterImpl(this);

        initPopupDialog();
        initFacebookLoginButton();
        initDigitLoginButton();

        doCheckLogin();

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
    public void onClickLoginLater(){
        presenter.presentState(ViewState.LOGIN_LATER);
    }

    @OnClick(R.id.login_button_limakilo)
    public void onClickLoginLimakilo(){
        facebookLoginButton.performClick();
    }

    // facebook stuff
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getLoginActivity().getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void doLoginLater() {
        PreferencesManager.saveAuthToken(getActivity(), APICallManager.DEMO_AUTH);
        doOpenMainActivity();
    }

    private void doOpenMainActivity() {
        final Activity loginActivity = getActivity();
        final Intent mainIntent = new Intent(loginActivity, MainActivity.class);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
                showState(ViewState.IDLE);
                loginActivity.finish();
                presenter.presentState(ViewState.IDLE);
                handler.removeCallbacks(this);
            }
        }, 300L);
    }

    @Override
    public void showState(ViewState state) {
        switch (state){
            case LOADING:
                loadingView.setVisibility(View.VISIBLE);
                break;
            case IDLE:
                loadingView.setVisibility(View.GONE);
                break;
            case SUCCESS:
                presenter.presentState(ViewState.IDLE);
                doOpenMainActivity();
                break;
            default:
                break;
        }
    }

    @Override
    public void doCheckLogin() {
        loadingView.setVisibility(View.VISIBLE);
        String auth = PreferencesManager.getAuthToken(getContext());
        if (auth != null && !auth.equalsIgnoreCase("")){
//            doOpenMainActivity();
            presenter.presentState(ViewState.LOGGING_IN);
        }
        else {
            presenter.presentState(ViewState.IDLE);
        }
    }

    @Override
    public void doShowDialogUpdate() {
        presenter.presentState(ViewState.IDLE);
        ((LoginActivity)getActivity()).showDialogWebview();
    }

    @Override
    public String doGetAuthentification() {
        return PreferencesManager.getInstance().getAuthToken(getContext());
    }

    @Override
    public void doShowDialogError(Exception e) {
        presenter.presentState(ViewState.IDLE);
        Crashlytics.log(Log.ERROR, TAG, "login failure : ");
        Snackbar.make(view, "maaf kami mengalami masalah...", Snackbar.LENGTH_SHORT).setAction("Bantuan", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConversationActivity.show(getActivity());
            }
        }).show();
    }

    @Override
    public void doSaveUserModel(LoginResponseModel.LoginResponseData userModel){
    }

    @Override
    public void doSaveUserAuthentification(String auth){
        PreferencesManager.saveAuthToken(getContext(), auth);
        doOpenMainActivity();
    }

    @Override
    public void doSaveUserData(GetUserResponseModel.GetUserResponseData userData){
        this.userModel = userData;

        PreferencesManager.saveAsString(getContext(), PreferencesManager.USER_ID, userModel.getUserId().toString());
        PreferencesManager.saveAsString(getContext(), PreferencesManager.HANDPHONE, userModel.getUserPhone());
        PreferencesManager.saveAsString(getContext(), PreferencesManager.LAST_LOGIN_TS, String.valueOf(System.currentTimeMillis()));

        if (userModel.getUserFirstName() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.FIRST_NAME, userModel.getUserFirstName()+" "+userModel.getUserLastName());

        if (userModel.getUserLastName() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.LAST_NAME, userModel.getUserFirstName()+" "+userModel.getUserLastName());

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

    }

    public void doRegisterLimakiloUser(DigitsSession digitsSession){

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

    @Override
    public void setUserModel(GetUserResponseModel.GetUserResponseData userModel) {
        this.userModel = userModel;
    }

    @Override
    public String doGetAppVersion() {
        return getResources().getString(R.string.app_version);
    }

    @Override
    public Activity doGetActivity() {
        return getActivity();
    }

    private void initDigitLoginButton(){
        getLoginActivity().setTwitterAuthCallback(presenter.getDigitCallback());
        digitsButton.setCallback(((LoginActivity) getActivity()).getTwitterAuthCallback());
    }

    private void initFacebookLoginButton(){
//        Session activeSession = Session.getActiveSession();
//        //Checks for an open session
//        if (!(activeSession == null || activeSession.getState().isClosed())) {
//            //if found one, kill it.
//            activeSession.closeAndClearTokenInformation();
//        }

        facebookLoginButton.setReadPermissions("public_profile");
        facebookLoginButton.setReadPermissions("user_friends");
        facebookLoginButton.setReadPermissions("email");
        facebookLoginButton.setFragment(this);
        facebookLoginButton.registerCallback(((LoginActivity) getActivity()).getCallbackManager(), facebookCallback);
    }

    //facebook stuffs
    private FacebookCallback facebookCallback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            // App code
            presenter.onCallback(LoginListener.ListenerCaller.FACEBOOK, SUCCESS, loginResult);
        }

        @Override
        public void onCancel() {
            // App code
            presenter.onCallback(LoginListener.ListenerCaller.FACEBOOK, CANCEL, null);
        }

        @Override
        public void onError(FacebookException exception) {
            // App code
            presenter.onCallback(LoginListener.ListenerCaller.FACEBOOK, FAILURE, exception);
            initPopupDialog();
        }
    };
}
