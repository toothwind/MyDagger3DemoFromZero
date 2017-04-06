### 1.dagger2 是google的 依赖注入框架

### 2.导入
    在project的build.gradle配置:
     dependencies {
            classpath 'com.android.tools.build:gradle:2.2.2'
            // 添加android-apt 插件
            classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

            // NOTE: Do not place your application dependencies here; they belong
            // in the individual module build.gradle files
        }
    在app的build.gradle配置:
    // 应用插件
    apply plugin: 'com.neenbedankt.android-apt'
    依赖:
    // dagger 2 的配置
    compile 'com.google.dagger:dagger:2.4'
    apt 'com.google.dagger:dagger-compiler:2.4'
    compile 'org.glassfish:javax.annotation:10.0-b28'// 添加java 注解库

### 3.初步试用:
    创建包:di  dependency injection 依赖注入
    内 创建 : components modules scopes (qualifiers)
    (1).创建实体类 Person
    public class Person {

        public Person() {
            Log.d("Person", "person  被创建了~");
        }
    }
    (2).创建MainModule 提供依赖对象的工厂类
    @Module //提供依赖对象的实例 --> 工厂类
    public class MainModule {

        @Provides //标明此方法提供依赖对象
        Person providePerson(){

            return new Person();
        }

    }
    (3).创建沟通部分 类库和调用者
    @Component(modules = {MainModule.class}) //沟通部分  调用者和依赖的对象库
    public interface MainComponent {

        //定义注入的方法
        void inject(MainActivity mainActivity);

    }
    (4).在MainActivity中调用
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
    (5).需要运行编译一下 才会有    DaggerMainComponent生成
    运行结果:
    D/Person: person  被创建了~




#### 其他:
        参考:
        1.http://blog.csdn.net/lisdye2/article/details/51887402
        2.http://blog.csdn.net/lisdye2/article/details/51942511