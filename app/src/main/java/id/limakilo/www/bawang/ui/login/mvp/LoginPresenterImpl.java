package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginListener{

    private LoginView loginView;
    private LoginInteractor loginInteractor;

    private LoginView.LoginState currentState;
    private LoginView.LoginStateInfo stateInfo;

    public LoginPresenterImpl (LoginView loginView){
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
    }


    // Login Listener Implementation
    @Override
    public void loginSuccess() {
    }

    @Override
    public void loginSuccess(LoginType loginType) {
    }

    @Override
    public void loginCancel(LoginType loginType) {

    }

    @Override
    public void loginError(LoginType loginType) {

    }

    @Override
    public void showLoginState(LoginView.LoginState state) {
        LoginView.LoginState prevState = currentState;
        currentState = state;

        switch (currentState) {
            case LOGGING_IN:
                break;
            case IDLE:
                break;
            case FAILURE:
                break;
            case CANCEL:
                break;
            case SUCCESS:
                break;
            default:
                break;
        }

    }

    public LoginView.LoginStateInfo getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(LoginView.LoginStateInfo stateInfo) {
        this.stateInfo = stateInfo;
    }
}
