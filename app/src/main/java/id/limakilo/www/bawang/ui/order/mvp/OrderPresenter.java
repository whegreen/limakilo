package id.limakilo.www.bawang.ui.order.mvp;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface OrderPresenter {

    public void showLoading(boolean state);
    public void refreshDetailOrder(String orderId);

}
