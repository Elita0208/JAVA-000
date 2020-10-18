import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderMain {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, InvocationTargetException {
        //类class的路径
        String classPath = "./Hello/Hello.xlass";

        MyClassLoader myClassLoader = new MyClassLoader(classPath);
        //类的全称
        String packageName = "Hello";

        //加载MyHello这个class文件
        Class<?> myhello = myClassLoader.loadClass(packageName);

        System.out.println("类加载器是:" + myhello.getClassLoader());

        //利用反射获取main方法
        Method method = myhello.getDeclaredMethod("hello");
        Object object = myhello.newInstance();
        method.invoke(object);
    }
}