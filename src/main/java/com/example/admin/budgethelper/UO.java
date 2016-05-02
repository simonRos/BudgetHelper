/*
CS 300
BudgetHelper App
May 1, 2016

UO Input
Allows user to enter new UOs
 */

package com.example.admin.budgethelper;
//imports
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class UO extends AppCompatActivity {

    EditText amount,name, dateInput; //textFields for amount, name, date
    Button uodate, save;    //date and save buttons
    String amountVal, nameVal, dateInputVal; //String values equivalent to the users input
    int year_x,month_x,day_x;   //date as int
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uo);

        //get java calendar and set with current year, month, day
        final java.util.Calendar cal = java.util.Calendar.getInstance();
        year_x = cal.get(java.util.Calendar.YEAR);
        month_x = cal.get(java.util.Calendar.MONTH);
        day_x = cal.get(java.util.Calendar.DAY_OF_MONTH);

        showDialogOnButtonClick();

        amount=(EditText)findViewById(R.id.amount); //amount field
        name=(EditText)findViewById(R.id.name); //name field
        dateInput=(EditText)findViewById(R.id.dateInput);   //date field

        save=(Button)findViewById(R.id.saveButton); //save button

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amountVal = ""+amount.getText();
                nameVal = ""+name.getText();
                dateInputVal = ""+dateInput.getText();

                //check for empty fields
                if(amountVal.equals("") || nameVal.equals("") || dateInputVal.equals("")){

                    //error toast
                    String errormsg = "Please fill out all fields";
                    int duration=Toast.LENGTH_LONG;
                    Toast toast=Toast.makeText(getApplicationContext(), errormsg, duration);
                    toast.show();

                }else {
                    DBHelper myDbHelper = new DBHelper(getApplicationContext());
                    SQLiteDatabase db = myDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    //insert UO into db
                    values.put(DBContract.UOEntry.COLUMN_AMOUNT, amount.getText().toString());
                    values.put(DBContract.UOEntry.COLUMN_TO, name.getText().toString());
                    values.put(DBContract.UOEntry.COLUMN_DATE, dateInput.getText().toString());

                    long newRowId = db.insert(
                            DBContract.UOEntry.TABLE_NAME,
                            null,
                            values);

                    String result
                            ;
                    //Check if the UO successfully added to db
                    if (newRowId != -1) {
                        result = "UO Updated";
                    } else {
                        result = "Error creating UO";
                    }

                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), result, duration);
                    toast.show();

                    //make fields blank
                    amount.setText("");
                    name.setText("");
                    dateInput.setText("");
                    amount.requestFocus();
                }
            }
        });
    }

    public void showDialogOnButtonClick(){
        uodate = (Button)findViewById(R.id.uodate);

        uodate.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showDialog(DIALOG_ID);

                    }
                }
        );
    }

    //Date picker
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
            dateInput.setText(month_x+"/"+day_x+"/"+year_x);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //menu
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