
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class Encryption {

    static Cipher cipher;
    static KeyGenerator keyGen;
    static SecretKey key;
    static byte[] iv;
    
    public static byte[] encrypt(byte[] encryptedMessage) throws Exception {
    
        keyGen = KeyGenerator.getInstance("AES"); 
        keyGen.init(128);
        key = keyGen.generateKey();
        cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        iv = cipher.getIV();
        byte[] cipherText = cipher.doFinal(encryptedMessage);
        return cipherText;
    }

    public static String decrypt(String encryptedMessage) throws Exception {
        byte[] messageInBytes = Base64.getDecoder().decode(encryptedMessage);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(128, iv);
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);

    }
}
