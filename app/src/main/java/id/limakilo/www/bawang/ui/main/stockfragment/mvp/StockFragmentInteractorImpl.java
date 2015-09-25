package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

/**
 * Created by walesadanto on 9/24/15.
 */
public class StockFragmentInteractorImpl implements StockFragmentInteractor {

    private StockFragmentListener listener;

    @Override
    public void callAsync(StockFragmentListener.Listener caller, StockFragmentListener.ListenerAction action) {
        switch (caller){
            case FACEBOOK:

                break;
            default:
                break;
        }
    }


}
