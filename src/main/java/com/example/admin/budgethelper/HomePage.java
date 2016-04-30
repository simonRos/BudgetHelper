package com.example.admin.budgethelper;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;

import java.text.DecimalFormat;


public class HomePage extends AppCompatActivity {

    //SQLiteDatabase db = bhDatabase.getWritableDatabase();
    //bhDatabase userDB= new bhDatabase(getApplicationContext());

    String amount, date;
    Double currentbudget=0.0;
    TextView budgetamount;
    Date curDate = new Date();
    Date budgetDate = new Date();
    Date spendDate = new Date();
    long days;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        budgetamount=(TextView)findViewById(R.id.budgetamount);


        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_AMOUNT + ", " + DBContract.BudgetEntry.COLUMN_DATE + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);
        if(c.moveToLast()){
            //do{
            //assing values
            amount = c.getString(0);
            date = c.getString(1);
            //Do something Here with values

            SimpleDateFormat sdfTime = new SimpleDateFormat("MM/dd/yyyy");
            Date now = new Date();
            String strTime = sdfTime.format(now);

            // (1) create a SimpleDateFormat object with the desired format.
            // this is the format/pattern we're expecting to receive.
            String expectedPattern = "MM/dd/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

            try
            {
                // (2) give the formatter a String that matches the SimpleDateFormat pattern
                //String userInput = "09/22/2009";
                budgetDate = formatter.parse(date);
                curDate = formatter.parse(strTime);

                // (3) prints out "Tue Sep 22 00:00:00 EDT 2009"
                //System.out.println(date);
            }
            catch (ParseException e)
            {
                // execution will come here if the String that is given
                // does not match the expected format.
                e.printStackTrace();
            }

            long msDiff = curDate.getTime() - budgetDate.getTime();
            days = TimeUnit.MILLISECONDS.toDays(msDiff);
/*
            if(days>=7){
                amount="It works";
            }else{
                amount="It doesn't work";
            }

*/

            //}while(c.moveToNext());
        }

        c.close();
        db.close();

        if(days>=7){
            DBHelper myDbHelper1 = new DBHelper(getApplicationContext());
            SQLiteDatabase db1 = myDbHelper1.getWritableDatabase();
            ContentValues values = new ContentValues();

            values.put(DBContract.BudgetEntry.COLUMN_AMOUNT, amount.toString());
            values.put(DBContract.BudgetEntry.COLUMN_DATE, curDate.toString());

            long newRowId = db1.insert(
                    DBContract.BudgetEntry.TABLE_NAME,
                    null,
                    values);

            String result;

            budgetDate = curDate;

            if(newRowId != -1){
                result = "Budget Updated";
            }else{
                result = "Error creating budget";
            }


            int duration=Toast.LENGTH_LONG;
            Toast toast=Toast.makeText(getApplicationContext(), result, duration);
            toast.show();
        }

        //budgetamount.setText("$"+amount);

        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
        Cursor c2 = db2.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + ", " +DBContract.SpendingsEntry.COLUMN_DATE+ " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c2.moveToFirst()){
            do{
                //assing values
                String item = c2.getString(0);
                String item2 = c2.getString(1);

                String expectedPattern = "MM/dd/yyyy";
                SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

                try
                {

                    spendDate = formatter.parse(item2);

                }
                catch (ParseException e)
                {

                    e.printStackTrace();
                }

                if(spendDate.after(budgetDate)){
                    currentbudget+= (Double.valueOf(item));
                }

            }while(c2.moveToNext());
        }
        c2.close();
        db2.close();

        if(currentbudget==0){
            budgetamount.setText("No Budget");
        }else {
            Double remaining = (Double.valueOf(amount) - currentbudget);
            DecimalFormat format = new DecimalFormat("##.00");

            budgetamount.setText("$" + format.format(remaining));
            //budgetamount.setText(amount);
        }


    }


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
