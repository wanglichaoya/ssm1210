package test20190903;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/3
 * Time: 下午 03:43
 * Description:
 */
public class Test02 implements TestInterface {


    @Override
    public String sayHello() {
        return null;
    }

    @Override
    public void hello() {

    }

    public void testListAndSet() {
        // List list
        //Set
        //Map
        String a = "helloworld hhahah";
        System.out.println("字符串的长度为： " + a.length());
        Set<String> set = new HashSet<String>();
        set.add("abc");
        set.add("abc");
        // Iterator<String> iterator = set.iterator()

    }

    public static void main(String[] args) {
        Test02 test02 = new Test02();
        test02.hello();
        test02.testListAndSet();

    }
}
