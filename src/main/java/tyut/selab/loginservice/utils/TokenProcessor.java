package tyut.selab.loginservice.utils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;
public class TokenProcessor {
    //饿汉式创建Token
    private TokenProcessor(){
    }
    private static final TokenProcessor instance = new TokenProcessor();
    public static TokenProcessor getInstance() {
        return instance;
    }
    /**
     * 生成Token
     * @return
     */
    public String makeToken() {
        String token = (System.currentTimeMillis() + new Random().nextInt(999999999)) + "";
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] =  md.digest(token.getBytes());
//            BASE64Encoder encoder = new BASE64Encoder();
            Base64.Encoder encoder = Base64.getEncoder();
//            return encoder.encode(md5);
            return encoder.encodeToString(md5);
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;//获取Token失败返回nullimport java.security.MessageDigest;
    }
}