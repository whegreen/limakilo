package com.limakilogram.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl (LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void doFacebookLogin() {

    }

    @Override
    public void doDigitLogin() {

    }

    @Override
    public void doLoginLater() {

    }

    // Login Listener Implementation
    @Override
    public void loginSuccess() {
        loginView.openMainActivity();
    }

    @Override
    public void loginSuccess(LoginType loginType) {
        loginView.openMainActivity();
    }

    @Override
    public void loginCancel(LoginType loginType) {

    }

    @Override
    public void loginError(LoginType loginType) {

    }

}
