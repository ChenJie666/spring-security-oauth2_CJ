package com.cj.oauth2.server.service.impl;

import com.cj.oauth2.server.domain.TbUser;
import com.cj.oauth2.server.mapper.TbUserMapper;
import com.cj.oauth2.server.service.TbUserService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;

@Service
public class TbUserServiceImpl implements TbUserService {

    //TODO 获取dao层接口的实例
    @Resource
    private TbUserMapper tbUserMapper;

    @Override
    public TbUser getByUsername(String username) {
        Example example = new Example(TbUser.class);
        example.createCriteria().andEqualTo("username", username);//TODO 通过example对象进行查询
        return tbUserMapper.selectOneByExample(example);
    }

}
