/*
CS 300
BudgetHelper App
May 1, 2016

Account Start page.
Includes three buttons the user can select to see their current spendings, IOUs, and UOs
 */

package com.example.admin.budgethelper;
//imports
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.view.MenuItem;
import android.widget.Toast;

public class AccountStartPage extends AppCompatActivity {
    Button spendings, iou, uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accntstartpage);

        spendings=(Button)findViewById(R.id.spendListButton); //To Spendings button
        iou=(Button)findViewById(R.id.iouListButton);   //To IOU button
        uo=(Button)findViewById(R.id.uoListButton); //To UO button

        spendings.setOnClickListener(new View.OnClickListener() {   //Click Listeners for Spending
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SpendingsEdit.class);
                startActivity(intent);
            }
        });

        iou.setOnClickListener(new View.OnClickListener() { //Click Listeners for IOU
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IOUEdit.class);
                startActivity(intent);
            }
        });

        uo.setOnClickListener(new View.OnClickListener(){ //Click Listeners for UO
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), UOEdit.class);
                startActivity(intent);
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