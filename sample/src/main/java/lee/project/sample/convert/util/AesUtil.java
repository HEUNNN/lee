package lee.project.sample.convert.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Base64;
import java.util.Date;

public class AesUtil {
    private static final String PRIVATE_KEY_AES_256 = "hellowordhelloword23114helloword"; // 32bit

    public static String encrypt(String plainText) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(PRIVATE_KEY_AES_256.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParam = new IvParameterSpec(PRIVATE_KEY_AES_256.substring(0, 16).getBytes());

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParam);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptString) throws Exception {
        byte[] encryptBytes = Base64.getDecoder().decode(encryptString);
        SecretKeySpec secretKey = new SecretKeySpec(PRIVATE_KEY_AES_256.getBytes(StandardCharsets.UTF_8), "AES");
        IvParameterSpec ivParam = new IvParameterSpec(PRIVATE_KEY_AES_256.substring(0, 16).getBytes());

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParam);

        byte[] decryptBytes = cipher.doFinal(encryptBytes);

        return new String(decryptBytes);
    }
}
