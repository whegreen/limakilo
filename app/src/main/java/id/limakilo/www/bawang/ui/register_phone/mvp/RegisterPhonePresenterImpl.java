package id.limakilo.www.bawang.ui.register_phone.mvp;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.RootResponseModel;
import id.limakilo.www.bawang.util.api.ecash.GetInquiryTransferMember;
import id.limakilo.www.bawang.util.api.ecash.GetLogin;
import retrofit.RetrofitError;

/**
 * Created by NwP.
 */
public class RegisterPhonePresenterImpl implements RegisterPhonePresenter, APICallListener {
    RegisterPhoneView view;
    RegisterPhoneInteractor interactor;

    public RegisterPhonePresenterImpl(RegisterPhoneView view) {
        this.view = view;
        this.interactor = new RegisterPhoneInteractorImpl(this,
                view.doRetrieveModel().getContext());
    }

    @Override
    public void presentState(RegisterPhoneView.ViewState state) {
        switch (state) {
            case LOADING:
                view.showState(RegisterPhoneView.ViewState.LOADING);
                break;
            case IDLE:
                view.showState(RegisterPhoneView.ViewState.IDLE);
                break;

            //panggil interactor di 3 state bawah
            case SHOW_LOGIN:
                view.showState(RegisterPhoneView.ViewState.LOADING);
                interactor.callAPIGetLogin(view.doRetrieveModel().getMsisdn(), view.doRetrieveModel().getToken());
//                view.showState(RegisterPhoneView.ViewState.LOGIN);
                break;
            case SHOW_INQUIRY:
                view.showState(RegisterPhoneView.ViewState.LOADING);
                interactor.callAPIGetInquiryTransfer(view.doRetrieveModel().getAmount(), view.doRetrieveModel().getTo());
//                view.showState(RegisterPhoneView.ViewState.INQUIRY);
                break;
            case SHOW_TRANSFER:
                view.showState(RegisterPhoneView.ViewState.LOADING);
                interactor.callAPIGetTransfer(view.doRetrieveModel().amount,
                        view.doRetrieveModel().to,
                        view.doRetrieveModel().msisdn,
                        view.doRetrieveModel().message
                );
//                view.showState(RegisterPhoneView.ViewState.TRANSFER);
                break;


//            case LOAD_SEND_SMS:
//                presentState(RegisterPhoneView.ViewState.LOADING);
////                interactor.callAPIGetSMSVerify(view.doRetrieveModel().getNumber());
//                break;
//            case SHOW_RECEIVE_SMS:
//                view.showState(RegisterPhoneView.ViewState.SHOW_RECEIVE_SMS);
//                break;
//            case LOAD_VERIFY_PIN:
//                presentState(RegisterPhoneView.ViewState.LOADING);
////                interactor.callAPIGetLogin(view.doRetrieveModel().getRequestId());
//                break;
//            case SHOW_VERIFY:
//                view.showState(RegisterPhoneView.ViewState.SHOW_VERIFY);
//                break;
//            case SHOW_SCREENSTATE:
//                view.showState(RegisterPhoneView.ViewState.SHOW_SCREENSTATE);
//                break;
//            case OPEN_SIGNUP:
//                view.showState(RegisterPhoneView.ViewState.OPEN_SIGNUP);
//                break;
            case ERROR:
                view.showState(RegisterPhoneView.ViewState.ERROR);
                break;
        }
    }

    @Override
    public void onAPICallSucceed(APICallManager.APIRoute route, RootResponseModel responseModel) {
        presentState(RegisterPhoneView.ViewState.IDLE);
        switch (route) {
            case GETLOGIN:
                view.doRetrieveModel().setToken(((GetLogin)responseModel).getToken());
                APICallManager.setToken(((GetLogin)responseModel).getToken());
                view.showState(RegisterPhoneView.ViewState.SHOW_LOGIN);
                break;
            case GETINQUIRYTRANSFERMEMBER:
                view.doRetrieveModel().setAdminFee(((GetInquiryTransferMember)responseModel).getTotalFee());
                view.showState(RegisterPhoneView.ViewState.SHOW_INQUIRY);
                break;
            case GETTRANSFERMEMBER:
                view.showState(RegisterPhoneView.ViewState.SHOW_TRANSFER);
                break;
            default:
                break;
        }
    }

    @Override
    public void onAPICallFailed(APICallManager.APIRoute route, RetrofitError retrofitError) {
        presentState(RegisterPhoneView.ViewState.IDLE);
        switch (route) {
            case GETLOGIN:
                view.showState(RegisterPhoneView.ViewState.ERROR);
            break;
            case GETINQUIRYTRANSFERMEMBER:
                view.showState(RegisterPhoneView.ViewState.ERROR);
                break;
            case GETTRANSFERMEMBER:
                view.showState(RegisterPhoneView.ViewState.ERROR);
                break;
            default:
                break;
        }
    }

}
