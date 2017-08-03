package com.example.codetribe.reportcard;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;


public class Spinner extends ActionBarActivity implements View.OnClickListener {

    private WebView browser;
    Button Create;
    Button Edit;
    Button View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);





//        browser = (WebView)findViewById(R.id.webView1);
//        browser.getSettings().setJavaScriptEnabled(true);
//        browser.loadUrl("file:///android_asset/Report/index.html");



        Create= (Button) findViewById(R.id.button7);
        Edit= (Button) findViewById(R.id.button8);
        View= (Button) findViewById(R.id.button9);

        Create.setOnClickListener(this);
        Edit.setOnClickListener(this);
        View.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v==Create){
            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else if(v==Edit){
            Intent i = new Intent(getApplicationContext(),Main4Activity.class);
            startActivity(i);
        }
        else if(v==View) {
            Intent i = new Intent(getApplicationContext(),Main3Activity.class);
            startActivity(i);
        }
        else{

        }
    }
}