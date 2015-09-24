package id.limakilo.www.bawang.ui.login;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.digits.sdk.android.AuthCallback;
import com.digits.sdk.android.DigitsAuthButton;
import com.digits.sdk.android.DigitsException;
import com.digits.sdk.android.DigitsSession;
import com.facebook.AccessToken;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.login.mvp.LoginListener;
import id.limakilo.www.bawang.ui.login.mvp.LoginPresenter;
import id.limakilo.www.bawang.ui.login.mvp.LoginPresenterImpl;
import id.limakilo.www.bawang.ui.login.mvp.LoginView;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import id.limakilo.www.bawang.util.social.SupportkitKit;
import io.supportkit.ui.ConversationActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class LoginFragment extends Fragment implements LoginView, APICallListener {

    private static final String TAG = "Login Fragment";

    private LoginButton loginButton;
    private View view;
    private LoginPresenter presenter;

    private View loadingBar;

    private GetUserResponseModel.GetUserResponseData userModel;

    public LoginState state = LoginState.IDLE;
    public LoginStateInfo stateInfo = LoginStateInfo.AUTO_LOGIN;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_login, container, false);
        presenter = new LoginPresenterImpl(this);

        if (AccessToken.getCurrentAccessToken() != null) {
            ((LoginPresenterImpl) presenter).loginSuccess();
            return view;
        }

        loadingBar = view.findViewById(R.id.loading_bar);

        loginButton = (LoginButton) view.findViewById(R.id.login_button);

        loginButton.setReadPermissions("user_friends");
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization

        // Callback registration
        loginButton.registerCallback(((LoginActivity) getActivity()).getCallbackManager(), new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                final String userId = loginResult.getAccessToken().getUserId();

                Bundle params = new Bundle();
                params.putString("fields", "first_name,last_name");

                /* make the API call */
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {

                            }
                        }
                ).executeAsync();

            }

            @Override
            public void onCancel() {
                // App code
                ((LoginPresenterImpl) presenter).loginCancel(LoginListener.LoginType.FACEBOOK);
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                ((LoginPresenterImpl) presenter).loginError(LoginListener.LoginType.FACEBOOK);
            }
        });

        final DigitsAuthButton digitsButton = (DigitsAuthButton) view.findViewById(R.id.auth_button);
        digitsButton.setBackgroundColor(getResources().getColor(R.color.color_primary_dark));
        digitsButton.setTextSize(12);
        digitsButton.setText("LOGIN USING PHONE NUMBER");
        digitsButton.setTypeface(null, Typeface.BOLD);
        ((LoginActivity) getActivity()).setTwitterAuthCallback(new AuthCallback() {
            @Override
            public void success(DigitsSession digitsSession, String s) {
                Crashlytics.log(Log.INFO, "digit login", s);
                registerLimakiloUser(digitsSession);
            }

            @Override
            public void failure(DigitsException e) {
                ((LoginPresenterImpl) presenter).loginCancel(LoginListener.LoginType.DIGIT);
            }
        });

        digitsButton.setCallback(((LoginActivity) getActivity()).getTwitterAuthCallback());

        Button loginLater = (Button) view.findViewById(R.id.btn_login_later);
        loginLater.setBackgroundColor(getResources().getColor(R.color.color_primary_dark));
        loginLater.setTextSize(12);
        loginLater.setText("LOGIN LATER");
        loginLater.setTypeface(null, Typeface.BOLD);
        loginLater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferencesManager.saveAuthToken(getActivity(), APICallManager.DEMO_AUTH);
                openMainActivity();
            }
        });

        showLoggingIn();

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((LoginActivity) getActivity()).getCallbackManager().onActivityResult(requestCode, resultCode, data);
    }

    public void openMainActivity() {
        final Intent mainIntent = new Intent(getActivity(), MainActivity.class);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(mainIntent);
            }
        }, 300L);
    }

    @Override
    public void showIdle() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoggingIn() {
        loadingBar.setVisibility(View.VISIBLE);
        String auth = PreferencesManager.getAuthToken(getContext());
        if (auth != null && auth.equalsIgnoreCase("")) {
            showSuccess();
        } else {
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
                new SupportkitKit().setupUser();
                ConversationActivity.show(getActivity());
            }
        }).show();
    }

    @Override
    public void showCancel() {
        showIdle();
    }


    public void saveUserAuthentification(String auth) {
        PreferencesManager.saveAsString(getContext(), PreferencesManager.AUTH_TOKEN, auth);

    }

    public void saveUserData() {

        PreferencesManager.saveAsString(getContext(), PreferencesManager.USER_ID, userModel.getUserId().toString());
        PreferencesManager.saveAsString(getContext(), PreferencesManager.HANDPHONE, userModel.getUserPhone());
        PreferencesManager.saveAsString(getContext(), PreferencesManager.LAST_LOGIN_TS, String.valueOf(System.currentTimeMillis()));

        if (userModel.getUserFirstName() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.NAME, userModel.getUserFirstName() + " " + userModel.getUserLastName());

        if (userModel.getUserAddress() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.ADDRESS, userModel.getUserAddress());

        if (userModel.getUserAvatarUrl() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.AVATAR_URL, userModel.getUserAvatarUrl());

        if (userModel.getUserCoverUrl() != null)
            PreferencesManager.saveAsString(getContext(), PreferencesManager.COVER_URL, userModel.getUserCoverUrl());

        //supportkit user
        SupportkitKit supportkitKit = new SupportkitKit();
        supportkitKit.setupUser();
    }

    public void getUserData() {
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
//                    onAPICallFailed(caller, (RetrofitError) e);
                }
                onAPICallSucceed(caller, getUserResponseModel);
            }

            @Override
            public void failure(RetrofitError error) {
                onAPICallFailed(caller, error);
            }
        });
    }

    public void registerLimakiloUser(DigitsSession digitsSession) {
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
        if (caller.equalsIgnoreCase(LoginStateInfo.DIGIT.toString())) {
            stateInfo = LoginStateInfo.DIGIT;
        }
        showFailure(error);
    }
}
