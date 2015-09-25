package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 25/6/15.
 */
public interface LoginView {

    public enum State {
        IDLE, LOGGING_IN, INPUT_INVITATION_CODE, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }

    //login digit --> check di server invitation code ada ngga --> OK --> lanjut app
    //login digit --> check di server invitation code ada ngga --> ga oke --> tampilin dialog input code invitation / masuk ke webview untuk get invitation code --> share twitter / fb

    public enum StateAction {
        FACEBOOK, DIGIT, API, NO_ACTION
    }

    public enum StateInfo {
        DIGIT, FACEBOOK, AUTO_LOGIN, GET_USER
    }

    public void showState(State state, StateInfo info);

    public void showIdle();
    public void stateLoggingIn();
    public void showSuccess();
    public void showFailure(Exception e);
    public void showCancel();

    public void stateError(String message);
    public void stateIdle();
    public void stateLoading();

}
