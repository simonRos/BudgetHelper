package com.example.admin.budgethelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class IOU extends AppCompatActivity {

    Button save,ioudate;
    EditText amount,name, dateInput;
    String amountVal, nameVal, dateInputVal;

    int year_x,month_x,day_x;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iou);

        final java.util.Calendar cal = java.util.Calendar.getInstance();
        year_x = cal.get(java.util.Calendar.YEAR);
        month_x = cal.get(java.util.Calendar.MONTH);
        day_x = cal.get(java.util.Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();

        amount=(EditText)findViewById(R.id.amount);
        name=(EditText)findViewById(R.id.name);
        dateInput=(EditText)findViewById(R.id.dateInput);

        amountVal = ""+amount.getText();
        nameVal = ""+amount.getText();
        dateInputVal = ""+dateInput.getText();

        save=(Button)findViewById(R.id.saveButton);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                bhDatabase myDB = new bhDatabase(getApplicationContext());
                SQLiteDatabase userDB = myDB.getWritableDatabase();
                BudgetDB myBudget = new BudgetDB("example", 100);
                        //Double.parseDouble(amount));
                userDB.createBudget(myBudget);
                */


                if(amountVal.equals("") || nameVal.equals("") || dateInputVal.equals("")){
                    String errormsg = "Please fill out all fields";
                    int duration=Toast.LENGTH_LONG;
                    Toast toast=Toast.makeText(getApplicationContext(), errormsg, duration);
                    toast.show();
                }else {
                    DBHelper myDbHelper = new DBHelper(getApplicationContext());
                    SQLiteDatabase db = myDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(DBContract.IOUEntry.COLUMN_AMOUNT, amount.getText().toString());
                    values.put(DBContract.IOUEntry.COLUMN_FROM, name.getText().toString());
                    values.put(DBContract.IOUEntry.COLUMN_DATE, dateInput.getText().toString());

                    long newRowId = db.insert(
                            DBContract.IOUEntry.TABLE_NAME,
                            null,
                            values);

                    String result;

                    if (newRowId != -1) {
                        result = "IOU Updated";
                    } else {
                        result = "Error creating IOU";
                    }


                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), result, duration);
                    toast.show();

                    amount.setText("");
                    name.setText("");
                    amount.requestFocus();
                }
            }
        });
    }

    public void showDialogOnButtonClick(){
        ioudate = (Button)findViewById(R.id.ioudate);

        ioudate.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(DIALOG_ID);



                    }
                }
        );
    }

    @Override
    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID)
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener(){
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth){
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            //Toast.makeText(IOU.this, month_x + "/" + day_x + "/" + year_x, Toast.LENGTH_SHORT).show();
            //ioudate.setText(month_x + "/" + day_x + "/" + year_x);
            dateInput.setText(month_x+"/"+day_x+"/"+year_x);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        switch (id){
            case R.id.home:
                Intent intent1 = new Intent(getApplicationContext(),HomePage.class);
                startActivity(intent1);
                return true;
            case R.id.spending:
                Intent intent2 = new Intent(getApplicationContext(),Spendings.class);
                startActivity(intent2);
                return true;
            case R.id.updatebudget:
                Intent intent3 = new Intent(getApplicationContext(),Budget.class);
                startActivity(intent3);
                return true;
            case R.id.iouStartPage:
                Intent intent4 = new Intent(getApplicationContext(), IOUStartPage.class);
                startActivity(intent4);
                return true;
            case R.id.calendar:
                Intent intent5 = new Intent(getApplicationContext(), Calendar.class);
                startActivity(intent5);
                return true;
            case R.id.account:
                Intent intent6 = new Intent(getApplicationContext(), AccountStartPage.class);
                startActivity(intent6);
                return true;

            default:
                return true;
        }
    }
}
