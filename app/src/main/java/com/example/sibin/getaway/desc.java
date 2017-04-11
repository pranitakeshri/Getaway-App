package com.example.sibin.getaway;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.widget.ImageView;
import android.widget.TextView;
import android.support.design.widget.CollapsingToolbarLayout;

import com.squareup.picasso.Picasso;


public class desc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desc);
        Toolbar toolbar=(Toolbar)findViewById(R.id.tb);
        setSupportActionBar(toolbar);
        String des = getIntent().getStringExtra("des");
        String name = getIntent().getStringExtra("name");
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.ct);
        collapsingToolbarLayout.setTitle(name);
        String url = "https://raw.githubusercontent.com/SibinJ/getaway/master/images/";
        name = name.replaceAll("\\s+","%20");
        url = url.concat(name);
        url = url.concat(".jpg");
        ImageView imageView=(ImageView)findViewById(R.id.iv);
        Picasso.with(desc.this).load(url).into(imageView);





        TextView v2 = (TextView) findViewById(R.id.tv2);
        v2.setText(des);


    }
}

