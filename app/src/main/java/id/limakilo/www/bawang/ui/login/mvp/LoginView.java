package id.limakilo.www.bawang.ui.login.mvp;

import id.limakilo.www.bawang.util.api.user.GetUserResponseModel;
import id.limakilo.www.bawang.util.api.user.LoginResponseModel;

/**
 * Created by walesadanto on 25/6/15.
 */
public interface LoginView {

    void doSaveUserModel(LoginResponseModel.LoginResponseData userModel);

    void doSaveUserAuthentification(String auth);

    void doSaveUserData(GetUserResponseModel.GetUserResponseData userModel);

    void setUserModel(GetUserResponseModel.GetUserResponseData userModel);

    public enum ViewState {
        IDLE, LOGGING_IN, LOGIN_LATER, INPUT_INVITATION_CODE, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }

    public void doLoginLater();

    public void showState(ViewState state);

    public void doCheckLogin();
    public void doShowDialogError(Exception e);

}
