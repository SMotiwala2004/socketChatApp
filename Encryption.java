
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encryption {

    static Cipher cipher;
    static KeyGenerator keyGen;
    static SecretKey key;
    static byte[] cipherText;
    
    public static void encrypt(String message) throws Exception {
    
        KeyGenerator.getInstance("AES"); 
        keyGen.init(128);
        keyGen.generateKey();
        Cipher.getInstance("AES");
        cipher.init(cipher.ENCRYPT_MODE, key);
        cipherText = cipher.doFinal(message.getBytes());
    }

    public static void decrypt() throws Exception {

        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decipherText = cipher.doFinal(cipherText);

    }
}
