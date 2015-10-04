package id.limakilo.www.bawang.ui.main.grosirfragment.mvp;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface GrosirFragmentView {

    public enum ViewState {
        IDLE, LOAD_DATA, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }
    public void showState(ViewState state);

}
