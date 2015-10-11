package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

/**
 * Created by walesadanto on 9/24/15.
 */
public interface StockFragmentInteractor {
    public void callAsync(StockFragmentListener.Listener caller, StockFragmentListener.ListenerAction action);

    void callAPIGetStocks();
}
