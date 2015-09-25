package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginPresenter {
    public void showState(LoginView.State state, LoginView.StateAction action);
    public void showState(LoginView.State state);

}
