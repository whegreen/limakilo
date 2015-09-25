package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface StockFragmentView{

    public enum State{
        IDLE, SUCCESS, FAILURE, CANCEL, LOADING, ERROR
    }

    public enum StateInfo{
        API, NO_ACTION
    }

    public enum StateAction{

    }

    public void showViewState(State state, StateInfo info);

}
