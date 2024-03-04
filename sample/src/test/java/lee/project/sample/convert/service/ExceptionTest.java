package lee.project.sample.convert.service;

public class ExceptionTest {

    public static void main(String[] args) {
        catchException();
    }

    public static void catchException() {
        try {
            System.out.println("i will catch");
            throwException();
        } catch (CustException cu) {
            System.out.println("customException");
            System.out.println(cu.getMessage());
            System.out.println(cu.getClass());
        } catch (Exception e) {
            System.out.println("Exception");
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        }
    }

    public static void throwException() throws Exception {
        throw new CustException("custom exception throw");
    }
}
