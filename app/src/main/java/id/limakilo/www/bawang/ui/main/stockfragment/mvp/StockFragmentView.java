package id.limakilo.www.bawang.ui.main.stockfragment.mvp;

import id.limakilo.www.bawang.util.api.RootResponseModel;

/**
 * Created by walesadanto on 22/8/15.
 */
public interface StockFragmentView{

    public enum ViewState {
        IDLE, LOAD_DATA, SUCCESS, FAILURE, CANCEL, LOADING, ERROR_API_CALL, BLANK_STATE, ERROR
    }

    public void doSetAuthentification();
    void doUpdateModel(RootResponseModel responseModel);

    public void doRetrieveModel();


    public void showState(ViewState state);

}
