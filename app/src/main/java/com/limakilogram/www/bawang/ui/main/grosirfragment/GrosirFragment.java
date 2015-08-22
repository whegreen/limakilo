package com.limakilogram.www.bawang.ui.main.grosirfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.main.grosirfragment.mvp.GrosirFragmentPresenter;
import com.limakilogram.www.bawang.ui.main.grosirfragment.mvp.GrosirFragmentView;
import com.limakilogram.www.bawang.ui.main.limakilofragment.StockRecyclerViewAdapter;
import com.limakilogram.www.bawang.ui.main.limakilofragment.mvp.LimakiloFragmentPresenter;
import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.api.stock.GetStockResponseModel;
import com.limakilogram.www.bawang.util.common.PreferencesManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by walesadanto on 22/8/15.
 */
public class GrosirFragment extends Fragment implements APICallListener, GrosirFragmentView {

    private GrosirFragmentPresenter presenter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_limakilo_list, container, false);
        return recyclerView;
    }

    @Override
    public void onAPICallSucceed() {

    }

    @Override
    public void onAPICallFailed() {

    }
}
