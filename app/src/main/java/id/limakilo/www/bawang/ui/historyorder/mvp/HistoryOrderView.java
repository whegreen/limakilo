package id.limakilo.www.bawang.ui.historyorder.mvp;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface HistoryOrderView {

    public enum ViewState {
        IDLE, LOGGING_IN, LOGIN_LATER, INPUT_INVITATION_CODE, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }

    public void showState(ViewState state);

}
