package lee.project.Connect.util;

public class ByteUtils {

    public static String convertByteToString(byte[] byteArr) {
        String result;
        if (byteArr == null) {
            result = "";
        } else {
            result = new String(byteArr);
        }
        return result;
    }
}
