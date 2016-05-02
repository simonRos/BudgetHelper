/*
CS 300
BudgetHelper App
May 1, 2016

Allows user to view existing UOs.
Allows user to delete UOs
 */

package com.example.admin.budgethelper;
//Aimports
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
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
//Jimports
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Locale;

public class UOEdit extends AppCompatActivity {

    String item, item1, item2;
    int item3;
    Date date, curDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uoedit);

        /* Find Tablelayout defined in main.xml */
        final TableLayout tableLayout = (TableLayout)findViewById(R.id.tableLayout1);

        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        //Cursor needed to access database
        Cursor c = db.rawQuery("SELECT " + DBContract.UOEntry.COLUMN_AMOUNT + ", " + DBContract.UOEntry.COLUMN_DATE + ", " + DBContract.UOEntry.COLUMN_TO + ", " + DBContract.UOEntry._ID + " FROM " + DBContract.UOEntry.TABLE_NAME, null);
        if(c.moveToFirst()){
            do{
                //assign values
                item = c.getString(0);
                item1 = c.getString(1);
                item2 = c.getString(2);
                item3 = c.getInt(3);

                // Creation row
                final TableRow tableRow = new TableRow(this);
                tableRow.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

                // Creation textView
                final TextView blank = new TextView(this);
                blank.setText(" ");
                blank.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                // Creation textView
                final TextView text1 = new TextView(this);
                text1.setText(item1);//item1
                text1.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                // Creation textView
                final TextView text2 = new TextView(this);
                text2.setText("$" + item);
                text2.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                // Creation textView
                final TextView text3 = new TextView(this);
                text3.setText(item2);//item2
                text3.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

                // Creation  button
                final Button button = new Button(this);
                button.setText("Delete");
                button.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final TableRow parent = (TableRow) v.getParent();
                        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
                        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
                        String[] whereArgs = new String[] { String.valueOf(item3) };
                        long newRowId = db2.delete(
                                DBContract.UOEntry.TABLE_NAME,
                                DBContract.UOEntry._ID+ "=?",
                                whereArgs);

                        String result;
                        //Has UO been successfully deleted?
                        if (newRowId != -1) {
                            tableLayout.removeView(parent);
                            result = "UO Deleted";
                        } else {
                            result = "Error Deleting UO";
                        }

                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(getApplicationContext(), result, duration);
                        toast.show();
                    }
                });

                tableRow.addView(blank);
                tableRow.addView(text1);
                tableRow.addView(text2);
                tableRow.addView(text3);
                tableRow.addView(button);

                tableLayout.addView(tableRow);

            }while(c.moveToNext());
        }
        c.close();
        db.close();


    }

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
