
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Encryption {

    static Cipher cipher;
    static KeyGenerator keyGen;
    static SecretKey key;
    static byte[] cipherText;
    
    public static byte[] encrypt(byte[] encryptedMessage) throws Exception {
    
        keyGen = KeyGenerator.getInstance("AES"); 
        keyGen.init(128);
        key = keyGen.generateKey();
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipherText = cipher.doFinal(encryptedMessage);
    }

    public static void decrypt() throws Exception {
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decipheredBytes = cipher.doFinal(cipherText);
        String decipheredText = new String(decipheredBytes);

    }
}
