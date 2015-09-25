package id.limakilo.www.bawang.ui.main.stockfragment.mvp;
import id.limakilo.www.bawang.util.api.APICallListener;

/**
 * Created by walesadanto on 9/24/15.
 */
public interface StockFragmentListener extends APICallListener{

    public enum Listener {
        FACEBOOK, DIGIT, LATER
    }

    public enum ListenerAction{
        FACEBOOK_AUTHORIZATION
    }

    public enum ListenerResult{
        SUCCESS, FAILURE, CANCEL
    }

    public void onListenerCallback(Listener listenerType, ListenerResult listenerResult, Object result);

}
