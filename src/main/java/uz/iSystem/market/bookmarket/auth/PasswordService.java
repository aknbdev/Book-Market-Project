package uz.iSystem.market.bookmarket.auth;

import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordService {

    public static String generateMD5(String password){
        MessageDigest md;
        try {

            md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
