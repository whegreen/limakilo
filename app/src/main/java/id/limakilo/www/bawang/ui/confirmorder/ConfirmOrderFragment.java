package id.limakilo.www.bawang.ui.confirmorder;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.afollestad.materialdialogs.MaterialDialog;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.confirmorder.mvp.ConfirmOrderView;

/**
 * Created by walesadanto on 30/8/15.
 */
public class ConfirmOrderFragment extends Fragment implements ConfirmOrderView {

    private MaterialDialog confirmDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_confirm_order, container, false);

        Button btnCaraPembayaran = (Button) view.findViewById(R.id.btn_cara_pembayaran);
        Button btnKonfirmasiPembayaran = (Button) view.findViewById(R.id.btn_konfirmasi_pembayaran);

        final View cardDetailOrder = view.findViewById(R.id.card_detail_order);
        final View cardCaraPembayaran = view.findViewById(R.id.card_cara_pembayaran);

        btnCaraPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cardDetailOrder.setVisibility(View.GONE);
                cardCaraPembayaran.setVisibility(View.VISIBLE);
            }
        });

        btnKonfirmasiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog.show();
                cardDetailOrder.setVisibility(View.VISIBLE);
                cardCaraPembayaran.setVisibility(View.GONE);
            }
        });

        confirmDialog = new MaterialDialog.Builder(getActivity())
                .title("Konfirmasi Pembayaran")
                .content("Pastikan Rekening dan jumlah transfer yang anda masukkan sudah benar. Proses pembelian bawang anda kami proses maksimal dalam waktu 24 jam. \nMari bantu petani bawang indonesia. :)")
                .positiveText("Lanjut")
                .negativeText("Batal")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        ((ConfirmOrderActivity)getActivity()).confirmOrder("80000", "walesa danto");
                        confirmDialog.hide();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();

        return view;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (Activity.RESULT_OK != resultCode) {
            getActivity().finish();
            return;
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_leave) {
//            leave();
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
