package com.cj.oauth2.server.mapper;

import com.cj.oauth2.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbPermissionMapper extends MyMapper<TbPermission> {      //继承MyMapper怎么理解？？

    List<TbPermission> selectByUserId(@Param("userId") Long userId);    //TODO 将userId传入sql语句中，需要@Param注解
}
