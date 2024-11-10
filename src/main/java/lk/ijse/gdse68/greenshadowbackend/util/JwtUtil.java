package lk.ijse.gdse68.greenshadowbackend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lk.ijse.gdse68.greenshadowbackend.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//
@Component
@PropertySource(ignoreResourceNotFound = true,value = "classpath:otherprops.properties") //@value walin -token ekk hdnkot backend ekm security wela tiyenne token eken ekm login ekk natuw unik krgnn
// screen key ekk dagnn ==jwt.secret=e#s$p%o$r^t9 ==req walin en token ek crate wen ekk wenne
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = 234234523523L;
//token eke valued time ek masayk wage gnn
    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 12;

    @Value("${jwt.secret}")
    private String secretKey;

    // Retrieve username from jwt token
//token ek ewwam eken user name ek gnn ek token ek extrack krl
    public String getUsernameFromToken(String token){
        return getClaimFromToken(token,Claims::getSubject);
    }
//user roll ek gnn ek
    public Claims getUserRoleCodeFromToken(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    // Retrieve expiration date from jwt token
    //expira time ek gnn ek
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }


    public <T> T getClaimFromToken(String token, Function<Claims,T> claimResolver){
        final Claims claims =getAllClaimFromToken(token);
        return claimResolver.apply(claims);
    }
    //for retrieving any information from token
    //we will need the secret key

    private Claims getAllClaimFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }
    //expir d nadd kiyl bllnn
    private Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());

    }
    //generate token for user
//do genatate ekt pass krnwa
    public String generateToken(UserDTO userDTO){
        Map<String, Object> claims = new HashMap<>();
        claims.put("role",userDTO.getRole());
        return doGenerateToken(claims,userDTO.getEmail());
    }

    //while creating the token
    //1.define claims of the token,like Issuer ,expirate
    //2.sign the jwt using the HS512 algorithm and secrete

    private String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date (System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512,secretKey).compact(); //token ek hdn algorithm ek HS walin

    }
    //validate token =booling ekkk denwa token ek hrid kiyl
    //token ek mulinm validate krnwa ek valued nm withark bakend ekt danawa
    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


}
