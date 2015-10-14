package id.limakilo.www.bawang.ui.historyorder.mvp;

/**
 * Created by walesadanto on 10/13/15.
 */
public interface HistoryOrderInteractor {

    void callAPIGetOrders(String authentification);

    void callAPIStockDetail(String authentification, String stockId);

    void callAPIOrderDetail(String authentification, String orderId);

    void callAPIConfirmOrder(String authentification, String orderId, String totalPayment, String fullName);
}
