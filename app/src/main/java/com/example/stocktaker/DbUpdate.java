package com.example.stocktaker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stocktaker.utility.DatabaseHelper;

public class DbUpdate extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText name, description, category, quantity,id;
    Button btnUpdate, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_update);

        // DB helper connection
        myDB = new DatabaseHelper(this);

        name = (EditText) findViewById(R.id.name);
        description = (EditText) findViewById(R.id.description);
        category = (EditText) findViewById(R.id.category);
        quantity = (EditText) findViewById(R.id.quantity);
        id = (EditText)findViewById(R.id.id);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnClear = (Button)findViewById(R.id.btnClear);

        UpdateDBData();
        clear();
    }

    //add data to the database
        public void UpdateDBData(){
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isUpdated = myDB.updateDB(id.getText().toString(),name.getText().toString(),
                            description.getText().toString(),category.getText().toString(),quantity.getText().toString());

                    if (name.getText().toString().length()>0 & description.getText().toString().length()>0 &
                            category.getText().toString().length()>0 & quantity.getText().toString().length()>0
                            & id.getText().toString().length()>0){

                        if(isUpdated == true)
                            Toast.makeText(DbUpdate.this, "Data updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(DbUpdate.this, "Data not updated", Toast.LENGTH_LONG).show();
                    }
                    else {Toast.makeText(DbUpdate.this, "All fields require values", Toast.LENGTH_LONG).show();}
                }
            });
        }

    public void clear() {
        btnClear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name.setText("");
                description.setText("");
                category.setText("");
                quantity.setText("");
                id.setText("");
            }
        });
    }
}