package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 25/6/15.
 */
public interface LoginView {

    public enum LoginState{
        IDLE, LOGGING_IN, SUCCESS, FAILURE, CANCEL
    }

    public enum LoginStateInfo{
        DIGIT, FACEBOOK, AUTO_LOGIN, GET_USER
    }

    public void showIdle();
    public void showLoggingIn();
    public void showSuccess();
    public void showFailure(Exception e);
    public void showCancel();

}
