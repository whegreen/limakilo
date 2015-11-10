package id.limakilo.www.bawang.ui.campaigndetail.mvp;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.campaign.GetCampaignDetailResponseModel;

/**
 * Created by walesadanto on 10/13/15.
 */
public class CampaignDetailModel {

    private GetCampaignDetailResponseModel.GetCampaignDetailData campaignDetailModel;

    public CampaignDetailModel(){
        campaignDetailModel = new GetCampaignDetailResponseModel().new GetCampaignDetailData();
    }

    private List<GetCampaignDetailResponseModel.Package> orderList =
            new ArrayList<GetCampaignDetailResponseModel.Package>();

    public GetCampaignDetailResponseModel.GetCampaignDetailData getCampaignDetailModel() {
        return campaignDetailModel;
    }

    public void setCampaignDetailModel(GetCampaignDetailResponseModel.GetCampaignDetailData campaignDetailModel) {
        this.campaignDetailModel = campaignDetailModel;
    }

    public void setOrderList(List<GetCampaignDetailResponseModel.Package> orderList) {
        this.orderList = orderList;
    }

    public List<GetCampaignDetailResponseModel.Package> getOrderList() {
        return orderList;
    }

}
