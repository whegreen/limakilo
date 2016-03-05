package id.limakilo.www.bawang.ui.register_phone;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import id.limakilo.www.bawang.R;


public class RegisterPhoneActivity extends AppCompatActivity {

    RegisterPhoneFragment registerPhoneFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_phone);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        setRegisterPhoneFragment((RegisterPhoneFragment) fragment);
    }

    public RegisterPhoneFragment getRegisterPhoneFragment() {
        return registerPhoneFragment;
    }

    public void setRegisterPhoneFragment(RegisterPhoneFragment registerPhoneFragment) {
        this.registerPhoneFragment = registerPhoneFragment;
    }
}
