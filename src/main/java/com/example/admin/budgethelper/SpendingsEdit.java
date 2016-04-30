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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.Toast;
import android.database.Cursor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SpendingsEdit extends AppCompatActivity {

    TextView spendingsList;
    String amount, spendDateStr, store, dateStr;
    Date spendDate = new Date();
    Date budgDate = new Date();
    //Integer i=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendingsedit);

        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_DATE + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);
        if(c.moveToLast()){
            //do{
            //assing values
            dateStr = c.getString(0);
            //Do something Here with values

            // (1) create a SimpleDateFormat object with the desired format.
            // this is the format/pattern we're expecting to receive.
            String expectedPattern = "MM/dd/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

            try
            {
                // (2) give the formatter a String that matches the SimpleDateFormat pattern
                //String userInput = "09/22/2009";
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

        //spendingsList=(TextView)findViewById(R.id.spendingsList);
        /* Find Tablelayout defined in main.xml */
        final TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout1);

        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
        Cursor c2 = db2.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + ", "+DBContract.SpendingsEntry.COLUMN_DATE + ", " +DBContract.SpendingsEntry.COLUMN_STORE+ " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c2.moveToFirst()){
            do{
                //assing values
                amount = c2.getString(0);
                spendDateStr = c2.getString(1);
                store = c2.getString(2);
                //Do something Here with values

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

                if(spendDate.after(budgDate)) {

                    // Creation row
                    final TableRow tableRow = new TableRow(this);
                    tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

                    // Creation textView
                    final TextView blank = new TextView(this);
                    blank.setText(" ");
                    blank.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    // Creation textView
                    final TextView text1 = new TextView(this);
                    text1.setText(spendDateStr);
                    text1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    // Creation textView
                    final TextView text2 = new TextView(this);
                    text2.setText("$" + amount);
                    text2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    // Creation textView
                    final TextView text3 = new TextView(this);
                    text3.setText(store);
                    text3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                    // Creation  button
                    final Button button = new Button(this);
                    button.setText("Delete");
                    button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final TableRow parent = (TableRow) v.getParent();
                            tableLayout.removeView(parent);
                        }
                    });

                    tableRow.addView(blank);
                    tableRow.addView(text1);
                    tableRow.addView(text2);
                    tableRow.addView(text3);
                    tableRow.addView(button);

                    tableLayout.addView(tableRow);
                }

            }while(c.moveToNext());
        }
        c2.close();
        db2.close();

        //spendingsList.setText(item);

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
