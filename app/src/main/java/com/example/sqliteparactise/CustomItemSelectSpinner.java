package com.example.sqliteparactise;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class CustomItemSelectSpinner {
    String  customAct , customCourse , customSociety;
    public  class CustomItemSelectCourse implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(adapterView.getContext() , adapterView.getItemAtPosition(i).toString() , Toast.LENGTH_LONG).show();
            customCourse = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class CustomItemSelectActivity implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(adapterView.getContext() , adapterView.getItemAtPosition(i).toString() , Toast.LENGTH_LONG).show();
            customAct = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    public class CustomItemSelectSociety implements AdapterView.OnItemSelectedListener {

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //Toast.makeText(adapterView.getContext() , adapterView.getItemAtPosition(i).toString() , Toast.LENGTH_LONG).show();
            customSociety = adapterView.getItemAtPosition(i).toString();
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }



    public String getCustomCourse(){
        return customCourse;
    }

    public String getCustomAct(){
        return customAct;
    }

    public String getCustomSociety(){
        return customSociety;
    }

    /* public void setCustom(String cou , String act , String soc){
        cou = this.customCourse;
        act = this.customAct;
        soc = this.customSociety;
    } */
}
