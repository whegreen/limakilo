package id.limakilo.www.bawang.ui.detailorder.mvp;

import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockResponseModel;

/**
 * Created by walesadanto on 1/9/15.
 */
public class DetailOrderModel {

    GetOrderDetailResponseModel.GetOrderDetailResponseData orderModel;
    GetStockResponseModel.GetStockResponseData stockModel;

    DetailOrderModel(GetOrderDetailResponseModel.GetOrderDetailResponseData orderModel,
                     GetStockResponseModel.GetStockResponseData stockModel){
        this.orderModel = orderModel;
        this.stockModel = stockModel;
    }

    public String getOrderId(){
        return orderModel.getId().toString();
    }

    public String getOrderDate(){
        return getOrderDate().toString();
    }

    public String getUserFullName(){
        return "";
    }

    public String getOrderQuantity(){
        return orderModel.getQuantity();
    }

    public String getStockName(){
        return stockModel.getStockName();
    }

    public String getStockPrice(){
        return stockModel.getStockPrice();
    }

    public String getOrderPaymentCode(){
        return "";
    }

    public String getOrderDeliveryCost(){
        return "";
    }

    public String getOrderTotalPayment(){
        return "";
    }

    public String getOrderStatus(){
        return orderModel.getStatus();
    }

}
