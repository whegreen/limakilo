package id.limakilo.www.bawang.ui.register_phone.mvp;

import android.content.Context;

/**
 * Created by NwP.
 */
public class RegisterPhoneModel {
//    public RegisterPhoneDomain registerPhoneDomain;
//    public UserDomain userDomain;

    String number;
    Context context;
    String requestId;
    String verificationCode;

    String msisdn;
    String token;

    String to;
    String amount;
    String adminFee;
    String message;

    String balance;

    public String getMsisdn() {
        return msisdn;
    }

    public String getToken() {
        return token;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTo() {
        return to;
    }

    public String getAmount() {
        return amount;
    }

    public String getAdminFee() {
        return adminFee;
    }

    public String getMessage() {
        return message;
    }

    public String getBalance() {
        return balance;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setAdminFee(String adminFee) {
        this.adminFee = adminFee;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    RegisterPhoneView.ScreenState screenState;

    public RegisterPhoneModel() {
//        this.registerPhoneDomain = new RegisterPhoneDomain();
//        this.userDomain = new UserDomain();
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public RegisterPhoneView.ScreenState getScreenState() {
        return screenState;
    }

    public void setScreenState(RegisterPhoneView.ScreenState screenState) {
        this.screenState = screenState;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}
