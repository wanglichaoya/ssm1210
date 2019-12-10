package test20190903;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/3
 * Time: 下午 03:09
 * Description: BaseTest
 */
public class Test01 {

    public void TestHashMapAndHashTable() {
      /*Hashtable<String,Object> hashtable = new Hashtable<String,Object>();
        hashtable.put("testNull",null);*/
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("testNull2", null);
    }


    public static void main(String[] args) {
        Test01 test01 = new Test01();
        test01.TestHashMapAndHashTable();
        //Error
        // Exception
    }
}
