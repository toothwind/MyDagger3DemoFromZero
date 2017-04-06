package com.yaya25001.mydagger3demofromzero;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.yaya25001.mydagger3demofromzero.di.components.DaggerMainComponent;
import com.yaya25001.mydagger3demofromzero.di.components.MainComponent;
import com.yaya25001.mydagger3demofromzero.di.modules.MainModule;
import com.yaya25001.mydagger3demofromzero.model.Person;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity {

    @Inject
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        MainComponent mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        mainComponent.inject(this);

        Log.d("Main2Activity", "person:" + person);

    }
}
