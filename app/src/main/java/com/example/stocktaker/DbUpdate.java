package com.example.stocktaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

    // Update data to the database - this method reads the updateDB method in the DatabaseHelper class which takes the parameters from the
    // EditText fields which updates there corresponding rows in the database
        public void UpdateDBData(){
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder myAlert = new AlertDialog.Builder(DbUpdate.this);
                    myAlert.setTitle("Confirm");
                    myAlert.setMessage("Are you sure you want to update this record?").setCancelable(true);
                    if (name.getText().toString().length()>0 & description.getText().toString().length()>0 &
                            category.getText().toString().length()>0 & quantity.getText().toString().length()>0
                            & id.getText().toString().length()>0){
                        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                boolean isUpdated = myDB.updateDB(id.getText().toString(),name.getText().toString(),
                                        description.getText().toString(),category.getText().toString(),quantity.getText().toString());

                                if(isUpdated == true)
                                    Toast.makeText(DbUpdate.this, "Record updated", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(DbUpdate.this, "Record not updated", Toast.LENGTH_LONG).show();
                            }
                        });

                        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(DbUpdate.this, "Record not updated", Toast.LENGTH_LONG).show();
                            }
                        });
                        myAlert.show();
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