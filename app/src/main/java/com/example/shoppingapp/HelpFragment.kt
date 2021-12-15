package com.example.shoppingapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
class HelpFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val helpView: View = inflater.inflate(R.layout.fragment_help, container, false)
        val mWebView = helpView.findViewById<View>(R.id.webview) as WebView
        mWebView.loadUrl("https://google.com")
        val webSettings: WebSettings = mWebView.settings
        webSettings.javaScriptEnabled = true
        mWebView.webViewClient = WebViewClient()
        return helpView
    }
}