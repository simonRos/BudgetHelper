package com.example.admin.budgethelper;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

public class SpendingsEdit extends AppCompatActivity {

    TextView spendingsList;
    String[] item, item1, item2;
    String output="";
    Integer i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendingsedit);

        spendingsList=(TextView)findViewById(R.id.spendingsList);

        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + ", "+DBContract.SpendingsEntry.COLUMN_DATE + ", " +DBContract.SpendingsEntry.COLUMN_STORE+ " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c.moveToFirst()){
            do{
                //assing values
                item[i] = c.getString(0);
                item1[i] = c.getString(1);
                item2[i] = c.getString(2);
                //Do something Here with values

                i++;

            }while(c.moveToNext());
        }
        c.close();
        db.close();

        for(Integer j=0; j<i; j++){
            output=output+"$"+item[j]+" "+item1[j]+" "+item2[j]+" NEXT ";
        }

        spendingsList.setText(output);

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
