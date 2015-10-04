package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

/**
 * Created by walesadanto on 22/8/15.
 */
public class StockFragmentPresenterImpl implements StockFragmentPresenter {
    public StockFragmentView view;

    public StockFragmentPresenterImpl(StockFragmentView view){
        this.view = view;
    }


    @Override
    public void presentState(PresenterState state) {
        switch (state){
            case IDLE :

                break;
            default:
                break;
        }
    }
}

