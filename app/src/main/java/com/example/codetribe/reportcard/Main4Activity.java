package com.example.codetribe.reportcard;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends Activity {

    EditText etUsername;
    EditText etPassword;
    Button btnLogin;
    Button btnForgotPassword;
    Button btnUserSite;

    TextView tx1,tx2;
    int counter = 3;
    private ProgressDialog pDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);

        tx1=(TextView)findViewById(R.id.textView3);
        tx1.setVisibility(View.GONE);

        tx2=(TextView)findViewById(R.id.textView2);
        tx2.setVisibility(View.GONE);


       btnForgotPassword = (Button)findViewById(R.id.btnForgotPassword);

       btnForgotPassword.setOnClickListener(new View.OnClickListener() {
           public void onClick(View view) {
               Toast.makeText(getApplicationContext(),"Still On Construction Relax.",Toast.LENGTH_LONG).show();
               Intent myIntent = new Intent(view.getContext(), Main5Activity.class);
               startActivityForResult(myIntent, 0);
           }
        } );

        btnUserSite = (Button)findViewById(R.id.btnUserSite);

        btnUserSite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                pDialog = new ProgressDialog(Main4Activity.this);
                pDialog.setTitle("Loading menu...");
                pDialog.setMessage("Please Wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
                pDialog.setMax(10000);
                Intent myIntent = new Intent(view.getContext(), Spinner.class);
                startActivityForResult(myIntent, 0);
            }
        } );


        btnLogin = (Button)findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name;

               name = etUsername.getText().toString().toUpperCase();
                if(name.equals("MATODZI") && etPassword.getText().toString().equals("1995")) {

                    pDialog = new ProgressDialog(Main4Activity.this);
                    pDialog.setTitle("Opening Edit Menu...");
                    pDialog.setMessage("Please Wait...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                    pDialog.setMax(10000);

                    Intent myIntent = new Intent(view.getContext(), Main2Activity.class);
                    startActivityForResult(myIntent, 0);

                }

                else if (etUsername.length() == 0) {
                    etUsername.setError("This field can't be empty");
                    etUsername.requestFocus();
                    return;
                }
                else if (etPassword.length() == 0) {
                    etPassword.setError("This field can't be empty");
                    etPassword.requestFocus();
                    return;
                }
                else{
                    Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                   // v.vibrate(1000);
                    Toast.makeText(getApplicationContext(), "You Entered Wrong Credentials",Toast.LENGTH_SHORT).show();

                    tx2.setVisibility(View.VISIBLE);
                    tx2.setBackgroundColor(Color.WHITE);
                    tx1.setVisibility(View.VISIBLE);
                    tx1.setBackgroundColor(Color.WHITE);
                    counter--;
                    tx1.setText(Integer.toString(counter));

                    if (counter == 0) {
                        Toast.makeText(getApplicationContext(), "Restart App Before Next Login",Toast.LENGTH_SHORT).show();
                        btnLogin.setEnabled(false);
                    }
                }
            }
        });


    }
}
