package com.example.admin.budgethelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Budget extends AppCompatActivity {
    EditText amount;
    //EditText inputdate;
    //Button startdate;
    Button save;
    TextView budgetamount, currentbudget;

    String amountNum, amountVal;
    Double spendingsNum=0.0;
    //Integer remaining;

    //int year_x,month_x,day_x;
    //static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);
/*
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
*/
        //showDialogOnButtonClick();

        amount=(EditText)findViewById(R.id.amount);
        //inputdate=(EditText)findViewById(R.id.dateInput);

        amountVal = ""+amount.getText();

        budgetamount=(TextView)findViewById(R.id.budgetamount);
        currentbudget=(TextView)findViewById(R.id.currentbudgetamount);


        save=(Button)findViewById(R.id.saveButton);


        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_AMOUNT + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);
        if(c.moveToLast()){
            //do{
            //assing values
            amountNum = c.getString(0);
            //Do something Here with values

            //}while(c.moveToNext());
        }
        c.close();
        db.close();

        if(amountVal.equals("")){
            budgetamount.setText("No Budget");
        }else{
            budgetamount.setText("$"+amountNum);
        }

        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
        Cursor c2 = db2.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c2.moveToFirst()){
            do{
                //assing values
                String item = c2.getString(0);

                //Do something Here with values
                spendingsNum+= (Double.valueOf(item));

                //assing values
                //spendingsNum = c2.getString(0);
                //Do something Here with values

            }while(c2.moveToNext());
        }
        c2.close();
        db2.close();

        if(spendingsNum==0){
            currentbudget.setText("No Spendings");
        }else {
            Double remaining = (Double.valueOf(amountNum) - spendingsNum);
            DecimalFormat format = new DecimalFormat("##.00");

            currentbudget.setText("$" + format.format(remaining));
        }

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                /*
                bhDatabase myDB = new bhDatabase(getApplicationContext());
                SQLiteDatabase userDB = myDB.getWritableDatabase();
                BudgetDB myBudget = new BudgetDB("example", 100);
                        //Double.parseDouble(amount));
                userDB.createBudget(myBudget);
                */


                if(amountVal.equals("")){
                    String errormsg = "Please fill out all fields";
                    int duration=Toast.LENGTH_LONG;
                    Toast toast=Toast.makeText(getApplicationContext(), errormsg, duration);
                    toast.show();
                }else {

                    SimpleDateFormat sdfTime = new SimpleDateFormat("MM/dd/yyyy");
                    Date now = new Date();
                    String strTime = sdfTime.format(now);

                    DBHelper myDbHelper = new DBHelper(getApplicationContext());
                    SQLiteDatabase db = myDbHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();

                    values.put(DBContract.BudgetEntry.COLUMN_AMOUNT, amount.getText().toString());
                    values.put(DBContract.BudgetEntry.COLUMN_DATE, strTime);

                    long newRowId = db.insert(
                            DBContract.BudgetEntry.TABLE_NAME,
                            null,
                            values);

                    String result;

                    if (newRowId != -1) {
                        result = "Budget Updated";
                    } else {
                        result = "Error creating budget";
                    }


                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), result, duration);
                    toast.show();

                    amount.setText("");
                    //inputdate.setText("");
                    //amount.requestFocus();
                }


            }
        });
    }
/*
    public void showDialogOnButtonClick(){
        startdate = (Button)findViewById(R.id.startdate);

        startdate.setOnClickListener(
                new View.OnClickListener(){
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
            //Toast.makeText(Budget.this,month_x+"/"+day_x+"/"+year_x, Toast.LENGTH_SHORT).show();
            //startdate.setText(month_x + "/" + day_x + "/" + year_x);
            inputdate.setText(month_x + "/" + day_x + "/" + year_x);

        }
    };
*/
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
