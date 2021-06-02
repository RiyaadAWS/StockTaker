package com.example.stocktaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stocktaker.utility.DatabaseHelper;

public class DbAdd extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText name, description, category, quantity;
    Button btnadd, clear, back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_add);

        // DB helper connection
        myDB = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        category = (EditText) findViewById(R.id.category);
        quantity = (EditText) findViewById(R.id.quantity);
        btnadd = (Button) findViewById(R.id.btnAdd);
        clear = (Button) findViewById(R.id.btnClear);

        AddData();
        clear();


    }

    //add data to the database
    public void AddData() {
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean inserted = myDB.insertData(name.getText().toString(), description.getText().toString(), category.getText().toString(),
                        quantity.getText().toString());

                if (name.getText().toString().length()>0 & description.getText().toString().length()>0 &
                        category.getText().toString().length()>0 & quantity.getText().toString().length()>0){

                    if (inserted == true)
                        Toast.makeText(DbAdd.this, "Data inserted", Toast.LENGTH_LONG).show();

                    else
                        Toast.makeText(DbAdd.this, "Data not inserted", Toast.LENGTH_LONG).show();

                }
                else {Toast.makeText(DbAdd.this, "All fields require values", Toast.LENGTH_LONG).show();}

            }
        });

    }

    // clears all and edittext

    public void clear() {
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name.setText("");
                description.setText("");
                category.setText("");
                quantity.setText("");
            }
        });

    }

}