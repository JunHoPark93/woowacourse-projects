package next.reflection;

public class Junit4Test {
    @MyTest
    public void one() throws Exception {
        System.out.println("Running Test1");
    }

    @MyTest
    public void two() throws Exception {
        System.out.println("Running Test2");
    }

    public void testThree() throws Exception {
        System.out.println("Running Test3");
    }
}
