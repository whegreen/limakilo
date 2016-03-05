package id.limakilo.www.bawang.ui.register_phone.mvp;

/**
 * Created by NwP.
 */
public interface RegisterPhoneView {
    enum ViewState {
        LOADING, IDLE,

        SHOW_LOGIN, SHOW_INQUIRY, SHOW_TRANSFER,

        LOAD_SEND_SMS, SHOW_RECEIVE_SMS,
        LOAD_VERIFY_PIN, SHOW_VERIFY,
        SHOW_SCREENSTATE, OPEN_SIGNUP, ERROR
    }

    enum ScreenState {
        SCREEN_LOGIN, SCREEN_INQUIRY, SCREEN_SUCCEED, SCREEN_TRANSFER
    }

    RegisterPhoneModel doRetrieveModel();

    void showState(ViewState state);

    void showProgress(boolean flag);

    void showToast(String text);
}
