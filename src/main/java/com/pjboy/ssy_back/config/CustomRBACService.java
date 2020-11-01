package com.pjboy.ssy_back.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @program: ssy_back
 * @description: 动态资源鉴权规则
 * @author: BLADE
 * @create: 2020-11-01 08:22
 **/
@Component("rbacService")
public class CustomRBACService {
  private AntPathMatcher antPathMatcher = new AntPathMatcher();
  
  /**
  * @Description: 判断某用户是否有该 request 资源的访问权限
  * @Param: [request, authentication]
  * @return: boolean
  * @Author: BLADE
  * @Date: 2020/11/1
  */
  public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
    Object principal = authentication.getPrincipal();
    if (principal instanceof UserDetails) {
      UserDetails userDetails = (UserDetails) principal;
      List<GrantedAuthority> authorityList =
              AuthorityUtils.commaSeparatedStringToAuthorityList(request.getRequestURI());
      return userDetails.getAuthorities().contains(authorityList.get(0));
    }
    return false;
  }

}
