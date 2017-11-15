package ru.echodc.singlegroupe.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ru.echodc.singlegroupe.R;

public class ImageFragment extends BaseFragment {

    @BindView(R.id.webview)
    WebView webView;


    public static ImageFragment newInstance(String url) {

        Bundle args = new Bundle();
        args.putString("url", url);

        ImageFragment fragment = new ImageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getMainContentLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public int onCreateToolbarTitle() {
        return R.string.screen_name_image;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.setBackgroundColor(getResources().getColor(R.color.colorDefaultWhite));

        webView.loadUrl(getArguments().getString("url"));
    }


}