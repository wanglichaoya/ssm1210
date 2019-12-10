package test20190903;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2019/9/3
 * Time: 下午 03:35
 * Description:
 */
public interface TestInterface extends TestInterface02 {

    default public void hello() {
        System.out.println("您好");
    }
}
