package com.lucio.demo.service;

import com.lucio.demo.bean.CookbookInfo;
import com.lucio.demo.mapper.CookbookInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CookbookService {

    @Autowired
    private CookbookInfoMapper cookbookInfoMapper;

    public void create(CookbookInfo cookbookInfo){
        cookbookInfoMapper.insertSelective(cookbookInfo);
    }


    public void update(CookbookInfo cookbookInfo){
        cookbookInfoMapper.updateByPrimaryKeySelective(cookbookInfo);
    }

    public void delete(Integer id){
        cookbookInfoMapper.deleteByPrimaryKey(id);
    }

    public CookbookInfo query(Integer id){
        CookbookInfo info = cookbookInfoMapper.selectByPrimaryKey(id);
        return info;
    }

    public List<CookbookInfo> list(CookbookInfo cookbookInfo){
        return cookbookInfoMapper.queryList(cookbookInfo);
    }


}
