package com.arturo.dtp;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class RankingEmpresas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking_empresas);

        WebView webview = (WebView) findViewById(R.id.webViewRankingEmpresas);
        setContentView(webview);

        webview.loadUrl("peritajeag.com");
    }
}
