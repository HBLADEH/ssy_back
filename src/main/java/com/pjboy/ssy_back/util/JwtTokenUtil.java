package com.pjboy.ssy_back.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: ssy_back
 * @description: jwt 自动加载工具类
 * @author: BLADE
 * @create: 2020-10-24 12:28
 **/
@Data
@ConfigurationProperties(prefix = "jwt")
@Component
public class JwtTokenUtil {
  private String secret;
  private Long expiration;
  private String header;

  /**
  * @Description: 生成 token 令牌
  * @Param: [userDetails]
  * @return: java.lang.String
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>(2);
    claims.put("sub", userDetails.getUsername());
    claims.put("created", new Date());
    return generateToken(claims);
  }

  /**
  * @Description: 从令牌中获取用户名
  * @Param: [token]
  * @return: 用户名
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  public String getUsernameFromToken(String token) {
    String username;
    try {
      Claims claims = getClaimsFromToken(token);
      username = claims.getSubject();
    } catch (Exception e) {
      username = null;
    }
    return username;
  }

  /**
  * @Description: 判断令牌是否过期
  * @Param: token 令牌
  * @return: 是否过期
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  public Boolean isTokenExpired(String token) {
    try {
      Claims claims = getClaimsFromToken(token);
      Date expiration = claims.getExpiration();
      return expiration.before(new Date());
    } catch (Exception e) {
      return false;
    }
  }

  /**
  * @Description: 刷新令牌
  * @Param: token 令牌
  * @return: 新令牌
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  public String refreshToken(String token) {
    String refreshedToken;
    try {
      Claims claims = getClaimsFromToken(token);
      claims.put("created",new Date());
      refreshedToken = generateToken(claims);
    } catch (Exception e) {
      refreshedToken = null;
    }
    return refreshedToken;
  }
  
  /**
  * @Description: 验证令牌
  * @Param: [token, userDetails]
  * @return: 是否有效
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  public Boolean validateToken(String token, UserDetails userDetails) {
    String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

  }

  /**
  * @Description: 根据 claims 生成令牌
  * @Param: claims
  * @return: 令牌
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  private String generateToken(Map<String, Object> claims) {
    byte[] keyBytes = Decoders.BASE64.decode(secret);
    Key key = Keys.hmacShaKeyFor(keyBytes);
    return Jwts.builder().setClaims(claims).signWith(key).compact();
    //Date expirationDate = new Date(System.currentTimeMillis() + expiration);
    //return Jwts.builder().setClaims(claims)
    //        .setExpiration(expirationDate)
    //        .signWith()
    //        .compact();
  }

  /**
  * @Description: 从令牌获取数据声明
  * @Param: token 令牌
  * @return: 数据声明
  * @Author: BLADE
  * @Date: 2020/10/24
  */
  private Claims getClaimsFromToken(String token) {
    //Claims claims;
    //try {
    //  claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    //} catch (Exception e) {
    //  claims = null;
    //}
    //return claims;
    Claims claims;
    try {
      claims = Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    } catch (Exception e) {
      claims = null;
    }
    return claims;
  }

}
