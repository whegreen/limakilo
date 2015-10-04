package id.limakilo.www.bawang.ui.main.stockfragment.mvp;


/**
 * Created by walesadanto on 22/8/15.
 */
public interface StockFragmentPresenter {

    public enum PresenterState {
        IDLE, LOAD_DATA, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }

    public void presentState(PresenterState state);
}
