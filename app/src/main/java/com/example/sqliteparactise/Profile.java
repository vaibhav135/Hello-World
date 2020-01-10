package com.example.sqliteparactise;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ResourceCursorAdapter;

public class Profile extends AppCompatActivity {
    public static final int SPAN_EXCLUSIVE_EXCLUSIVE = 1;
    private EditText edtName , edtAge , edtGender , edtCourse , edtActivty , edtSociety ;
    private Button delete , update;
    Database db ;
    int deleteId = 0;
    String btnID = "update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        db = new Database(this);
        Cursor c = (Cursor) db.getAllInformation();
        ClientCursorAdapter clientCursorAdapter = new ClientCursorAdapter(Profile.this , c);
        init();
        DeleteButton();
        UpdateButton();
    }

    public void init(){

        edtName = (EditText)findViewById(R.id.getName);
        edtAge = (EditText)findViewById(R.id.getAge);
        edtGender = (EditText)findViewById(R.id.getGender);
        edtCourse = (EditText)findViewById(R.id.getCourse);
        edtActivty = (EditText)findViewById(R.id.getActivity);
        edtSociety = (EditText)findViewById(R.id.getSociety);
        delete = (Button)findViewById(R.id.deleteBtn);
        update = (Button)findViewById(R.id.btnUpdate);



    }

    public class ClientCursorAdapter extends ResourceCursorAdapter {

        public ClientCursorAdapter(Context context,  Cursor c) {
            super(context, R.layout.activity_profile ,c);

        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            edtName.setText(cursor.getString(cursor.getColumnIndex("Name")));
            edtAge.setText(cursor.getString(cursor.getColumnIndex("Age")));
            edtGender.setText(cursor.getString(cursor.getColumnIndex("Gender")));
            edtCourse.setText(cursor.getString(cursor.getColumnIndex("Course")));
            edtActivty.setText(cursor.getString(cursor.getColumnIndex("Activity")));
            edtSociety.setText(cursor.getString(cursor.getColumnIndex("Society")));


        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
            LayoutInflater li = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            return li.inflate(R.layout.activity_profile, viewGroup, false);
        }

    }


    public void DeleteButton(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteInformation(deleteId);
            }
        });
    }

    public void UpdateButton(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(Profile.this , MainActivity.class);
                in.putExtra(btnID,1);
                startActivity(in);
            }
        });
    }
}
