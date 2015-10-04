package id.limakilo.www.bawang.ui.order;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.ui.main.MainActivity;
import id.limakilo.www.bawang.util.api.APICallManager;
import id.limakilo.www.bawang.util.api.order.PostOrderResponseModel;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;
import id.limakilo.www.bawang.util.api.user.PutUserResponseModel;
import id.limakilo.www.bawang.util.common.PreferencesManager;
import io.supportkit.ui.ConversationActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 30/8/15.
 */
public class OrderActivity extends AppCompatActivity {

    public static final String STOCKID = "stock_id";
    public static final String STOCKNAME = "stock_name";
    public static final String STOCKQUANTITY = "stock_quantity";
    public static final String STOCKPRICE = "stock_price";
    public static final String SELLERID = "seller_id";
    public static final String SELLERNAME = "seller_name";
    public static final String SELLERADDRESS = "seller_address";
    public static final String SELLERAVAURL = "seller_avatar_url";
    public static final String SELLERREPUTATION = "seller_reputation";
    public static final String SELLERCITY = "seller_city";

    private GetStockDetailResponseModel.GetStockDetailResponseData stockModel;
    private PostOrderResponseModel.PostOrderResponseData orderModel;
    private PutUserResponseModel.PutUserResponseData userModel;

    private OrderFragment orderFragment;

    public MaterialDialog confirmDialog;
    private int orderQuantity = 0;

    private MaterialDialog dialogProgress;
    private MaterialDialog.Builder dialogFinishOrder;

    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.backdrop) ImageView backdrop;
    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.loading_bar) View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stockModel = new GetStockDetailResponseModel().new GetStockDetailResponseData();
        orderModel = new PostOrderResponseModel().new PostOrderResponseData();
        userModel = new PutUserResponseModel().new PutUserResponseData();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            //
        } else {
            stockModel.setStockId(Integer.parseInt(extras.getString(STOCKID)));
            stockModel.setStockName(extras.getString(STOCKNAME));
            stockModel.setStockQuantity(Integer.parseInt(extras.getString(STOCKQUANTITY)));
            stockModel.setStockPrice(extras.getString(STOCKPRICE));
            stockModel.setSellerId(Integer.parseInt(extras.getString(SELLERID)));
            stockModel.setSellerName(extras.getString(SELLERNAME));
            stockModel.setSellerAvatarUrl(extras.getString(SELLERAVAURL));
            stockModel.setSellerAddress(extras.getString(SELLERADDRESS));
            stockModel.setSellerReputation(extras.getString(SELLERREPUTATION));
            stockModel.setSellerCity(extras.getString(SELLERCITY));
        }

        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String activityTitle = stockModel.getStockName()+" | "+ stockModel.getSellerName();
        collapsingToolbarLayout.setTitle(activityTitle);

        boolean wrapInScrollView = true;
        confirmDialog = new MaterialDialog.Builder(this)
                .title("Konfirmasi Pesanan")
                .customView(R.layout.dialog_confirm_order_custom_view, wrapInScrollView)
                .positiveText("Lanjut")
                .negativeText("Batal")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        setOrderQuantity(1);
                        confirmDialog.hide();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        dialog.hide();
                    }
                })
                .build();

        dialogFinishOrder = new MaterialDialog.Builder(this)
                .content("Terima kasih sudah berbelanja, mari ramaikan gerakan #yukbelanjakepetani bersama #limakilo :)")
                .positiveText("ok")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        showLoadingView();

                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            public void run() {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                                getApplicationContext().startActivity(intent);
                                hideLoadingView();
                                handler.removeCallbacks(this);
                                OrderActivity.this.finish();
                            }
                        }, 300L);
                    }
                });

        Glide.with(this)
                .load(Integer.parseInt(stockModel.getSellerAvatarUrl()))
                .fitCenter()
                .into(backdrop);

        retrieveUserInfo();
        retrieveStockDetail();

    }

    @OnClick(R.id.fab)
    public void onFABClick(){
        new MaterialDialog.Builder(getBaseContext())
                .title("Cara Pemesanan Limakilo")
                .content("FAQ-nya limakilo")
                .positiveText("ok")
                .negativeText("Tanya kami")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        dialog.hide();
                    }

                    @Override
                    public void onNegative(MaterialDialog dialog) {
                        ConversationActivity.show(getApplicationContext());
                    }
                })
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_limakilo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.about_us) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public GetStockDetailResponseModel.GetStockDetailResponseData getStockModel() {
        return stockModel;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public void showOrderDetail() {
        View customView = confirmDialog.getCustomView();
        ((TextView)customView.findViewById(R.id.dialog_id_pesanan)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_harga_pesanan)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_jumlah_pesanan)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_kode_transfer)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_nama_penerima)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_ongkos_kirim)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_status_pesanan)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_tanggal_pesanan)).setText("");
        ((TextView)customView.findViewById(R.id.dialog_total)).setText("");

        confirmDialog.show();
    }

    public void showDialogProgress() {
        dialogProgress = new MaterialDialog.Builder(this)
                .title("memproses pemesanan")
                .content("mohon tunggu ...")
                .progress(true, 0)
                .show();
    }

    public void hideSoftKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showSoftKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        }
    }

    public PostOrderResponseModel.PostOrderResponseData getOrderModel() {
        return orderModel;
    }

    public void setOrderModel(PostOrderResponseModel.PostOrderResponseData orderModel) {
        this.orderModel = orderModel;
    }

    public PutUserResponseModel.PutUserResponseData getUserModel() {
        return userModel;
    }

    public void setUserModel(PutUserResponseModel.PutUserResponseData userModel) {
        this.userModel = userModel;
    }

    public void retrieveUserInfo(){
        PreferencesManager.getAsString(this, PreferencesManager.NAME);
        PreferencesManager.getAsString(this, PreferencesManager.HANDPHONE);
        PreferencesManager.getAsString(this, PreferencesManager.ADDRESS);
        PreferencesManager.getAsString(this, PreferencesManager.CITY);
    }

    public void retrieveStockDetail(){


        APICallManager.getInstance(PreferencesManager.getAuthToken(this)).getStocks(stockModel.getStockId().toString(),
                new Callback<GetStockDetailResponseModel>() {
                    @Override
                    public void success(GetStockDetailResponseModel getStockDetailResponseModel, Response response) {
                        stockModel = getStockDetailResponseModel.getData().get(0);
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Crashlytics.logException(error);
                    }
                });
    }

    public void saveUserInfo(String name, String phone, String address, String city){
        PreferencesManager.saveAsString(this, PreferencesManager.NAME, name);
        PreferencesManager.saveAsString(this, PreferencesManager.HANDPHONE, phone);
        PreferencesManager.saveAsString(this, PreferencesManager.ADDRESS, address);
        PreferencesManager.saveAsString(this, PreferencesManager.CITY, city);
    }

    public void showDialogFinishOrder(){
        dialogFinishOrder.show();
    }

    public OrderFragment getOrderFragment() {
        return orderFragment;
    }

    public void setOrderFragment(OrderFragment orderFragment) {
        this.orderFragment = orderFragment;
    }

    public void showLoadingView(){
        loadingView.setVisibility(View.VISIBLE);
    }

    public void hideLoadingView(){
        loadingView.setVisibility(View.GONE);
    }
}
