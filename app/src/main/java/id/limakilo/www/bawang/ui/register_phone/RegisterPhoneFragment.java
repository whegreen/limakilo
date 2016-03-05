package id.limakilo.www.bawang.ui.register_phone;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.wang.avi.AVLoadingIndicatorView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.ui.register_phone.mvp.RegisterPhoneModel;
import id.limakilo.www.bawang.ui.register_phone.mvp.RegisterPhonePresenter;
import id.limakilo.www.bawang.ui.register_phone.mvp.RegisterPhonePresenterImpl;
import id.limakilo.www.bawang.ui.register_phone.mvp.RegisterPhoneView;
import id.limakilo.www.bawang.util.common.Constant;

public class RegisterPhoneFragment extends Fragment implements RegisterPhoneView {
    /*
     * views
     */

    @Bind(R.id.screen_login)
    View screenLogin;
    @Bind(R.id.screen_transfer)
    View screenTransfer;

    @Bind(R.id.textinput_biaya_admin)
    TextInputLayout textInputBiayaAdmin;
    @Bind(R.id.textinput_jumlah)
    TextInputLayout textInputJumlah;
    @Bind(R.id.textinput_nomor)
    TextInputLayout textInputNomor;
    @Bind(R.id.textinput_pesan)
    TextInputLayout textInputPesan;
    @Bind(R.id.textinput_phone)
    TextInputLayout textInputPhone;
    @Bind(R.id.textinput_pin)
    TextInputLayout textInputPin;

    @Bind(R.id.avloading_register_phone)
    AVLoadingIndicatorView loadingIndicatorView;

    /*
     * vars
     */
    RegisterPhoneModel model;
    RegisterPhonePresenter presenter;
    RegisterPhoneActivity mActivity;
    SMSResultReceiver mReceiver;

    @OnClick(R.id.button_login)
    public void onClickLogin() {
        doRetrieveModel().setMsisdn(textInputPhone.getEditText().getText().toString());
        doRetrieveModel().setToken(textInputPin.getEditText().getText().toString());
        presenter.presentState(ViewState.SHOW_LOGIN);
    }

    @OnClick(R.id.button_transfer)
    public void onClickTransfer(Button button) {
        if (doRetrieveModel().getScreenState().equals(ScreenState.SCREEN_INQUIRY)) {
            doRetrieveModel().setTo(textInputNomor.getEditText().getText().toString());
            doRetrieveModel().setAmount(textInputJumlah.getEditText().getText().toString());
            doRetrieveModel().setMessage(textInputPesan.getEditText().getText().toString());
            presenter.presentState(ViewState.SHOW_INQUIRY);
        } else {
            presenter.presentState(ViewState.SHOW_TRANSFER);
            button.setText("Transfer e-cash");
        }
    }

    public RegisterPhoneFragment() {
        // Required empty public constructor
    }

    public static RegisterPhoneFragment newInstance(String param1, String param2) {
        RegisterPhoneFragment fragment = new RegisterPhoneFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = new RegisterPhoneModel();
        this.presenter = new RegisterPhonePresenterImpl(this);
        this.mReceiver = new SMSResultReceiver();
        mActivity.registerReceiver(mReceiver, new IntentFilter("SMSReceiver"));

        doRetrieveModel().setScreenState(ScreenState.SCREEN_LOGIN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register_phone, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (RegisterPhoneActivity) activity;
    }

    @Override
    public RegisterPhoneModel doRetrieveModel() {
        return this.model;
    }

    @Override
    public void showState(ViewState state) {
        switch (state) {
            case LOADING:
                showProgress(true);
                break;
            case IDLE:
                showProgress(false);
                showScreenState(ScreenState.SCREEN_LOGIN);
                break;
            case SHOW_LOGIN:
                showScreenState(ScreenState.SCREEN_INQUIRY);
//                showProgress(false);
                break;
            case SHOW_INQUIRY:
                showScreenState(ScreenState.SCREEN_TRANSFER);
//                showProgress(false);
                break;
            case SHOW_TRANSFER:
                Toast.makeText(getActivity(), "transfer e-cash sukses", Toast.LENGTH_SHORT).show();
                getActivity().finish();
//                showScreenState(ScreenState.SCREEN_SUCCEED);
//                showProgress(false);
                break;
//            case SHOW_RECEIVE_SMS:
//                showReceiveSMS();
//                break;
//            case SHOW_VERIFY:
//                showVerify();
//                break;
//            case SHOW_SCREENSTATE:
//                showScreenState(doRetrieveModel().getScreenState());
//                break;
//            case OPEN_SIGNUP:
//                openSignUpActivity();
//                break;
            case ERROR:
                showError();
                break;
        }
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag)
            loadingIndicatorView.setVisibility(View.VISIBLE);
        else loadingIndicatorView.setVisibility(View.GONE);
    }

