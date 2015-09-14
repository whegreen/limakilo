package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginListener {

    public enum LoginType{
        FACEBOOK, DIGIT
    }
    public void loginSuccess();
    public void loginSuccess(LoginType loginType);
    public void loginCancel(LoginType loginType);
    public void loginError(LoginType loginType);

}
