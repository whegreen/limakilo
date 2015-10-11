package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;

/**
 * Created by walesadanto on 10/9/15.
 */
public class StockFragmentModel {
//    public List<GetStockResponseModel.GetStockResponseData> stockList;
        List<GetStockResponseModel.GetStockResponseData> stockList =
            new ArrayList<GetStockResponseModel.GetStockResponseData>();

    public void setStockList(List< GetStockResponseModel.GetStockResponseData> stockList) {
        this.stockList = stockList;
    }

    public List<GetStockResponseModel.GetStockResponseData> getStockList() {
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
        Collections.sort(stockList, new Comparator<GetStockResponseModel.GetStockResponseData>() {
            @Override
            public int compare(GetStockResponseModel.GetStockResponseData lhs, GetStockResponseModel.GetStockResponseData rhs) {
                return lhs.getSellerName().compareTo(rhs.getSellerName());
            }
        });
    }

}
