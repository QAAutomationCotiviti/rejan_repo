import org.testng.annotations.*;

public class Demo {
    @Test(description = "Test")
    public void test() {
        System.out.println("5.This is test");
    }


    @Test(priority = 1, description = "test method 1")
    public void testMethod1() {
        System.out.println("test method 1");
    }

    @Test(priority = 10, description = "test method 2")
    public void testMethod2() {
        System.out.println("test method 2");
    }
}
