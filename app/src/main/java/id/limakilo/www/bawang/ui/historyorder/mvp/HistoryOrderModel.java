package id.limakilo.www.bawang.ui.historyorder.mvp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;

/**
 * Created by walesadanto on 10/13/15.
 */
public class HistoryOrderModel {

    private GetOrderDetailResponseModel.GetOrderDetailResponseData orderDetailModel;
    private GetStockDetailResponseModel.GetStockDetailResponseData stockDetailModel;
    private String totalPayment = "0";

    public HistoryOrderModel(){
        orderDetailModel = new GetOrderDetailResponseModel().new GetOrderDetailResponseData();
        stockDetailModel = new GetStockDetailResponseModel().new GetStockDetailResponseData();
    }

    private List<GetOrderResponseModel.GetOrderResponseData> orderList =
            new ArrayList<GetOrderResponseModel.GetOrderResponseData>();

    public GetStockDetailResponseModel.GetStockDetailResponseData getStockDetailModel() {
        return stockDetailModel;
    }

    public void setStockDetailModel(GetStockDetailResponseModel.GetStockDetailResponseData stockDetailModel) {
        this.stockDetailModel = stockDetailModel;
    }

    public GetOrderDetailResponseModel.GetOrderDetailResponseData getOrderDetailModel() {
        return orderDetailModel;
    }

    public void setOrderDetailModel(GetOrderDetailResponseModel.GetOrderDetailResponseData orderDetailModel) {
        this.orderDetailModel = orderDetailModel;
    }

    public List<GetOrderResponseModel.GetOrderResponseData> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<GetOrderResponseModel.GetOrderResponseData> orderList) {
        this.orderList = orderList;
    }

    public String capitalize(String word){
        return word.substring(0,1).toUpperCase() + word.substring(1);
    }

    public String decimalFormat(Double number){
        Locale locale  = new Locale("id", "ID");
        String pattern = "###,###.##";

        DecimalFormat decimalFormat = (DecimalFormat)
                NumberFormat.getNumberInstance(locale);
        decimalFormat.applyPattern(pattern);

        return decimalFormat.format(number);
    }

    public String getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        this.totalPayment = totalPayment;
    }
}
