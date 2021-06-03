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
    Button delete,drop,clear,view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_delete);

        // DB helper connection
        myDB = new DatabaseHelper(this);
        id = (EditText) findViewById(R.id.editTextId);
        delete = (Button) findViewById(R.id.btnDelete);
        drop = (Button) findViewById(R.id.btnDeleteTable);
        clear = (Button) findViewById(R.id.btnClear);
        view = (Button) findViewById(R.id.btnView);
        DeleteData();
        DropTable();
        viewAll();
        clear();
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

    public void showMessage (String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
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

    public void clear() {
        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                id.setText("");
            }
        });
    }
}