package id.limakilo.www.bawang.ui.order;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.limakilo.www.bawang.R;
import id.limakilo.www.bawang.util.api.stock.GetStockDetailResponseModel;

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

    private OrderFragment orderFragment;

    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.backdrop) ImageView backdrop;
//    @Bind(R.id.fab) FloatingActionButton fab;
    @Bind(R.id.loading_bar) View loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        stockModel = new GetStockDetailResponseModel().new GetStockDetailResponseData();

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            //
        } else {
            stockModel.setStockId(Integer.parseInt(extras.getString(STOCKID)));
            stockModel.setStockName(extras.getString(STOCKNAME));
            stockModel.setStockQuantity(Float.parseFloat(extras.getString(STOCKQUANTITY)));
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

//        String activityTitle = stockModel.getStockName()+" | "+ stockModel.getSellerName();
        String activityTitle = stockModel.getStockName();
        collapsingToolbarLayout.setTitle(activityTitle);

        Glide.with(this)
                .load(Integer.parseInt(stockModel.getSellerAvatarUrl()))
                .fitCenter()
                .into(backdrop);

    }

//    @OnClick(R.id.fab)
//    public void onFABClick(){
//        new MaterialDialog.Builder(getBaseContext())
//                .title("Cara Pemesanan Limakilo")
//                .content("FAQ-nya limakilo")
//                .positiveText("ok")
//                .negativeText("Tanya kami")
//                .callback(new MaterialDialog.ButtonCallback() {
//                    @Override
//                    public void onPositive(MaterialDialog dialog) {
//                        dialog.hide();
//                    }
//
//                    @Override
//                    public void onNegative(MaterialDialog dialog) {
//                        ConversationActivity.show(getApplicationContext());
//                    }
//                })
//                .build()
//                .show();
//    }

    @OnClick(R.id.backdrop)
    public void OnBackdropClick(){
        hideSoftKeyboard();
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
