package com.example.sibin.getaway;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;



public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        TextView ver = (TextView)findViewById(R.id.tv2);
        TextView abt = (TextView)findViewById(R.id.tv3);
        ver.setText(R.string.ver);
        abt.setText(R.string.about_app);
    }
}
