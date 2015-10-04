package id.limakilo.www.bawang.ui.login.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;

/**
 * Created by walesadanto on 23/6/15.
 */
public interface LoginListener extends APICallListener{

    public enum ListenerCaller{
        DIGIT, LATER
    }

    public enum ListenerAction{
        FACEBOOK_AUTHORIZATION, DIGIT_LOGIN, LATER_LOGIN
    }

    public enum ListenerResult{
        SUCCESS, FAILURE, CANCEL
    }

    public void onCallback(ListenerCaller listenerType, ListenerResult listenerResult, Object result);

}
