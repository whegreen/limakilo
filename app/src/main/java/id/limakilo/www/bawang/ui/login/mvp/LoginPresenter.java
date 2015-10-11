package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginPresenter {

    public void presentState(LoginView.ViewState state);
    public void callAsync(LoginListener.ListenerCaller caller, LoginListener.ListenerAction action);
}
