package com.example.stocktaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.stocktaker.utility.DatabaseHelper;

public class DbView extends AppCompatActivity {
    DatabaseHelper myDB;
    Button view,viewInStock,viewOutStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);
        myDB = new DatabaseHelper(this);
        viewInStock = (Button) findViewById(R.id.btnInstock);
        viewOutStock = (Button)findViewById(R.id.btnNoStock);
        view = (Button)findViewById(R.id.btnView);
        viewAll();
        inStock();
        outStock();
    }

    public void viewAll() {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               Cursor res = myDB.getAllData();
               if(res.getCount() == 0){
                   //show message
                   showMessage("Error","Nothing found");
                   return;
               }
               StringBuffer buffer = new StringBuffer();
               while (res.moveToNext()){
                   buffer.append("ID :" + res.getString(0)+ "\n");
                   buffer.append("NAME :" + res.getString(1)+ "\n");
                   buffer.append("DESCRIPTION :" + res.getString(2)+ "\n");
                   buffer.append("CATEGORY :" + res.getString(3)+ "\n");
                   buffer.append("QUANTITY :" + res.getString(4)+ "\n\n");
               }
               showMessage("Data", buffer.toString());
            }
        });
    }

    public void inStock() {
        viewInStock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cursor res = myDB.getAllDataInstock();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :" + res.getString(0)+ "\n");
                    buffer.append("NAME :" + res.getString(1)+ "\n");
                    buffer.append("DESCRIPTION :" + res.getString(2)+ "\n");
                    buffer.append("CATEGORY :" + res.getString(3)+ "\n");
                    buffer.append("QUANTITY :" + res.getString(4)+ "\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void outStock() {
        viewOutStock.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Cursor res = myDB.getAllDataOutOfStock();
                if(res.getCount() == 0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :" + res.getString(0)+ "\n");
                    buffer.append("NAME :" + res.getString(1)+ "\n");
                    buffer.append("DESCRIPTION :" + res.getString(2)+ "\n");
                    buffer.append("CATEGORY :" + res.getString(3)+ "\n");
                    buffer.append("QUANTITY :" + res.getString(4)+ "\n\n");
                }
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}