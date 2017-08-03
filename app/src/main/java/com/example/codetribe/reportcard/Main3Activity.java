package com.example.codetribe.reportcard;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class  Main3Activity extends AppCompatActivity {
    TextView textViewid;
    TextView textViewstdname;
    TextView textViewcosc;
    TextView textViewgrdcosc;
    TextView textViewcalc;
    TextView textViewgrdcalc;
    TextView textViewstat;
    TextView textViewsgrdstat;
    Button btnBack;
    Button btnNext;
    Button btnPrev;

    private static final String SELECT_SQL = "SELECT * FROM persons";

    private SQLiteDatabase db;
    private ProgressDialog pDialog;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        openDatabase();

       // textViewid= (TextView) findViewById(R.id.textViewId);
        textViewstdname= (TextView) findViewById(R.id.textViewstdname);
        textViewcosc= (TextView) findViewById(R.id.textViewcosc);
        textViewgrdcosc= (TextView) findViewById(R.id.textViewgrdcosc);
        textViewcalc= (TextView) findViewById(R.id.textViewcalc);
        textViewgrdcalc= (TextView) findViewById(R.id.textViewgrdcalc);
        textViewstat= (TextView) findViewById(R.id.textViewstat);
        textViewsgrdstat= (TextView) findViewById(R.id.textViewsgrdstat);

        btnBack = (Button) findViewById(R.id.button);
        btnNext = (Button) findViewById(R.id.button5);
        btnPrev = (Button) findViewById(R.id.button6);

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePrev();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveNext();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(Main3Activity.this);
                pDialog.setTitle("Loading menu...");
                pDialog.setMessage("Please Wait...");
                pDialog.setIndeterminate(false);
                pDialog.setCancelable(true);
                pDialog.show();
                pDialog.setMax(10000);

                Intent i = new Intent(getApplicationContext(),Spinner.class);
                startActivity(i);
            }
        });

        c = db.rawQuery(SELECT_SQL, null);
        c.moveToFirst();
        showRecords();
    }

    protected void openDatabase() {
        db = openOrCreateDatabase("PersonDB", Context.MODE_PRIVATE, null);
    }

    protected void showRecords() {

       // String id = c.getString(0);
       // textViewid.setText(id);

        String name = c.getString(1);
        textViewstdname.setText(name);

        String c1 = c.getString(2);
        textViewcosc.setText(c1);

        String g = c.getString(3);
        textViewgrdcosc.setText(g);

        String cal = c.getString(4);
        textViewcalc.setText(cal);

        String gra = c.getString(5);
        textViewgrdcalc.setText(gra);

        String grad = c.getString(6);
        textViewstat.setText(grad);

        String statistics = c.getString(7);
        textViewsgrdstat.setText(statistics);


    }

    protected void moveNext() {
        if (!c.isLast()) {
            c.moveToNext();

            showRecords();
        }
    }

    protected void movePrev() {
        if (!c.isFirst()) {
            c.moveToPrevious();

            showRecords();
        }
    }

}
