package com.example.stocktaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stocktaker.utility.DatabaseHelper;

public class DbSearch extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText value;
    Button searchId,searchName,searchDescription,searchCategory,searchQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_search);

        myDB = new DatabaseHelper(this);
        value = (EditText) findViewById(R.id.editTextId);
        searchId = (Button) findViewById(R.id.btnSearchId);
        searchCategory = (Button) findViewById(R.id.btnSearchCategory);
        searchDescription = (Button) findViewById(R.id.btnSearchDescription);
        searchName = (Button) findViewById(R.id.btnSearchName);
        searchQuantity = (Button) findViewById(R.id.btnSearchQuantity);
        SearchDBName();
        SearchDBCategory();
        SearchDBDescription();
        SearchDBid();
        SearchDBQuantity();

    }

    public void SearchDBName (){
        searchName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n = value.getText().toString();
                Cursor res = myDB.searchAllDataName(n);

                if (value.getText().toString().length()>0){
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
                else {Toast.makeText(DbSearch.this, "Enter Name", Toast.LENGTH_LONG).show();}
            }
        });
    }

    public void SearchDBid (){
        searchId.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n = value.getText().toString();
                Cursor res = myDB.searchAllDataID(n);

                if (value.getText().toString().length()>0){
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
                else {Toast.makeText(DbSearch.this, "Enter an ID", Toast.LENGTH_LONG).show();}
            }
        });
    }

    public void SearchDBQuantity (){
        searchQuantity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n = value.getText().toString();
                Cursor res = myDB.searchAllDataQuantity(n);

                if (value.getText().toString().length()>0){
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
                else {Toast.makeText(DbSearch.this, "Enter Quantity", Toast.LENGTH_LONG).show();}
            }
        });
    }

    public void SearchDBDescription (){
        searchDescription.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n = value.getText().toString();
                Cursor res = myDB.searchAllDataDescription(n);

                if (value.getText().toString().length()>0){
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
                else {Toast.makeText(DbSearch.this, "Enter Description", Toast.LENGTH_LONG).show();}
            }
        });
    }

    public void SearchDBCategory (){
        searchCategory.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String n = value.getText().toString();
                Cursor res = myDB.searchAllDataCategory(n);

                if (value.getText().toString().length()>0){
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
                else {Toast.makeText(DbSearch.this, "Enter Category", Toast.LENGTH_LONG).show();}
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