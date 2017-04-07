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

### 4.变通:
    注释掉providePerson
    @Module //提供依赖对象的实例 --> 工厂类
    public class MainModule {
    /*

        @Provides //标明此方法提供依赖对象
        Person providePerson(){

            return new Person();
        }
    */

    }
    给Person构造方法 添加注解@Inject
    public class Person {

        @Inject
        public Person() {
            Log.d("Person", "person  被创建了~");
        }
    }
    运行结果:
    D/Person: person  被创建了~
    逻辑分析:
    先判断module中是否有提供该对象的实例化方法
    |-->有 则返回,结束
    |-->没有 则找到该类的构造方法,是否带有@Inject

### 5.Singleton注解 单例
    假如对同一个对象 需要注入两次,打印 内存地址:
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

            mainComponent = DaggerMainComponent.builder().mainModule(new MainModule()).build();
            mainComponent.inject(this);

            //打印两个对象的内存地址
            Log.d("MainActivity", "person:" + person.toString());
            Log.d("MainActivity", "person2:" + person2.toString());

        }
    }
    运行结果:
     D/Person: person  被创建了~
     D/Person: person  被创建了~
     D/MainActivity: person:com.yaya25001.mydagger3demofromzero.model.Person@230bb5
     D/MainActivity: person2:com.yaya25001.mydagger3demofromzero.model.Person@ca760
    创建了两个person
    ---->
    给providePerson方法添加注解
    @Provides @Singleton
            //标明此方法提供依赖对象
        Person providePerson(){

            return new Person();
        }
    同时也要给MainComponent添加注解
    @Singleton
    @Component(modules = {MainModule.class}) //沟通部分  调用者和依赖的对象库
    public interface MainComponent {

        //定义注入的方法
        void inject(MainActivity mainActivity);

    }
    运行结果:
    D/Person: person  被创建了~
    D/MainActivity: person:com.yaya25001.mydagger3demofromzero.model.Person@230bb552
    D/MainActivity: person2:com.yaya25001.mydagger3demofromzero.model.Person@230bb552
    新建activity
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
    MainActivity添加
    public void open(View view) {
        startActivity(new Intent(this,Main2Activity.class));
    }
    component添加inject
    @Singleton
    @Component(modules = {MainModule.class}) //沟通部分  调用者和依赖的对象库
    public interface MainComponent {

        //定义注入的方法
        void inject(MainActivity mainActivity);

        //定义注入的方法
        void inject(Main2Activity mainActivity);

    }
    点击打开main2activity
    输出:
    D/Person: person  被创建了~
    D/MainActivity: person:com.yaya25001.mydagger3demofromzero.model.Person@21391726
    D/MainActivity: person2:com.yaya25001.mydagger3demofromzero.model.Person@21391726
    D/Person: person  被创建了~
    D/Main2Activity: person:com.yaya25001.mydagger3demofromzero.model.Person@1b46a637
    可知结果:@singleton只对一个Component有效,即其单例所依赖的Component
    ok..

### 6.需要参数的构造方法
    现在Person需要参数
    public class Person {

        private Context context;

        //    @Inject
        public Person(Context context) {
            this.context = context;
            Log.d("Person", "person  被创建了~");
        }
    }
    对与=应修改module
    @Module //提供依赖对象的实例 --> 工厂类
    public class MainModule {

        private Context context;

        public MainModule(Context context) {
            this.context = context;
        }

        @Provides //提供 上下文
        Context provideContext(){

            return context;
        }

        @Provides @Singleton
            //标明此方法提供依赖对象
        Person providePerson(Context context){

            return new Person(context);
        }

    }
    MainActivity 修改 传入context
    mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
    mainComponent.inject(this);
    注意:providePerson 添加context 添加了provideContext来提供context对象
    过程:
    ##### (1)根据@Inject注解 查找需要依赖注入的对象
    ##### (2)根据的MainModule的方法返回值,找到方法providePerson,
    ##### (3)发现 需要一个参数context,找到module具有返回context的方法provideContext
    ##### (4)完成初始化
    添加 provideContext 是为了解耦 假如获取context的方法发生了变化 还需要修改providePerson方法
    ok...
    注意 同一个方法 不要返回值和传入参数一样,会导致递归死循环

