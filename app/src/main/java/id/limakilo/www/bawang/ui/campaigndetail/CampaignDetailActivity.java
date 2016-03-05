package id.limakilo.www.bawang.ui.campaigndetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.afollestad.materialdialogs.MaterialDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;

/**
 * Created by walesadanto on 23/8/15.
 */
public class CampaignDetailActivity extends AppCompatActivity {

    @Nullable
//    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.toolbar) Toolbar toolbar;
//    @Bind(R.id.fab) FloatingActionButton fab;

    public static final String CAMPAIGN_ID = "campaign_id";

    private String campaignId;

    private boolean wrapInScrollView = true;
    private MaterialDialog faq;

//    @OnClick(R.id.fab)
//    public void OnClickFab(){
//        final View loadingBar = faq.getCustomView().findViewById(R.id.loading_bar);
//        final WebView webView = ((WebView)faq.getCustomView().findViewById(R.id.webview));
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.loadUrl("http://limakilo.id/faq");
//        webView.setWebViewClient(new WebViewClient() {
//
//            public void onPageFinished(WebView view, String url) {
//                // do your stuff here
//                webView.setVisibility(View.VISIBLE);
//                loadingBar.setVisibility(View.GONE);
//            }
//        });
//        faq.show();
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            //
        } else {
            setCampaignId(extras.getString(CAMPAIGN_ID));
        }

        setContentView(R.layout.activity_campaign_detail);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = "Campaign Detail";
//        collapsingToolbarLayout.setTitle(itemTitle);

        toolbar.setTitle(itemTitle);

        faq = new MaterialDialog.Builder(this)
                .customView(R.layout.fragment_webview, wrapInScrollView)
                .positiveText("Tutup")
                .callback(new MaterialDialog.ButtonCallback() {
                    @Override
                    public void onPositive(MaterialDialog dialog) {
                        super.onPositive(dialog);
                        dialog.hide();
                    }
                })
                .build();
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignId() {
        return campaignId;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
