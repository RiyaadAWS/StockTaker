package com.example.stocktaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.stocktaker.utility.DatabaseHelper;

public class DbDelete extends AppCompatActivity {

    DatabaseHelper myDB;
    EditText id;
    Button delete,drop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_delete);

        // DB helper connection
        myDB = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.editTextId);
        delete = (Button) findViewById(R.id.btnDelete);
        drop = (Button) findViewById(R.id.btnDeleteTable);
        DeleteData();
        DropTable();
    }

    public void DeleteData(){

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder myAlert = new AlertDialog.Builder(DbDelete.this);
                myAlert.setTitle("Confirm");
                myAlert.setMessage("Are you sure you want to delete this record?").setCancelable(true);
                if (id.getText().toString().length()>0){
                        myAlert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Integer deletedRow = myDB.deleteData(id.getText().toString());
                                if(deletedRow > 0 )  Toast.makeText(DbDelete.this, "Record deleted", Toast.LENGTH_LONG).show();

                                else Toast.makeText(DbDelete.this, "Record does not exist", Toast.LENGTH_LONG).show();
                            }
                        });

                        myAlert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                 Toast.makeText(DbDelete.this, "Record not deleted", Toast.LENGTH_LONG).show();
                             }
                        });
                        myAlert.show();
                }
                else {Toast.makeText(DbDelete.this, "Enter an ID", Toast.LENGTH_LONG).show();}
            }
        });
}

    public void DropTable(){
        drop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDB.dropTable();
                Toast.makeText(DbDelete.this, "Table Dropped", Toast.LENGTH_LONG).show();
            }
        });
    }
}