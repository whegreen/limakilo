package id.limakilo.www.bawang.ui.register_phone.mvp;

import android.content.Context;

import id.limakilo.www.bawang.util.api.APICallListener;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.ecash.GetInquiryTransferMember;
import id.limakilo.www.bawang.util.api.ecash.GetLogin;
import id.limakilo.www.bawang.util.api.ecash.GetTransferMember;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by NwP.
 */
public class RegisterPhoneInteractorImpl implements RegisterPhoneInteractor {
    APICallListener listener;
    Context context;
//    RegisterModel registerModel;

    public RegisterPhoneInteractorImpl(APICallListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public void callAPIGetLogin(String msisdn, String token) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETLOGIN;
        APICallManager.getInstance().getLogin(msisdn, token, new Callback<GetLogin>() {
            @Override
            public void success(GetLogin getLogin, Response response) {
                listener.onAPICallSucceed(route, getLogin);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route,error);
            }
        });
    }

    @Override
    public void callAPIGetInquiryTransfer(String amount, String to) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETINQUIRYTRANSFERMEMBER;
        APICallManager.getInstance().getInquiryTransfer(amount, to, new Callback<GetInquiryTransferMember>() {
            @Override
            public void success(GetInquiryTransferMember getInquiryTransferMember, Response response) {
                listener.onAPICallSucceed(route, getInquiryTransferMember);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

    @Override
    public void callAPIGetTransfer(String amount, String to, String from, String description) {
        final APICallManager.APIRoute route = APICallManager.APIRoute.GETTRANSFERMEMBER;
        APICallManager.getInstance().getTransfer(amount, to, from, description, new Callback<GetTransferMember>() {
            @Override
            public void success(GetTransferMember getTransferMember, Response response) {
                listener.onAPICallSucceed(route, getTransferMember);
            }

            @Override
            public void failure(RetrofitError error) {
                listener.onAPICallFailed(route, error);
            }
        });
    }

//    @Override
//    public void callAPIGetSMSVerify(String number) {
//        final APICallManager.APIRoute route = APICallManager.APIRoute.GET_VERIFY;
//        APICallManager.getInstance().getSMSVerify(number, new Callback<RegisterModel>() {
//            @Override
//            public void success(RegisterModel registerModel, Response response) {
//                setResponse(registerModel);
//                listener.onAPICallSucceed(route, null);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                listener.onAPICallFailed(route, error);
//            }
//        });
//    }
//
//    @Override
//    public void callAPIGetLogin(String requestId) {
//        final APICallManager.APIRoute route = APICallManager.APIRoute.LOGIN;
//        APICallManager.getInstance().loginFacebook(requestId, new Callback<UsersModel>() {
//            @Override
//            public void success(UsersModel usersModel, Response response) {
//                listener.onAPICallSucceed(route, usersModel);
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                listener.onAPICallFailed(route, error);
//            }
//        });
//    }

//    @Override
//    public RegisterModel getResponse() {
//        return this.registerModel;
//    }
//
//    @Override
//    public void setResponse(RegisterModel registerModel) {
//        this.registerModel = registerModel;
//    }
}
