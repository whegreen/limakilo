package id.limakilo.www.bawang.ui.historyorder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.limakilo.www.bawang.R;

/**
 * Created by walesadanto on 23/8/15.
 */
public class HistoryOrderActivity extends AppCompatActivity {

    @Nullable
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.loading_bar) View loadingBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(id.limakilo.www.bawang.R.layout.activity_history_order);
        ButterKnife.bind(this);

        setSupportActionBar((Toolbar) findViewById(id.limakilo.www.bawang.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = "Pesanan";
        collapsingToolbarLayout.setTitle(itemTitle);

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

    public void showLoadingBar(){
        loadingBar.setVisibility(View.VISIBLE);
    }
    public void hideLoadingBar(){
        loadingBar.setVisibility(View.GONE);
    }

}
