package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginPresenter {

    // public dialog dialog
    // public void prepare dialog
    // public void show dialog
    // public void hide dialog

    public enum PresenterState {
        LOGIN,
    }
    public void presentState(LoginView.ViewState state);
    public void callAsync(LoginListener.ListenerCaller caller, LoginListener.ListenerAction action);
}
