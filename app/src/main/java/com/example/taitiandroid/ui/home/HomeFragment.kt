package com.example.taitiandroid.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.taitiandroid.R

class HomeFragment : Fragment() {

    private val URL = "https://taiti.es"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val  mWebView = view.findViewById(R.id.webView) as WebView
        mWebView.loadUrl(URL)

        val webSettings = mWebView.getSettings()
        webSettings.setJavaScriptEnabled(true)


        mWebView.setWebViewClient(WebViewClient())


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}