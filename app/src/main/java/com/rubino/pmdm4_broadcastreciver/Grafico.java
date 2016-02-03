package com.rubino.pmdm4_broadcastreciver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Grafico extends AppCompatActivity {

    private WebView webView;
    private int[] llamadasN = new int[]{9, 14, 20, 13, 17};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafico);
        setTitle("Estadisticas de llamadas");
        webView = (WebView) findViewById(R.id.webView);

        final Intent i = getIntent();
        llamadasN = i.getExtras().getIntArray("Cursor");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("file:///android_asset/canvas/pruebagraficos.html");

        webView.addJavascriptInterface(this, "InterfazAndroid");
    }


    @JavascriptInterface
    public int enviarDia(int x) {
        return llamadasN[x];
    }
}
