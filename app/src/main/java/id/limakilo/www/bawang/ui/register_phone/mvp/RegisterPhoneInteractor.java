package id.limakilo.www.bawang.ui.register_phone.mvp;


/**
 * Created by NwP.
 */
public interface RegisterPhoneInteractor {
//    void callAPIGetSMSVerify(String number);

//    void callAPIGetLogin(String requestId);

//    RegisterModel getResponse();

//    void setResponse(RegisterModel registerModel);


    void callAPIGetLogin(String msisdn, String token);

    void callAPIGetInquiryTransfer(String amount, String to);

    void callAPIGetTransfer(String amount, String to, String from, String description);
}
