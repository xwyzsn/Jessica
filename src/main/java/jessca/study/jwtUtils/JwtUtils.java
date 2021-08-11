package jessca.study.jwtUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
@Data
@Component
@ConfigurationProperties(prefix = "xwyzsn.jwt")
public class JwtUtils {
    //生成jwt
    private long expire ;
    private String secret;
    private String header;
    public String generateToken(String name){
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime()+1000*expire);
        return     Jwts.builder().setHeaderParam("typ","JWT")
                .setSubject(name)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    //解析jwt
    public Claims getClaimByToken(String jwt){
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (Exception e) {
            return null;
        }

    }
    //判断Jwt是否过期

    public boolean isTokenExpired (Claims claims){
        return claims.getExpiration().before(new Date());
    }


}
