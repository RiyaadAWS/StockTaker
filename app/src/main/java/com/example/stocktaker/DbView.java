package com.example.stocktaker;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.stocktaker.utility.DatabaseHelper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class DbView extends AppCompatActivity {
    DatabaseHelper myDB;
    Button view,viewInStock,viewOutStock, createPDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_db_view);
        myDB = new DatabaseHelper(this);

        ActivityCompat.requestPermissions(this, new String[] {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE}, PERMISSION_GRANTED);

        viewInStock = (Button) findViewById(R.id.btnInstock);
        viewOutStock = (Button)findViewById(R.id.btnNoStock);
        view = (Button)findViewById(R.id.btnView);
        createPDF = (Button) findViewById(R.id.btnPDF);
        viewAll();
        inStock();
        outStock();
        try {
            printPDF();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    public  void CreatePDF(){
        createPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor res = myDB.printPDF();

                try {
                    res.moveToFirst();
                }
                catch (Exception e){
                    e.printStackTrace();
                    return;
                }
                PdfDocument pdfDocument = new PdfDocument();
                PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600,1).create();
                PdfDocument.Page page = pdfDocument.startPage(pageInfo);
                page.getCanvas().drawText(res.getString(0),10, 25, new Paint());
                pdfDocument.finishPage(page);
                String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/" + myDB.getDatabaseName().toString() + ".pdf";
                File file = new File(filePath);
                try {
                    pdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                pdfDocument.close();
            }
        });

    }

    public void printPDF() throws IOException {

        createPDF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PdfDocument myPdfDocument = new PdfDocument();
                Paint myPaint = new Paint();

                Cursor cursor = myDB.printPDF();

                PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(1000,2000,1).create();
                PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);
                Canvas canvas = myPage.getCanvas();

                myPaint.setTextSize(80);
                canvas.drawText("Stock Management", 30,80, myPaint);

                myPaint.setTextSize(30);
                canvas.drawText("Stock Management Database Below:", 30,140, myPaint);

                myPaint.setColor(Color.rgb(150,150,150));
                canvas.drawRect(30,150,canvas.getWidth()-30,160,myPaint);

                myPaint.setColor(Color.BLACK);
                canvas.drawText("ID: ",30, 200,myPaint);

                myPaint.setColor(Color.BLACK);
                canvas.drawText("Name: ",180, 200,myPaint);

                myPaint.setColor(Color.BLACK);
                canvas.drawText("Description: ", 400, 200,myPaint);

                myPaint.setColor(Color.BLACK);
                canvas.drawText("Category: ", 600, 200,myPaint);

                myPaint.setColor(Color.BLACK);
                canvas.drawText("Quantity: ", 800, 200,myPaint);

                try {

                    int y = 250;
                    int i = 50;

                    while (cursor.moveToNext()) {

                        do {
                            y = y+i;
                            canvas.drawText(cursor.getString(0), 30, y, myPaint);
                            canvas.drawText(cursor.getString(1), 180, y, myPaint);
                            canvas.drawText(cursor.getString(2), 400, y, myPaint);
                            canvas.drawText(cursor.getString(3), 600, y, myPaint);
                            canvas.drawText(cursor.getString(4), 800, y, myPaint);
                        }
                        while (cursor.moveToNext());
                        }
                }

                catch (Exception e){}

                myPdfDocument.finishPage(myPage);
                String filePath = Environment.getExternalStorageDirectory().getPath()+"/Download/" + myDB.getDatabaseName()+ ".pdf";
                File file = new File(filePath);

                try {
                    myPdfDocument.writeTo(new FileOutputStream(file));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                myPdfDocument.close();
                Toast.makeText(DbView.this, "PDF Created", Toast.LENGTH_LONG).show();

            }
        });

    }
}
