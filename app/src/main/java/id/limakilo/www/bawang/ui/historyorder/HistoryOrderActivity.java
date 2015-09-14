package id.limakilo.www.bawang.ui.historyorder;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by walesadanto on 23/8/15.
 */
public class HistoryOrderActivity extends AppCompatActivity {

    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(id.limakilo.www.bawang.R.layout.activity_history_order);

        setSupportActionBar((Toolbar) findViewById(id.limakilo.www.bawang.R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String itemTitle = "Riwayat Pemesanan";
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(id.limakilo.www.bawang.R.id.collapsing_toolbar);
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

}
