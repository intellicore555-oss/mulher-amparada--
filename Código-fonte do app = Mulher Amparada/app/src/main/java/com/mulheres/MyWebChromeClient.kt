package com.mulheres

import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView

class MyWebChromeClient : WebChromeClient() {

    override fun onJsConfirm(
        view: WebView?,
        url: String?,
        message: String?,
        result: JsResult?
    ): Boolean {

        result?.confirm()

        return true
    }
}