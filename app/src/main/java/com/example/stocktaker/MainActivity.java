package com.example.stocktaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.stocktaker.utility.DatabaseHelper;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText name, description, category, quantity;
    Button btnAdd,btnView, btnDelete, btnUpdate, search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, new String[] {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_GRANTED);

        myDB = new DatabaseHelper(this);
        name = (EditText)findViewById(R.id.name);
        description = (EditText)findViewById(R.id.description);
        category = (EditText)findViewById(R.id.category);
        quantity = (EditText)findViewById(R.id.quantity);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnDelete = (Button)findViewById(R.id.btnDelete);
        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        btnView = (Button)findViewById(R.id.btnView);
        search = (Button)findViewById(R.id.btnSearch);

        AddData();
        UpdateData();
        DeleteData();
        ViewData();
        SearchDb();

    }
    //add data to the database activity opens
    public void AddData(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbAdd.class);
                startActivity(intent);
            }
        });

    }

    public void SearchDb(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbSearch.class);
                startActivity(intent);
            }
        });

    }

    public void UpdateData(){
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbUpdate.class);
                startActivity(intent);
            }
        });

        }

    public void ViewData(){
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbView.class);
                startActivity(intent);
            }
        });
    }


    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DbDelete.class);
                startActivity(intent);
            }
        });

    }

    }
