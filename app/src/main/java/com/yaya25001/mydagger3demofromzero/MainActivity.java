package com.yaya25001.mydagger3demofromzero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yaya25001.mydagger3demofromzero.di.components.DaggerMainComponent;
import com.yaya25001.mydagger3demofromzero.di.components.MainComponent;
import com.yaya25001.mydagger3demofromzero.di.modules.MainModule;
import com.yaya25001.mydagger3demofromzero.model.Person;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject  //要注入的对象
    Person person;

    private MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
        mainComponent.inject(this);
    }
}
