package com.cj.oauth2.controller;

import com.cj.oauth2.dto.ResponseResult;
import com.cj.oauth2.entities.TbContent;
import com.cj.oauth2.service.impl.TbContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TbContentController {

    @Autowired
    private TbContentServiceImpl tbContentService;

    @GetMapping("/")
    public ResponseResult<List<TbContent>> list(){
        return new ResponseResult<List<TbContent>>(HttpStatus.OK.value(), HttpStatus.OK.toString(),tbContentService.selectAll());
    }

}
