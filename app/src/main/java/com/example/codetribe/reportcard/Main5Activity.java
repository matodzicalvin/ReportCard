package com.example.codetribe.reportcard;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main5Activity extends Activity {

    EditText etSecretCode;
    Button btnView;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        etSecretCode = (EditText)findViewById(R.id.etSecretCode);


        btnBack = (Button)findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Main4Activity.class);
                startActivityForResult(myIntent, 0);
            }
        } );

        btnView = (Button)findViewById(R.id.btnView);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;
                name=etSecretCode.getText().toString().toUpperCase();
                if(name.equals("CODETRIBE")) {
                    Toast.makeText(getApplicationContext(), "Getting Admin Credentials...",Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(view.getContext(), ViewPassWord.class);
                    startActivityForResult(myIntent, 0);

                }

                else{
                    Toast.makeText(getApplicationContext(), "You Entered Wrong Secret Code",Toast.LENGTH_SHORT).show();
                }

            }});}}


