package id.limakilo.www.bawang.ui.order.mvp;

import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;

/**
 * Created by walesadanto on 10/11/15.
 */
public class OrderModel {

    private PostOrderResponseModel.PostOrderResponseData postOrderModel;
    private GetStockDetailResponseModel.GetStockDetailResponseData stockDetailModel;
    private PutUserResponseModel.PutUserResponseData userModel;

    public OrderModel(){
        postOrderModel = new PostOrderResponseModel().new PostOrderResponseData();
        stockDetailModel = new GetStockDetailResponseModel().new GetStockDetailResponseData();
        userModel = new PutUserResponseModel().new PutUserResponseData();
    }

    public void setPostOrderModel(PostOrderResponseModel.PostOrderResponseData postOrderModel) {
        this.postOrderModel = postOrderModel;
    }

    public void setStockDetailModel(GetStockDetailResponseModel.GetStockDetailResponseData stockDetailModel) {
        this.stockDetailModel = stockDetailModel;
    }

    public void setUserModel(PutUserResponseModel.PutUserResponseData userModel) {
        this.userModel = userModel;
    }

    public GetStockDetailResponseModel.GetStockDetailResponseData getStockDetailModel() {
        return stockDetailModel;
    }

    public PostOrderResponseModel.PostOrderResponseData getPostOrderModel() {
        return postOrderModel;
    }

    public PutUserResponseModel.PutUserResponseData getUserModel() {
        return userModel;
    }

}
