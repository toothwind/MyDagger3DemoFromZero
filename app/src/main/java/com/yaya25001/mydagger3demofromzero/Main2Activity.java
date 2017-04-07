package com.yaya25001.mydagger3demofromzero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.yaya25001.mydagger3demofromzero.di.components.DaggerStuComponent;
import com.yaya25001.mydagger3demofromzero.di.modules.StuModule;
import com.yaya25001.mydagger3demofromzero.di.qualifiers.StuForName123;
import com.yaya25001.mydagger3demofromzero.di.qualifiers.StuForName456;
import com.yaya25001.mydagger3demofromzero.model.Student;

import javax.inject.Inject;

public class Main2Activity extends AppCompatActivity {

//    @Inject
//    Person person;

//    @Named("123")
    @StuForName123
    @Inject
    Student student1;

//    @Named("456")
    @StuForName456
    @Inject
    Student student2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

//        MainComponent mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
//        mainComponent.inject(this);
//
//        Log.d("Main2Activity", "person:" + person);

        DaggerStuComponent.builder().stuModule(new StuModule()).build().inject(this);

    }
}
