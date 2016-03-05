package id.limakilo.www.bawang.ui.campaigndetail.mvp;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.campaign.GetCampaignDetailResponseModel;

/**
 * Created by walesadanto on 10/13/15.
 */
public class CampaignDetailModel {

    private GetCampaignDetailResponseModel.GetCampaignDetailData campaignDetailModel;

//    private List<GetCampaignDetailResponseModel.Package> stockList;
    private List<GetCampaignDetailResponseModel.GetCampaignDetailData> contributorList;
//    private List<GetCampaignDetailResponseModel.Feedback> feedbackList;
//    private GetCampaignDetailResponseModel.Campaign campaign;

    private String campaignId;


    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    public CampaignDetailModel(){
        campaignDetailModel = new GetCampaignDetailResponseModel().new GetCampaignDetailData();
    }

    private List<GetCampaignDetailResponseModel.GetCampaignDetailData> orderList =
            new ArrayList<GetCampaignDetailResponseModel.GetCampaignDetailData>();

    public GetCampaignDetailResponseModel.GetCampaignDetailData getCampaignDetailModel() {
        return campaignDetailModel;
    }

    public void setCampaignDetailModel(GetCampaignDetailResponseModel.GetCampaignDetailData campaignDetailModel) {
        this.campaignDetailModel = campaignDetailModel;

//        campaign = campaignDetailModel.getCampaign().get(0);
//        stockList = campaignDetailModel.getPackage();
//        contributorList = campaignDetailModel;
//        feedbackList = campaignDetailModel.getFeedback();

    }

    public void setOrderList(List<GetCampaignDetailResponseModel.GetCampaignDetailData> orderList) {
        this.orderList = orderList;
    }

    public List<GetCampaignDetailResponseModel.GetCampaignDetailData> getOrderList() {
        return orderList;
    }

//    public GetCampaignDetailResponseModel.Campaign getCampaign() {
//        return campaign;
//    }

    public List<GetCampaignDetailResponseModel.GetCampaignDetailData> getContributorList() {
        return contributorList;
    }

//    public List<GetCampaignDetailResponseModel.Feedback> getFeedbackList() {
//        return feedbackList;
//    }

//    public List<GetCampaignDetailResponseModel.Package> getStockList() {
//        return stockList;
//    }
}
