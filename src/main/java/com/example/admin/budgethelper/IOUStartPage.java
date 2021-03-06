/*
CS 300
BudgetHelper App
May 1, 2016

This is the Page you come to when you select "Add IOU/UO" in the main menu
There are two buttons that the user can select to direct them to either
add an IOU or UO

IOU - Friend borrowed money from the user
UO - The user borrowed money from a friend
 */

package com.example.admin.budgethelper;

//Imports
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

public class IOUStartPage extends AppCompatActivity {
    Button iou;
    Button uo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ioustartpage);

        iou=(Button)findViewById(R.id.iouButton);
        uo=(Button)findViewById(R.id.uoButton);

        iou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //take user to IOU Input page
                Intent intent = new Intent(getApplicationContext(), IOU.class);
                startActivity(intent);
            }
        });

        uo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //take user to UO Input page
                Intent intent = new Intent(getApplicationContext(), UO.class);
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
