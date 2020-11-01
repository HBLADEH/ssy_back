package com.pjboy.ssy_back.config;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: ssy_back
 * @description:
 * @author: BLADE
 * @create: 2020-10-31 16:33
 **/

public interface CustomUserDetailsServiceMapper {

  @Select("select username, password, enabled " +
          "from sys_user u " +
          "where u.username = #{userId}")
  public CustomUserDetails findByUsername(@Param("userId") String userId);

  @Select("select r.role_code " +
          "from sys_role r " +
          "left join sys_user_role ur on r.id = ur.role_id " +
          "left join sys_user u on u.id = ur.user_id " +
          "where u.username = #{userId}")
  public List<String> findRoleByUserName(@Param("userId") String userId);

  @Select({
          "<script>",
          "select url",
          "from sys_menu m",
          "left join sys_role_menu rm on m.id = rm.menu_id",
          "left join sys_role r on r.id = rm.role_id",
          "where r.role_code in",
          "<foreach collection='roleCodes' item='roleCode' open='(' separator=',' close=')'>",
          "#{roleCode}",
          "</foreach>",
          "</script>"
  })
  public List<String> findAuthorityByRoleCodes(@Param("roleCodes") List<String> roleCodes);
}
