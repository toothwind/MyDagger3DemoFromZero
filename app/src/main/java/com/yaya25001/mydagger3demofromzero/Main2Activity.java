package com.yaya25001.mydagger3demofromzero;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yaya25001.mydagger3demofromzero.di.components.DaggerStuComponent;
import com.yaya25001.mydagger3demofromzero.di.modules.StuModule;
import com.yaya25001.mydagger3demofromzero.di.qualifiers.StuForName123;
import com.yaya25001.mydagger3demofromzero.di.qualifiers.StuForName456;
import com.yaya25001.mydagger3demofromzero.model.Student;

import javax.inject.Inject;
import javax.inject.Provider;

import dagger.Lazy;

public class Main2Activity extends AppCompatActivity {

//    @Inject
//    Person person;

////    @Named("123")
//    @StuForName123
//    @Inject
//    Student student1;
//
////    @Named("456")
//    @StuForName456
//    @Inject
//    Student student2;

    @StuForName123
    @Inject
    Lazy<Student> lazyStudent;

    @StuForName456
    @Inject
    Provider<Student> providerStudent;


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

    public void lazy(View view){
        //该方法调用时 才会创建student 以后每次都用这个
        Student student = lazyStudent.get();
        Log.d("Main2Activity", "student:" + student);
    }
    public void provide(View view){
        //调用该方法才回去实例化stu 根据module方法 每次都重新加载 根据module的实现 可能相同 也可能不相同
        Student student = providerStudent.get();
        Log.d("Main2Activity", "student:" + student);

    }

}
