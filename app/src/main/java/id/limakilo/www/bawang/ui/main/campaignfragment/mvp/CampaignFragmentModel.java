package id.limakilo.www.bawang.ui.main.campaignfragment.mvp;

import java.util.ArrayList;
import java.util.List;

import id.limakilo.www.bawang.util.api.campaign.GetCampaignResponseModel;

/**
 * Created by walesadanto on 10/9/15.
 */
public class CampaignFragmentModel {
//    public List<GetStockResponseModel.GetStockResponseData> stockList;
        List<GetCampaignResponseModel.GetCampaignResponseData> stockList =
            new ArrayList<GetCampaignResponseModel.GetCampaignResponseData>();

    public void setStockList(List<GetCampaignResponseModel.GetCampaignResponseData> stockList) {
        this.stockList = stockList;
    }

    public List<GetCampaignResponseModel.GetCampaignResponseData> getStockList() {
        return stockList;
    }

    public boolean isModelReady(){
        if (stockList == null){
            return false;
        }
        else if (stockList.isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    public void sortModel(){
//        Collections.sort(stockList, new Comparator<GetStockResponseModel.GetStockResponseData>() {
//            @Override
//            public int compare(GetStockResponseModel.GetStockResponseData lhs, GetStockResponseModel.GetStockResponseData rhs) {
//                return lhs.getSellerName().compareTo(rhs.getSellerName());
//            }
//        });
    }

}