    @Override
    public void showToast(String text) {
        Toast.makeText(mActivity, "", Toast.LENGTH_SHORT).show();
    }

    private void showReceiveSMS() {
//        doRetrieveModel().setScreenState(ScreenState.SCREEN_INPUT_PIN);
//        presenter.presentState(ViewState.SHOW_SCREENSTATE);
    }

    private void showVerify() {
        presenter.presentState(ViewState.OPEN_SIGNUP);
    }

    private void openSignUpActivity() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        mActivity.startActivity(intent);
    }

    private void showScreenState(ScreenState state) {
        doRetrieveModel().setScreenState(state);
        switch (state) {
            case SCREEN_LOGIN:
                screenLogin.setVisibility(View.VISIBLE);
                screenTransfer.setVisibility(View.GONE);
                break;
            case SCREEN_INQUIRY:
                screenLogin.setVisibility(View.GONE);
                screenTransfer.setVisibility(View.VISIBLE);
                textInputBiayaAdmin.setVisibility(View.GONE);
                break;
            case SCREEN_TRANSFER:
                screenLogin.setVisibility(View.GONE);
                screenTransfer.setVisibility(View.VISIBLE);
                break;
            case SCREEN_SUCCEED:
                screenLogin.setVisibility(View.GONE);
                screenTransfer.setVisibility(View.GONE);
                break;
        }
    }

    private void showError() {
        new MaterialDialog.Builder(mActivity)
                .content(R.string.err_general)
                .positiveText(R.string.err_not_enough_friend_option)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(MaterialDialog materialDialog, DialogAction dialogAction) {
                        materialDialog.dismiss();
                    }
                })
                .contentColor(getResources().getColor(R.color.dialog_txt_color)) // notice no 'res' postfix for literal color
                .backgroundColorRes(R.color.dialog_BGColor)
                .show();
    }

    class SMSResultReceiver extends BroadcastReceiver {
//        @Override
//        protected void onReceiveResult(int resultCode, final Bundle resultData) {
//            if (resultCode == Constant.SUCCESS_RESULT) {
//                final String verificationCode = resultData.getString(Constant.TAG_BUNDLE_VERIFICATION_CODE);
//                mActivity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (!verificationCode.isEmpty() || verificationCode != null) {
//                            doRetrieveModel().setVerificationCode(verificationCode);
//                            textInputLayoutPin.getEditText().setText(doRetrieveModel().getVerificationCode());
//                        } else
//                            showToast("cek");
//                    }
//                });
//            } else {
//                mActivity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        showToast("cek 2");
//                    }
//                });
//            }
//        }

        @Override
        public void onReceive(Context context, Intent intent) {
            final String verificationCode = intent.getStringExtra(Constant.TAG_BUNDLE_VERIFICATION_CODE);
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (!verificationCode.isEmpty() || verificationCode != null) {
                        doRetrieveModel().setVerificationCode(verificationCode);
//                        textInputLayoutPin.getEditText().setText(doRetrieveModel().getVerificationCode());
                    } else
                        showToast("cek");
                }
            });
        }
    }
}
