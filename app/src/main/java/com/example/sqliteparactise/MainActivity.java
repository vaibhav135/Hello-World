package com.example.sqliteparactise;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name , age ;
    private RadioGroup rg;
    //private RadioButton m , f , o;
    private Spinner course , act, soc ;
    private Button btn ;
    String selectedActivity , selectedCourse , selectedSociety  , gender;
    CustomItemSelectSpinner customItemSelectSpinner;
    private Database db;
    String update  ;

    private static final String TAG = "MainActivity";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        customItemSelectSpinner = new CustomItemSelectSpinner() ;
        init(); initArrayAdapters();
        radio();
        db = new Database(this);
        button();
        Intent i = getIntent();
        update = i.getStringExtra("btnId");

    }

     public void init(){

        name = (EditText)findViewById(R.id.getName);
        age = (EditText)findViewById(R.id.getAge);
        rg = (RadioGroup)findViewById(R.id.radioGroup);
        course = (Spinner)findViewById(R.id.Edtcourse);
        act = (Spinner)findViewById(R.id.EdtActivity);
        soc = (Spinner)findViewById(R.id.EdtSociety);
        btn = (Button)findViewById(R.id.deleteBtn);

     }

     public void initArrayAdapters(){
         List<String> courses = new ArrayList<>();
         courses.add("BSc Maths");
         courses.add("BA prog.");
         courses.add("BA (hons)");
         courses.add("BSc hons comp science ");
         courses.add("BSc Chemistry");
         courses.add("BSc physics ");
         courses.add("BCom");
         courses.add("BCom (Hons)");


         List<String> activity = new ArrayList<>();
         activity.add("swimming");
         activity.add("tennis");
         activity.add("cricket");
         activity.add("Taekwondo");
         activity.add("craft");
         activity.add("camping");
         activity.add("cycling");

         List<String> society = new ArrayList<>();
         society.add("Drama");
         society.add("Theatre");
         society.add("Debate");
         society.add("Sports");
         society.add("Commercium");
         society.add("Tech");
         society.add("Arts");

         ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item , courses);
         arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         course.setAdapter(arrayAdapter);

         ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item , activity);
         arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         act.setAdapter(arrayAdapter2);

         ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item , society);
         arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
         soc.setAdapter(arrayAdapter3);

         course.setOnItemSelectedListener(customItemSelectSpinner.new CustomItemSelectCourse());
         act.setOnItemSelectedListener(customItemSelectSpinner.new CustomItemSelectActivity());
         soc.setOnItemSelectedListener(customItemSelectSpinner.new CustomItemSelectSociety());

         selectedCourse = customItemSelectSpinner.getCustomCourse();
         selectedActivity = customItemSelectSpinner.getCustomAct();
         selectedSociety = customItemSelectSpinner.getCustomSociety();

     }

     public void radio() {
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                RadioButton rb = (RadioButton) radioGroup.findViewById(checkedId);
                if(null != rb && checkedId > -1){
                    Toast.makeText(getApplicationContext() , rb.getText() , Toast.LENGTH_LONG).show();
                    gender = rb.getText().toString();
                }
            }
        });
     }


     public void button(){
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(update == null) {
                  Log.d(TAG, "onClick: something happened here");
                  db.insertValues(name.getText().toString(), age.getText().toString(), gender, selectedCourse, selectedActivity, selectedSociety);
                  startActivity(new Intent(MainActivity.this, Profile.class));
              }
              else{
                  btn.setText("update");
                  db.UpdateInformation(1 , name.getText().toString() , age.getText().toString() , gender , selectedCourse , selectedActivity , selectedSociety );
              }

            }
        });
     }

}
