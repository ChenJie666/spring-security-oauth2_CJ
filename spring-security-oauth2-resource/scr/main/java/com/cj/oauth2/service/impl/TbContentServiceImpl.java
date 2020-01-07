package com.cj.oauth2.service.impl;

import com.cj.oauth2.dao.TbContentMapper;
import com.cj.oauth2.entities.TbContent;
import com.cj.oauth2.service.TbContentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TbContentServiceImpl implements TbContentService {

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public List<TbContent> selectAll() {
        return tbContentMapper.selectAll();
    }
}