### 7.依赖dependents 一个组件Component

    现在创建一个组件AppComponent 提供context
    @Component(modules = AppModule.class)
    public interface AppComponent {

        //向下层提供context
        Context proContext();

    }
    @Module
    public class AppModule {

        private Context context;

        public AppModule(Context context) {
            this.context = context;
        }

        //这里提供context
        @Provides
        Context provideContext(){
            return context;
        }

    }
    在另一个module需要context  添加dependencies = AppComponent.class
    @Module
    public class ActivityModule {

        @Provides
        Person providePerson(Context context){
            return new Person(context);
        }

    }
    @Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
    public interface ActivityComponent {

        //子component
        void inject(MainActivity mainActivity);

    }
    MainActivity中
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     // mainComponent = DaggerMainComponent.builder().mainModule(new MainModule(this)).build();
     // mainComponent.inject(this);

        //依赖的对象 component
        AppComponent app = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        DaggerActivityComponent.builder().appComponent(app).activityModule(new ActivityModule()).build().inject(this);

        //打印两个对象的内存地址
        Log.d("MainActivity", "person:" + person.toString());
        Log.d("MainActivity", "person2:" + person2.toString());

    }
    注意:
    父Component需要添加提供对象的接口
    子component注解添加dependencies

### 8.@qualifier标记  @Named
#### 8.1 @Named
    //TODO
    相同的inject(MainActivity mainActivity) 报错...
    //新创建
    bean类:
    public class Student {

        private String name;

        public Student(String name) {
            this.name = name;
            Log.d("dagger", "创建==name=" + name);
        }
    }
    component...
    @Component(modules = {StuModule.class})
    public interface StuComponent {

        void inject(Main2Activity mainActivity);

    }
    module...
    @Module
    public class StuModule {

        @Named("123")
        @Provides
        Student provideStudent(){
            return new Student("123");
        }

        @Named("456")
        @Provides
        Student provideStudent2(){
            return new Student("456");
        }

    }
    调...
    public class Main2Activity extends AppCompatActivity {

    //    @Inject
    //    Person person;

        @Named("123")
        @Inject
        Student student1;

        @Named("456")
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
    运行结果:
    D/dagger: 创建==name=123
    D/dagger: 创建==name=456

#### 8.1 @qualifier
    新创建;俩qualifier
    1.
    @Qualifier //关键词
    @Retention(RetentionPolicy.RUNTIME)
    public @interface StuForName123 {
    }
    2.
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface StuForName456 {
    }
    修改Module
    @Module
    public class StuModule {

    //    @Named("123")
        @StuForName123
        @Provides
        Student provideStudent(){
            return new Student("123");
        }

    //    @Named("456")
        @StuForName456
        @Provides
        Student provideStudent2(){
            return new Student("456");
        }

    }
    使用修改:
    //    @Named("123")
        @StuForName123
        @Inject
        Student student1;

    //    @Named("456")
        @StuForName456
        @Inject
        Student student2;
    运行结果:
    D/dagger: 创建==name=123
    D/dagger: 创建==name=456

### 9. scope 对于singleton 是scope的一个实现
    singleton源码:
    @Scope
    @Documented
    @Retention(RUNTIME)
    public @interface Singleton {}
    全局生命周期:
    @Scope //模仿 单例singleton
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PerApp {
    }
    application:
    public class MyApp extends Application {

        //AppComponent 对应着app的生命周期
        public static AppComponent appComponent;

        @Override
        public void onCreate() {
            super.onCreate();
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
        }
    }
    对应的module
    //这里提供context
    @Provides
    @PerApp
    //添加该标记标明 该方法只产生一个 实例
    Context provideContext(){
        return context;
    }
    对应的component
    @PerApp //module添加该标记 此处也要加
    @Component(modules = AppModule.class)
    public interface AppComponent {

        //向下层提供context
        Context proContext();

    }
    activity生命周期
    @Scope
    @Documented
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PerActivity {
    }
    module..
    @Module
    public class ActivityModule {

        @PerActivity
        @Provides
        Person providePerson(Context context){
            return new Person(context);
        }

    }
    component....
    @PerActivity
    @Component(dependencies = AppComponent.class,modules = {ActivityModule.class})
    public interface ActivityComponent {

        //子component
        void inject(MainActivity mainActivity);

    }
    运行 正常



#### 其他:
        参考:
        1.http://blog.csdn.net/lisdye2/article/details/51887402
        2.http://blog.csdn.net/lisdye2/article/details/51942511