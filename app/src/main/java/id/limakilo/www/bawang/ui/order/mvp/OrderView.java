package id.limakilo.www.bawang.ui.order.mvp;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface OrderView {

    String doRetrieveUserAuth();
    String doRetrieveUserFullname();

    public enum OrderProcessState{
        ORDER, PAYMENT
    }

    public enum ViewState {
        IDLE, LOADING, CONFIRM_PAYMENT, ERROR_CONFIRM_ORDER, ORDER_PAID, ORDER_PROCESSED, POST_ORDER,
        TOGGLE_KEYBOARD, SHOW_USER_INFO, LOAD_ORDERS, STOCK_NOT_AVAILABLE, SHOW_STOCK_LIST, LOAD_STOCK
    }

    public void showState(ViewState state);

    void doUpdateModel(RootResponseModel responseModel);
    public OrderModel doRetrieveModel();

}
