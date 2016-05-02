/*
CS 300
BudgetHelper App
May 1, 2016

Calculates Budget according to spendings.
Also handles updated budgets.
 */

package com.example.admin.budgethelper;
//Aimports
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
//Jimports
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Budget extends AppCompatActivity {
    EditText amount;    //Edit amount text
    Button save;    //Save button
    TextView budgetamount, currentbudget;   //Amount and budget text

    //amountNum= the value pulled from the database
    //amountVal= is the String equivalent to amount
    String amountNum, amountVal, dateStr, spendDateStr;
    Double spendingsNum=0.0;
    Date spendDate = new Date();
    Date budgDate = new Date();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        amount=(EditText)findViewById(R.id.amount);
        budgetamount=(TextView)findViewById(R.id.budgetamount);
        currentbudget=(TextView)findViewById(R.id.currentbudgetamount);
        save=(Button)findViewById(R.id.saveButton);

        DBHelper myDbHelper = new DBHelper(getApplicationContext());    //DBHelper
        SQLiteDatabase db = myDbHelper.getReadableDatabase();   //Readable database
        //Cursor is grabbing latest budget from Database
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_AMOUNT + ", " + DBContract.BudgetEntry.COLUMN_DATE + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);
        if(c.moveToLast()){
            //assing values
            amountNum = c.getString(0);
            dateStr = c.getString(1);
            //Do something Here with values

            // (1) create a SimpleDateFormat object with the desired format.
            // this is the format/pattern we're expecting to receive.
            String expectedPattern = "MM/dd/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

            try
            {
                // (2) give the formatter a String that matches the SimpleDateFormat pattern
                budgDate = formatter.parse(dateStr);
            }
            catch (ParseException e)
            {
                // execution will come here if the String that is given
                // does not match the expected format.
                e.printStackTrace();
            }

        }
        c.close();
        db.close();

        if(amountNum.equals("")){   //Sanitizing input. This catches blank input.
            budgetamount.setText("No Budget");
        }else{
            budgetamount.setText("$"+amountNum);
        }

        //DB for spendings
        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
        Cursor c2 = db2.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + ", " + DBContract.SpendingsEntry.COLUMN_DATE + " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c2.moveToFirst()){
            do{
                String item = c2.getString(0);
                spendDateStr = c2.getString(1);

                String expectedPattern = "MM/dd/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

                try
                {

                    spendDate = formatter.parse(spendDateStr);

                }
                catch (ParseException e)
                {

                    e.printStackTrace();
                }

                if(spendDate.after(budgDate)){
                    spendingsNum+= (Double.valueOf(item));
                }




            }while(c2.moveToNext());    //While more spendings exist
        }
        c2.close();
        db2.close();

        if(spendingsNum==0){
            currentbudget.setText("$"+amountNum);
        }else {
            Double remaining = (Double.valueOf(amountNum) - spendingsNum);
            DecimalFormat format = new DecimalFormat("##.00");

            currentbudget.setText("$" + format.format(remaining));
        }

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                amountVal=""+amount.getText();

                if(amountVal.equals("")){   //Check for blank input
                    String errormsg = "Please fill out all fields";
                    int duration=Toast.LENGTH_LONG;
                    Toast toast=Toast.makeText(getApplicationContext(), errormsg, duration);    //errorToast
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

                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){    //Menu
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
