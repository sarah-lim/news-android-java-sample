package com.slim.newsdaily.ui;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

/**
 * Created by Sarah Lim on 09/09/2021.
 */
public class BindingAdapters {

    @BindingAdapter("articleImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext()).load(imageUrl).into(view);
    }

    @BindingAdapter("webUrl")
    public static void configureWebView(WebView webView, String url) {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }
        });

        webView.loadUrl(url);

    }
}
