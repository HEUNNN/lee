package lee.project.wordfilter.util;

public class ConvertUtil {
    public static String convertByteArrToString(byte[] bytes) {
        if (bytes != null) {
            return new String(bytes);
        }
        return "";
    }
}
