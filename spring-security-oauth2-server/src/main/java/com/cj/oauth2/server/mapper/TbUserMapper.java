package com.cj.oauth2.server.mapper;

import com.cj.oauth2.server.domain.TbUser;
import tk.mybatis.mapper.MyMapper;
import tk.mybatis.mapper.entity.Example;

public interface TbUserMapper extends MyMapper<TbUser> {    //继承MyMapper怎么理解？？

    TbUser selectOneByExample(Example example); //TODO 通过封装sql代码进行查询
}
