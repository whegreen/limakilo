package com.limakilogram.www.bawang.ui.main.limakilofragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.limakilogram.www.bawang.R;
import com.limakilogram.www.bawang.ui.main.limakilofragment.mvp.LimakiloFragmentPresenter;
import com.limakilogram.www.bawang.ui.main.limakilofragment.mvp.LimakiloFragmentView;
import com.limakilogram.www.bawang.util.api.APICallListener;
import com.limakilogram.www.bawang.util.api.APICallManager;
import com.limakilogram.www.bawang.util.common.PreferencesManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by walesadanto on 22/8/15.
 */
public class LimakiloFragment extends Fragment implements APICallListener, LimakiloFragmentView {


//    List<GetRecentChatResponseModel.GetRecentResponseData> chatList =
//            new ArrayList<GetRecentChatResponseModel.GetRecentResponseData>();
    private LimakiloFragmentPresenter presenter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_limakilo_list, container, false);

        setupChatRecyclerView();
        retrieveChatList();

        return recyclerView;
    }

    public void setupChatRecyclerView(){
//        recyclerView.setLayoutManager(new LinearLayoutManager((recyclerView.getContext())));
//        recyclerView.setAdapter(new ChatRecyclerViewAdapter(getActivity(), chatList));
    }

    public void retrieveChatList(){
        APICallManager.getInstance().setAuthentification(PreferencesManager.getAuthToken(getActivity()));

//        APICallManager.getInstance().postChat("1", "{20000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("2", "{50000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("3", "{56000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("9", "{-200000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("11", "{220000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("14", "{-120000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("13", "{320000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("7", "{90000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });
//
//        APICallManager.getInstance().postChat("10", "{-60000}", new Callback<PostChatResponseModel>() {
//            @Override
//            public void success(PostChatResponseModel postChatResponseModel, Response response) {
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//
//            }
//        });

//        APICallManager.getInstance().getRecentChatList(new Callback<GetRecentChatResponseModel>() {
//            @Override
//            public void success(GetRecentChatResponseModel getRecentChatResponseModel, Response response) {
//
//                chatList = getRecentChatResponseModel.getData();
//
//                for (Iterator<GetRecentChatResponseModel.GetRecentResponseData> i = chatList.iterator(); i.hasNext();){
//                    if (i.next().getAvaUrl() == null) i.remove();
//                }
//
//                Collections.sort(chatList, new Comparator<GetRecentChatResponseModel.GetRecentResponseData>() {
//                    @Override
//                    public int compare(GetRecentChatResponseModel.GetRecentResponseData lhs, GetRecentChatResponseModel.GetRecentResponseData rhs) {
//                        return lhs.getUserFirstname().compareTo(rhs.getUserFirstname());
//                    }
//                });
//
//                setupChatRecyclerView();
//
//            }
//
//            @Override
//            public void failure(RetrofitError error) {
//                Toast.makeText(getActivity(), "failed to get data", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public void onAPICallSucceed() {

    }

    @Override
    public void onAPICallFailed() {

    }
}
