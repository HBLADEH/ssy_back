package com.pjboy.ssy_back.config.filter;

import com.pjboy.ssy_back.config.CustomUserDetailsService;
import com.pjboy.ssy_back.util.JwtTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: ssy_back
 * @description: 接口访问鉴权过滤器
 * @description: 拦截接口请求，从请求 request 获取 token，从 token 中解析得到用户名
 * @description: 然后通过 UserDetailsService 获得系统用户（从数据库、或其他其存储介质）
 * @description: 根据用户信息和 JWT 令牌，验证系统用户与用户输入的一致性，并判断 JWT 是否过期。如果没有过期，至此表明了该用户的确是该系统的用户。
 * @author: BLADE
 * @create: 2020-10-28 22:03
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

  @Resource
  CustomUserDetailsService userDetailsService;

  @Resource
  JwtTokenUtil jwtTokenUtil;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String jwtToken = request.getHeader(jwtTokenUtil.getHeader());
    if (jwtToken != null && StringUtils.isNotEmpty(jwtToken)) {
      String username = jwtTokenUtil.getUsernameFromToken(jwtToken);

      // 如果能从 jwt 中提取到用户信息, 并且该用户未被授权
      if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
          // 给使用 jwt 令牌的用户授权
          Object userDetails1;
          Object principal;
          UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
      }
    }
    filterChain.doFilter(request, response);
  }
}
