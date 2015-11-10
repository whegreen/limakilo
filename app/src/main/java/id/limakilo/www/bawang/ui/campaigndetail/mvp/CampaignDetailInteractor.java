package id.limakilo.www.bawang.ui.campaigndetail.mvp;

/**
 * Created by walesadanto on 10/13/15.
 */
public interface CampaignDetailInteractor {


    void callAPIGetCampaignDetail(String authentification, String campaignId);

    void callAPIGetOrders(String authentification);

    void callAPIStockDetail(String authentification, String stockId);

    void callAPIOrderDetail(String authentification, String orderId);

    void callAPIConfirmOrder(String authentification, String orderId, String totalPayment, String fullName);
}
