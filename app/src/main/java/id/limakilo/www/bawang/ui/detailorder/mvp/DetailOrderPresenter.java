package id.limakilo.www.bawang.ui.detailorder.mvp;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface DetailOrderPresenter {

    public void showLoading(boolean state);
    public void refreshDetailOrder(String orderId);

}
