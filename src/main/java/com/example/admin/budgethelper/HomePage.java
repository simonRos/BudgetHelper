/*
        CS 300
        BudgetHelper App
        May 1, 2016

        Home/Main page for app.
        Displays remaining budget.
*/

        package com.example.admin.budgethelper;
//Android imports
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
//Java imports
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.concurrent.TimeUnit;
        import java.text.DateFormat;
        import java.text.DecimalFormat;

public class HomePage extends AppCompatActivity {

    String amount, date;//Budget values pulled from the database
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


        //Select current budget from the database to determine if the weekly budget should be reset
        DBHelper myDbHelper = new DBHelper(getApplicationContext());
        SQLiteDatabase db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT " + DBContract.BudgetEntry.COLUMN_AMOUNT + ", " + DBContract.BudgetEntry.COLUMN_DATE + " FROM " + DBContract.BudgetEntry.TABLE_NAME, null);

        //Only select the most recent budget inputted
        if(c.moveToLast()){
            amount = c.getString(0);
            date = c.getString(1);

            //Get current timestamp
            SimpleDateFormat sdfTime = new SimpleDateFormat("MM/dd/yyyy");
            Date now = new Date();
            String strTime = sdfTime.format(now);

            //Create object "formatter" to format the dates
            String expectedPattern = "MM/dd/yyyy";
            SimpleDateFormat formatter = new SimpleDateFormat(expectedPattern);

            //Exception handling
            try
            {
                //format budget start date and current date
                budgetDate = formatter.parse(date);
                curDate = formatter.parse(strTime);

            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }

            //Find the number of milliseconds between the budget date and current date
            long msDiff = curDate.getTime() - budgetDate.getTime();
            //Change the milliseconds to days
            days = TimeUnit.MILLISECONDS.toDays(msDiff);

        }

        c.close();
        db.close();

        //If there are 7 or more days between the budget start date and current date
        //then it's been at least a week and a new budget is created with the same total budget amount
        //but the start date is changed to the current date
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

        //Select the spendings from the database to subtract from the total budget
        DBHelper myDbHelper2 = new DBHelper(getApplicationContext());
        SQLiteDatabase db2 = myDbHelper2.getReadableDatabase();
        Cursor c2 = db2.rawQuery("SELECT " + DBContract.SpendingsEntry.COLUMN_AMOUNT + ", " +DBContract.SpendingsEntry.COLUMN_DATE+ " FROM " + DBContract.SpendingsEntry.TABLE_NAME, null);
        if(c2.moveToFirst()){
            do{
                String item = c2.getString(0);//Spending amount
                String item2 = c2.getString(1);//Spending Date

                //Format the Date for the current spending selected
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

                //if the spendDate is after the budget start date then subtract it from the total budget
                if(spendDate.after(budgetDate)){
                    currentbudget+= (Double.valueOf(item));
                }//if the spendDate is before the budget don't subtract it because it is not part of this weeks budget

            }while(c2.moveToNext());
        }
        c2.close();
        db2.close();

        //if there is no current budget in the db output "No Budget"
        if(date == null){
            budgetamount.setText("No Budget");
        }else {
            Double remaining = (Double.valueOf(amount) - currentbudget);
            DecimalFormat format = new DecimalFormat("##.00");

            budgetamount.setText("$" + format.format(remaining));
        }


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