package com.yaya25001.mydagger3demofromzero;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yaya25001.mydagger3demofromzero.di.components.DaggerMainComponent;
import com.yaya25001.mydagger3demofromzero.di.components.MainComponent;
import com.yaya25001.mydagger3demofromzero.di.modules.MainModule;
import com.yaya25001.mydagger3demofromzero.model.Person;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject  //要注入的对象
    Person person;

    @Inject
    Person person2;

    private MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
        mainComponent.inject(this);

        //打印两个对象的内存地址
        Log.d("MainActivity", "person:" + person.toString());
        Log.d("MainActivity", "person2:" + person2.toString());

    }

    public void open(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }

}
