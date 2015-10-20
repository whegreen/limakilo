package id.limakilo.www.bawang.ui.historyorder.mvp;

import android.widget.TextView;

/**
 * Created by walesadanto on 1/9/15.
 */
public interface HistoryOrderPresenter {
    public void presentState(HistoryOrderView.ViewState state);

    void doUpdateTextColor(TextView textView, int color);
}
