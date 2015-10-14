package id.limakilo.www.bawang.ui.order.mvp;

/**
 * Created by walesadanto on 10/11/15.
 */
public interface OrderInteractor {
    void callAPIGetOrder(String authentification, String orderId, String orderAmount, String name);

    void callAPIPostOrder(String authentification, String stockId, String orderAmount,
                          String alamatPengiriman, String stockPrice, String kotaPenerima, String email);

    void callAPIPutUser(String authentification, String address, String phone, String email, String city);

    void callAPIGetStockDetails(String authentification, String stockId);
}
