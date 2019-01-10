import javax.crypto.*;
import java.security.*;

public class Main {
    static KeyPair key = null;

    public static void main(String []args){
        gen();
        byte[] enc = encrypt("lazizi abderrahmane you will be the best", key.getPublic());
        System.out.println(enc);
        System.out.println(new String(enc));
        System.out.println(decrypt(enc,key.getPrivate()));
        System.out.println("key publique : "+key.getPublic().getFormat()+" \nkey private : "+key.getPrivate().getFormat());


    }

    public static String gen(){
        KeyPairGenerator keygen = null;
        try {
            keygen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keygen.initialize(1024);
        key = keygen.generateKeyPair();



        return "";
    }

    public static byte[] encrypt(String message, PublicKey pk){
        Cipher cipher = null;
        byte[] chiffrement = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.ENCRYPT_MODE,pk);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            chiffrement = cipher.doFinal(message.getBytes());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return chiffrement;
    }
    public static String decrypt(byte[] chiffrement, PrivateKey sk){
        byte[] dec = null;
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }
        try {
            cipher.init(Cipher.DECRYPT_MODE,sk);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        try {
            dec = cipher.doFinal(chiffrement);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return new String(dec);


    }
}
