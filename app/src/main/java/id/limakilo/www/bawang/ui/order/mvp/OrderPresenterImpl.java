package id.limakilo.www.bawang.ui.order.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 1/9/15.
 */
public class OrderPresenterImpl implements OrderPresenter, APICallListener {

    private OrderView view;
    private OrderInteractorImpl interactor;

    public OrderPresenterImpl(OrderView view) {
        this.view = view;
        this.interactor = new OrderInteractorImpl(this);
    }

    @Override
    public void presentState(OrderView.ViewState state) {
        switch (state) {
            case LOADING:
                view.showState(OrderView.ViewState.LOADING);
                break;
            case IDLE:
                view.showState(OrderView.ViewState.IDLE);
                break;
            case POST_ORDER:
                presentState(OrderView.ViewState.IDLE);
                OrderModel model = view.doRetrieveModel();
                interactor.callAPIPostOrder(
                        view.doRetrieveUserAuth(),
                        model.getStockDetailModel().getStockId().toString(),
                        model.getPostOrderModel().getOrderQuantity().toString(),
                        model.getUserModel().getUserAddress().toString(),
                        model.getStockDetailModel().getStockPrice(),
                        model.getUserModel().getUserCity(),
                        model.getUserModel().getUserEmail().toString()
//                    orderActivity.getStockModel().getStockId().toString(),
//                    orderActivity.getOrderFragment().cardInputOrderViewHolder.orderAmount.getText().toString(),
//                    alamatPengiriman.getText().toString(),
//                    orderActivity.getStockModel().getStockPrice(),
//                    kotaPenerima.getSelectedItem().toString(),
//                    email.getText().toString()
                );
                presentState(OrderView.ViewState.LOADING);
                break;
            case CONFIRM_PAYMENT:
                presentState(OrderView.ViewState.LOADING);
                PostOrderResponseModel.PostOrderResponseData order = view.doRetrieveModel().getPostOrderModel();
                interactor.callAPIGetOrder(
                        view.doRetrieveUserAuth(),
                        order.getOrderId().toString(),
                        order.getOrderAmount(),
                        view.doRetrieveUserFullname()
                );
                break;
            case ORDER_PAID:
                view.showState(OrderView.ViewState.ORDER_PAID);
                break;
            case ORDER_PROCESSED:
                view.showState(OrderView.ViewState.ORDER_PROCESSED);
                break;
            case SHOW_STOCK_LIST:
                view.showState(OrderView.ViewState.SHOW_STOCK_LIST);
                break;
            case ERROR_CONFIRM_ORDER:
                view.showState(OrderView.ViewState.ERROR_CONFIRM_ORDER);
                break;
            case LOAD_STOCK:
                interactor.callAPIGetStockDetails(view.doRetrieveUserAuth(),
                        view.doRetrieveModel().getStockDetailModel().getStockId().toString());
                break;
            case TOGGLE_KEYBOARD:
                view.showState(OrderView.ViewState.TOGGLE_KEYBOARD);
                break;
            case STOCK_NOT_AVAILABLE:
                view.showState(OrderView.ViewState.STOCK_NOT_AVAILABLE);
                break;
            case SHOW_USER_INFO:
                PutUserResponseModel.PutUserResponseData user = view.doRetrieveModel().getUserModel();
                interactor.callAPIPutUser(
                        view.doRetrieveUserAuth(),
                        user.getUserAddress(),
                        user.getUserPhone(),
                        user.getUserEmail(),
                        user.getUserCity()
                );
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading(boolean state) {
        if (state) {
//            view.showDialogProgress();
        } else {
//            view.hideDialogProgress();
        }
    }

    @Override
    public void refreshDetailOrder(String orderId) {
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        presentState(OrderView.ViewState.IDLE);
        if (endPoint == APICallManager.APIRoute.CONFIRMORDER) {
            presentState(OrderView.ViewState.ORDER_PAID);
        } else if (endPoint == APICallManager.APIRoute.POSTORDER) {
            if (responseModel.getError()){
                if (responseModel.getMessage().equalsIgnoreCase("Stock Not Available")){
                    presentState(OrderView.ViewState.STOCK_NOT_AVAILABLE);
                }
            }else{
                PostOrderResponseModel.PostOrderResponseData data = ((PostOrderResponseModel) responseModel).getData().get(0);
                view.doRetrieveModel().setPostOrderModel(data);
                presentState(OrderView.ViewState.ORDER_PROCESSED);
            }
        } else if (endPoint == APICallManager.APIRoute.PUTUSER) {
            view.showState(OrderView.ViewState.SHOW_USER_INFO);
        } else if (endPoint == APICallManager.APIRoute.GETSTOCK) {
            view.doRetrieveModel().setStockDetailModel(((GetStockDetailResponseModel)responseModel).getData().get(0));
            view.showState(OrderView.ViewState.IDLE);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
        presentState(OrderView.ViewState.IDLE);
        if (endPoint == APICallManager.APIRoute.CONFIRMORDER) {
            presentState(OrderView.ViewState.ERROR_CONFIRM_ORDER);
        } else if (endPoint == APICallManager.APIRoute.POSTORDER) {

        } else if (endPoint == APICallManager.APIRoute.PUTUSER) {

        } else if (endPoint == APICallManager.APIRoute.GETSTOCK) {

        }
    }
}
