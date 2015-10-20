package id.limakilo.www.bawang.ui.historyorder.mvp;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderDetailResponseModel;
import id.limakilo.www.bawang.util.api.order.GetOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import retrofit.RetrofitError;

/**
 * Created by walesadanto on 1/9/15.
 */
public class HistoryOrderPresenterImpl implements HistoryOrderPresenter, APICallListener{


    private HistoryOrderView view;
    private HistoryOrderInteractorImpl interactor;

    public HistoryOrderPresenterImpl(HistoryOrderView view){
        this.view = view;
        this.interactor = new HistoryOrderInteractorImpl(this);
    }

    @Override
    public void presentState(HistoryOrderView.ViewState state) {
        switch (state){
            case LOADING:
                view.showState(HistoryOrderView.ViewState.LOADING);
                break;
            case IDLE:
                view.showState(HistoryOrderView.ViewState.IDLE);
                break;
            case CONFIRM_ORDER:
                interactor.callAPIConfirmOrder(
                        view.doRetrieveAuthentification(),
                        view.doRetrieveModel().getOrderDetailModel().getOrderId().toString(),
                        view.doRetrieveModel().getOrderDetailModel().getOrderAmount(),
                        view.doRetrieveFullname()
                );
                break;
            case LOAD_ORDERS:
                interactor.callAPIGetOrders(view.doRetrieveAuthentification());
                view.showState(HistoryOrderView.ViewState.LOADING);
                break;
            case LOAD_STOCK:
                interactor.callAPIStockDetail(
                        view.doRetrieveAuthentification(),
                        view.doRetrieveModel().getOrderDetailModel().getStockId().toString()
                );
                break;
            case SHOW_ORDERS:
                view.showState(HistoryOrderView.ViewState.SHOW_ORDERS);
                break;
            case SHOW_DETAIL_ORDER:
                view.showState(HistoryOrderView.ViewState.SHOW_DETAIL_ORDER);
                break;
            case SHOW_CONFIRM_DIALOG:
                view.showState(HistoryOrderView.ViewState.SHOW_CONFIRM_DIALOG);
                break;
            case SHOW_FINISH_DIALOG:
                view.showState(HistoryOrderView.ViewState.SHOW_FINISH_DIALOG);
                break;
            case SHOW_DISCARD_DIALOG:
                view.showState(HistoryOrderView.ViewState.SHOW_DISCARD_DIALOG);
                break;
            case SHOW_SHIPMENT_DIALOG:
                view.showState(HistoryOrderView.ViewState.SHOW_SHIPMENT_DIALOG);
                break;
            case API_ERROR:
                view.showState(HistoryOrderView.ViewState.API_ERROR);
                break;
            case BLANK_STATE:
                view.showState(HistoryOrderView.ViewState.BLANK_STATE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute endPoint, RootResponseModel responseModel) {
        presentState(HistoryOrderView.ViewState.IDLE);
        if (endPoint == APICallManager.APIRoute.GETORDERS){
            try{
                List<GetOrderResponseModel.GetOrderResponseData> temp = new ArrayList<GetOrderResponseModel.GetOrderResponseData>();
                for (GetOrderResponseModel.GetOrderResponseData data : ((GetOrderResponseModel)responseModel).getData()) {
                    if (data.getSellerId() != null) {
                        temp.add(data);
                    }
                }
                view.doRetrieveModel().setOrderList(temp);

                if (temp.isEmpty()){
                    presentState(HistoryOrderView.ViewState.BLANK_STATE);
                }
                else{
                    Collections.sort(view.doRetrieveModel().getOrderList(), new Comparator<GetOrderResponseModel.GetOrderResponseData>() {
                        @Override
                        public int compare(GetOrderResponseModel.GetOrderResponseData lhs, GetOrderResponseModel.GetOrderResponseData rhs) {
                            return rhs.getOrderDate().compareTo(lhs.getOrderDate());
                        }
                    });
                    presentState(HistoryOrderView.ViewState.SHOW_ORDERS);
                }
            } catch (Exception e){
            }
        } if (endPoint == APICallManager.APIRoute.GETORDER){
            view.doRetrieveModel().setOrderDetailModel(((GetOrderDetailResponseModel) responseModel).getData().get(0));
            presentState(HistoryOrderView.ViewState.SHOW_DETAIL_ORDER);
        }else if (endPoint == APICallManager.APIRoute.GETSTOCK){
            view.doRetrieveModel().setStockDetailModel(((GetStockDetailResponseModel) responseModel).getData().get(0));
            presentState(HistoryOrderView.ViewState.SHOW_CONFIRM_DIALOG);
        }else if (endPoint == APICallManager.APIRoute.CONFIRMORDER){
            presentState(HistoryOrderView.ViewState.SHOW_FINISH_DIALOG);
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute endPoint, RetrofitError retrofitError) {
        presentState(HistoryOrderView.ViewState.IDLE);
        if (endPoint == APICallManager.APIRoute.GETORDERS){
            presentState(HistoryOrderView.ViewState.API_ERROR);
        } else if (endPoint == APICallManager.APIRoute.GETORDER){
            presentState(HistoryOrderView.ViewState.API_ERROR);
        }else if (endPoint == APICallManager.APIRoute.GETSTOCK){
            presentState(HistoryOrderView.ViewState.API_ERROR);
        }else if (endPoint == APICallManager.APIRoute.CONFIRMORDER){
            presentState(HistoryOrderView.ViewState.API_ERROR);
        }
    }

    @Override
    public void doUpdateTextColor(TextView textView, int color){
        textView.setTextColor(view.doRetrieveColor(color));
    }

}
