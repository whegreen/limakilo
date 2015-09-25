package id.limakilo.www.bawang.ui.login.mvp;

import id.limakilo.www.bawang.ui.login.mvp.LoginView.StateAction;
import id.limakilo.www.bawang.ui.login.mvp.LoginView.State;

/**
 * Created by walesadanto on 23/6/15.
 */
public class LoginPresenterImpl implements LoginPresenter, LoginListener{

    private LoginView view;

    private LoginView.State currentState;
    private LoginView.StateInfo stateInfo;

    public LoginPresenterImpl (LoginView view){
        this.view = view;
    }


    // Login Listener Implementation


    @Override
    public void showState(LoginView.State state, StateAction action) {
        LoginView.State prevState = currentState;
        currentState = state;

        switch (currentState) {
            case LOGGING_IN:
                break;
            case IDLE:
                view.stateIdle();
                break;
            case FAILURE:
                break;
            case CANCEL:
                break;
            case SUCCESS:
                break;
            case LOADING:
                view.stateLoading();
                break;
            case ERROR:
                view.stateError("error");
                break;
            default:
                break;
        }

    }

    @Override
    public void showState(State state) {
        showState(state, LoginView.StateAction.NO_ACTION);
    }

//    public void showViewState(StateAction action){
//        switch (action){
//
//        }
//    }

    public LoginView.StateInfo getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(LoginView.StateInfo stateInfo) {
        this.stateInfo = stateInfo;
    }

    @Override
    public void onCallback(ListenerCaller listenerType, ListenerResult listenerResult, Object result) {

    }

}
