package com.example.codetribe.reportcard;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class  Main2Activity extends AppCompatActivity {
    EditText sname;
    EditText cosc1;
    EditText gradecosc;
    EditText calculas;
    EditText gradecal;
    EditText statistics;
    EditText gradesta;
    EditText editTextId;
    Button btnPrev;
    Button btnNext;
    Button btnSave;
    Button btnDelete;
    Button menu;

    private static final String SELECT_SQL = "SELECT * FROM persons";
    private SQLiteDatabase db;
    private ProgressDialog pDialog;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        openDatabase();

        editTextId = (EditText) findViewById(R.id.editTextId);
        sname = (EditText) findViewById(R.id.sname);
        cosc1 = (EditText) findViewById(R.id.cosc1);
        gradecosc = (EditText) findViewById(R.id.gradecosc);
        calculas = (EditText) findViewById(R.id.calculas);
        gradecal= (EditText) findViewById(R.id.gradecal);
        statistics= (EditText) findViewById(R.id.statistics);
        gradesta = (EditText) findViewById(R.id.gradesta);

        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        menu= (Button) findViewById(R.id.button4);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveNext();

            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movePrev();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sname.length() == 0) {
                    sname.setError("This field can't be empty");
                    sname.requestFocus();
                    return;
                } else if (cosc1.length() == 0) {
                    cosc1.setError("This field can't be empty");
                    cosc1.requestFocus();
                    return;
                } else if (gradecosc.length() == 0) {
                    gradecosc.setError("This field can't be empty");
                    gradecosc.requestFocus();
                    return;
                } else if (calculas.length() == 0) {
                    calculas.setError("This field can't be empty");
                    calculas.requestFocus();
                    return;
                } else if (gradecal.length() == 0) {
                    gradecal.setError("This field can't be empty");
                    gradecal.requestFocus();
                    return;
                } else if (statistics.length() == 0) {
                    statistics.setError("This field can't be empty");
                    statistics.requestFocus();
                    return;
                }
                else if(editTextId.length() == 0) {
                    editTextId.setError("This field can't be empty");
                    editTextId.requestFocus();
                    return;
                }
                else {
                    pDialog = new ProgressDialog(Main2Activity.this);
                    pDialog.setTitle("Saving...");
                    pDialog.setMessage("Please Wait...");
                    pDialog.setIndeterminate(false);
                    pDialog.setCancelable(true);
                    pDialog.show();
                    pDialog.setMax(10000);
                    saveRecord();
                }

            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRecord();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pDialog = new ProgressDialog(Main2Activity.this);
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

        String id = c.getString(0);
        editTextId.setText(id);

        String name = c.getString(1);
        sname.setText(name);

        String c1 = c.getString(2);
        cosc1.setText(c1);

        String g = c.getString(3);
        gradecosc.setText(g);

        String cal = c.getString(4);
        calculas.setText(cal);

        String gra = c.getString(5);
        gradecal.setText(gra);

        String grad = c.getString(6);
        statistics.setText(grad);

        String statistics = c.getString(7);
        gradesta.setText(statistics);


    }

    protected void moveNext() {
        if (!c.isLast()) {
            c.moveToNext();

            showRecords();
        }

     else if(c.isLast()){
        Toast.makeText(getApplicationContext(),"This is the last one",Toast.LENGTH_LONG).show();
    }
    else{

        }
    }
    protected void movePrev() {
        if (!c.isFirst()) {
            c.moveToPrevious();
            showRecords();
        }
            else if(c.isFirst()){
                Toast.makeText(getApplicationContext(),"This is the first one",Toast.LENGTH_LONG).show();
            }
            else{

        }

    }


    protected void saveRecord() {
        String id = editTextId.getText().toString().trim();
        String name = sname.getText().toString().trim();
        String c1 = cosc1.getText().toString().trim();
        String g = gradecosc.getText().toString().trim();
        String cal = calculas.getText().toString().trim();
        String gra = gradecal.getText().toString().trim();
        String statistic = statistics.getText().toString().trim();
        String grad = gradesta.getText().toString().trim();

        // String sql = "UPDATE persons SET sname='" + sname + "', cosc1='" + cosc1 + "', gradecosc='" + gradecosc + "', calculas='" + calculas + "', gradecal='" + gradecal + "', statistics='" + statistics + "', gradesta='" + gradesta + "' WHERE id=" + id + ";";
        //String sql = "UPDATE persons WHERE id=" + id + ";";
        String sql = "UPDATE persons SET sname='" +name + "', cosc1 ='" + c1 + "', gradecosc='" + g + "', calculas ='" + cal + "', gradecal ='" + gra + "', statistics ='" + statistic + "', gradesta ='" + grad + "' WHERE id=" + id + ";";
      // String sql = "UPDATE persons SET name='" +g + "', c1 ='" + c1 + "', cal ='" + cal + "', statistic ='" + statistic + "' WHERE id=" + id + ";";

            db.execSQL(sql);
            Toast.makeText(getApplicationContext(), "Records Saved Successfully", Toast.LENGTH_LONG).show();
            c = db.rawQuery(SELECT_SQL, null);
            c.moveToPosition(Integer.parseInt(id));
        Intent my = new Intent(getApplicationContext(),Spinner.class);
        startActivity(my);

    }

    private void deleteRecord() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want delete this person?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        String id = editTextId.getText().toString().trim();

                        String sql1 = "DELETE FROM persons WHERE id=" + id + ";";
                        db.execSQL(sql1);

                        Toast.makeText(getApplicationContext(), "Record Deleted", Toast.LENGTH_LONG).show();
                        c = db.rawQuery(SELECT_SQL, null);
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });


        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }


}
