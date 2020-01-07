package com.cj.oauth2.dao;

import com.cj.oauth2.entities.TbContent;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface TbContentMapper extends MyMapper<TbContent> {
    List<TbContent> selectAll();
}
