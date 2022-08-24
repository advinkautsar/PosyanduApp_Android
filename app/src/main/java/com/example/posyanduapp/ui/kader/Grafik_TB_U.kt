package com.example.posyanduapp.ui.kader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.posyanduapp.R
import com.example.posyanduapp.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_grafik_bb_tb.*

class Grafik_TB_U : AppCompatActivity() {
    var idnya: String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grafik_tb_u)

        idnya = intent.getStringExtra("nik").toString()
        webView.webViewClient = WebViewClient()

        // this will load the url of the website
        webView.loadUrl(ApiService.BASE_URL_WEB+"grafikall/standart_tb_u/"+idnya)

        // this will enable the javascript settings
        webView.settings.javaScriptEnabled = true

        // if you want to enable zoom feature
        webView.settings.setSupportZoom(true)
    }
    // if you press Back button this code will work
    override fun onBackPressed() {
        // if your webview can go back it will go back
        if (webView.canGoBack())
            webView.goBack()
        // if your webview cannot go back
        // it will exit the application
        else
            super.onBackPressed()






    }
}