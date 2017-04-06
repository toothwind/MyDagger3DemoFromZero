package com.yaya25001.mydagger3demofromzero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class Main2Activity extends AppCompatActivity {

//    @Inject
//    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        MainComponent mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
//        mainComponent.inject(this);
//
//        Log.d("Main2Activity", "person:" + person);

    }
}
