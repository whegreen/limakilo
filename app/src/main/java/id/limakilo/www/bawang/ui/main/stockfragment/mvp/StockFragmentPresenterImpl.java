package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

import static id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentView.State;
import static id.limakilo.www.bawang.ui.main.stockfragment.mvp.StockFragmentView.StateAction;

/**
 * Created by walesadanto on 22/8/15.
 */
public class StockFragmentPresenterImpl implements StockFragmentPresenter {
    public StockFragmentView view;

    public StockFragmentPresenterImpl(StockFragmentView view){
        this.view = view;
    }


    @Override
    public void showState(State state, StateAction action) {
        switch (state){
            case IDLE :

                break;
            default:
                break;
        }
    }
}

