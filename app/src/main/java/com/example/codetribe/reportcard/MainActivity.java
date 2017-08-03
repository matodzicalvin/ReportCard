package com.example.codetribe.reportcard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public SQLiteDatabase db;
    private ProgressDialog pDialog;



    EditText NAME;
    EditText COSC;
    EditText GCOSC;
    EditText CALCULAS;
    EditText GCALC;
    EditText STATISTICS;
    EditText GSTATS;

    Button SAVE;
    Button EDIT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NAME= (EditText) findViewById(R.id.sname);
        COSC= (EditText) findViewById(R.id.cosc1);
        GCOSC= (EditText) findViewById(R.id.gradecosc);
        CALCULAS= (EditText) findViewById(R.id.calculas);
        GCALC= (EditText) findViewById(R.id.gradecal);
        STATISTICS= (EditText) findViewById(R.id.statistics);
        GSTATS= (EditText) findViewById(R.id.gradesta);

        SAVE= (Button) findViewById(R.id.button2);
        EDIT=(Button) findViewById(R.id.button3);


        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(MainActivity.this);
                pDialog.setTitle("Loading menu...");
                pDialog.setMessage("Please Wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
                pDialog.setMax(10000);
                Intent i=new Intent(getApplicationContext(),Spinner.class);
                startActivity(i);
            }
        });

        SAVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(NAME.length()==0){
                    NAME.setError("This field can't be empty");
                    NAME.requestFocus();
                }
                else if(COSC.length()==0){
                    COSC.setError("This field can't be empty");
                    COSC.requestFocus();
                }
                else if(GCOSC.length()==0){
                    GCOSC.setError("This field can't be empty");
                    GCOSC.requestFocus();
                }
               else if(CALCULAS.length()==0){
                    CALCULAS.setError("This field can't be empty");
                    CALCULAS.requestFocus();
                }
                else if(GCALC.length()==0){
                    GCALC.setError("This field can't be empty");
                    GCALC.requestFocus();
                }
                else if(STATISTICS.length()==0){
                    STATISTICS.setError("This field can't be empty");
                    STATISTICS.requestFocus();
                }
                else if(GSTATS.length()==0){
                    GSTATS.setError("This field can't be empty");
                    GSTATS.requestFocus();
                }else {
                    createDatabase();

                     AddMarks();

                }
            }
        });

    }
    protected void createDatabase(){
        db=openOrCreateDatabase("PersonDB",Context.MODE_PRIVATE, null);

       // db=openOrCreateDatabase("PersonsDB",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS persons(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, sname VARCHAR,cosc1 VARCHAR,gradecosc INTEGER,calculas VARCHAR,gradecal INTEGER,statistics VARCHAR, gradesta INTEGER  );");
    }
  public void AddMarks () {


      pDialog = new ProgressDialog(MainActivity.this);
      pDialog.setTitle("Creating Report");
      pDialog.setMessage("Please Wait...");
      pDialog.setIndeterminate(false);
      pDialog.setCancelable(true);
      pDialog.show();
      pDialog.setMax(10000);

      insertIntoDB();


        }



        protected void insertIntoDB(){

        String sname = NAME .getText().toString().trim();
        String cosc1 = COSC.getText().toString().trim();
        String gradecosc = GCOSC.getText().toString().trim();
        String calculas = CALCULAS.getText().toString().trim();
        String gradecal = GCALC.getText().toString().trim();
        String statistics = STATISTICS.getText().toString().trim();
        String gradesta = GSTATS.getText().toString().trim();


        String query = "INSERT INTO persons (sname,cosc1,gradecosc,calculas,gradecal,statistics,gradesta) VALUES('"+sname+"', '"+cosc1+"', '"+gradecosc+"', '"+calculas+"', '"+gradecal+"', '"+statistics+"', '"+gradesta+"');";
        db.execSQL(query);

       Toast.makeText(getApplicationContext(),"Saved Successfully", Toast.LENGTH_LONG).show();
            Diadismiss();
    }
    public void Diadismiss(){
       Intent i = new Intent(MainActivity.this,Spinner.class);
        startActivity(i);
   }

}
