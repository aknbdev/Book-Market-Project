package uz.iSystem.market.bookmarket.util;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import uz.iSystem.market.bookmarket.user.User;

import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${secret.key}")
    private String secretKey;

    @Value("${jwt.issuer}")
    private String jwtIssuer;

    public String generateToken(User user){

        JwtBuilder jwtBuilder = Jwts.builder();

        jwtBuilder.setId(String.valueOf(user.getId()));
        jwtBuilder.setIssuedAt(new Date());
        jwtBuilder.setSubject(String.format("%s#%S" , user.getId(), user.getContact()));
        jwtBuilder.signWith(SignatureAlgorithm.HS256, secretKey);
        jwtBuilder.setExpiration(new Date(System.currentTimeMillis() + (24*60*60*1000)));
        jwtBuilder.setIssuer(jwtIssuer);
        return jwtBuilder.compact();
    }

    public String getPhone(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split("#")[1];
    }

    public String getId(String token){

        Claims claims = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject().split("#")[0];
    }

    public Boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
