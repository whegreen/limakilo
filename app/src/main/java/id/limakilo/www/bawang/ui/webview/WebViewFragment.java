package id.limakilo.www.bawang.ui.webview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import id.limakilo.www.bawang.R;

/**
 * Created by walesadanto on 19/9/15.
 */
public class WebViewFragment extends Fragment {

    View view;
    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_webview, container, false);

        webView = (WebView) view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://blog.limakilo.id/post/128903695111/sekilas-tentang-limakilo");

        return view;
    }


}
