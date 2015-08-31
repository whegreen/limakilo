package com.limakilogram.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 25/6/15.
 */
public interface LoginView {

    public enum LoginState{
        IDLE, SUCCESS, FAILURE, CANCEL
    }

    public void openMainActivity();

}
