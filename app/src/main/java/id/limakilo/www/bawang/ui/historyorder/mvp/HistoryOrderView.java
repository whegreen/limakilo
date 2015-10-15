package id.limakilo.www.bawang.ui.historyorder.mvp;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface HistoryOrderView {

    String doRetrieveFullname();

    public enum ViewState {
        IDLE, LOADING, LOAD_ORDERS, API_ERROR, SHOW_ORDERS, CONFIRM_ORDER, SHOW_DETAIL_ORDER, SHOW_CONFIRM_DIALOG, BLANK_STATE, LOAD_ORDER, LOAD_STOCK, SHOW_FINISH_DIALOG
    }

    public void showState(ViewState state);

    String doRetrieveAuthentification();

    public HistoryOrderModel doRetrieveModel();

}
