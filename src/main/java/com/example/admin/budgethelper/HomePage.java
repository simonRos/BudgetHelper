package com.example.admin.budgethelper;

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

import java.text.DecimalFormat;


public class HomePage extends AppCompatActivity {

    //SQLiteDatabase db = bhDatabase.getWritableDatabase();
    //bhDatabase userDB= new bhDatabase(getApplicationContext());

    String amount;
    Double currentbudget=0.0;
    TextView budgetamount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        budgetamount=(TextView)findViewById(R.id.budgetamount);
/*
        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_AMOUNT + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);
        if(c.moveToLast()){
            //do{
                //assing values
                amount = c.getString(0);
                //Do something Here with values

            //}while(c.moveToNext());
        }
        c.close();
        db.close();

        budgetamount.setText("$"+amount);
*/

        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_AMOUNT + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);
        if(c.moveToLast()){
            //do{
            //assing values
            amount = c.getString(0);
            //Do something Here with values

            //}while(c.moveToNext());
        }
        c.close();
        db.close();

        //budgetamount.setText("$"+amount);

        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
        Cursor c2 = db2.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c2.moveToFirst()){
            do{
                //assing values
                String item = c2.getString(0);

                //Do something Here with values
                currentbudget+= (Double.valueOf(item));

            }while(c2.moveToNext());
        }
        c2.close();
        db2.close();

        Double remaining = (Double.valueOf(amount)-currentbudget);
        DecimalFormat format = new DecimalFormat("##.00");

        budgetamount.setText("$"+format.format(remaining));


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
