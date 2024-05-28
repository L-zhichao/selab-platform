package tyut.selab.loginservice.utils;

import com.alibaba.druid.util.StringUtils;
import io.jsonwebtoken.*;

import java.util.Date;

/**
 * Classname: JwtHelperUtils
 * Description:
 *
 * @Author tt
 * @Creat 2024/5/22 20:43
 * @Version 17
 */
public class JwtHelperUtils {
    private static long tokenExpiration = 24*60*60*1000;
    private static String tokenSignKey = "123456";
    //生成token字符串
    public static String createToken(Integer userId, String username, Integer groupId, Integer roleId) {
        String token = Jwts.builder()
                .setSubject("YYGH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("userName", username)
                .claim("groupId", groupId)
                .claim("roleId", roleId)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    //从token字符串获取username
    public static String getUsername(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        String username = (String)claims.get("userName");
        return username;
    }
    //从token字符串获取userId
    public static Integer getUserId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer userId = (Integer) claims.get("userId");
        return userId;
    }
    //从token字符串获取groupId
    public static Integer getGroupId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer groupId = (Integer) claims.get("groupId");
        return groupId;
    }
    //从token字符串获取roleId
    public static Integer getRoleId(String token) {
        if(StringUtils.isEmpty(token)) return null;
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        Integer roleId = (Integer) claims.get("roleId");
        return roleId;
    }


    //判断token是否有效
    public static boolean isExpiration(String token){
        try {
            boolean isExpire = Jwts.parser()
                    .setSigningKey(tokenSignKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getExpiration().before(new Date());
            //没有过期，有效，返回false
            return isExpire;
        }catch(Exception e) {
            //过期出现异常，返回true
            return true;
        }
    }
}
