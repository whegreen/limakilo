package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface StockFragmentView{

    public enum ViewState {
        IDLE, LOAD_DATA, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }
    public void showState(ViewState state);

}
