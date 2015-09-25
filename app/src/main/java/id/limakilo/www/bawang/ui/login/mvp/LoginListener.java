package id.limakilo.www.bawang.ui.login.mvp;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginListener {

    public enum ListenerCaller{
        FACEBOOK, DIGIT, LATER
    }

    public enum ListenerAction{
        FACEBOOK_AUTHORIZATION
    }

    public enum ListenerResult{
        SUCCESS, FAILURE, CANCEL
    }

    public void onCallback(ListenerCaller listenerType, ListenerResult listenerResult, Object result);

}
