package com.lucio.demo.mapper;

import com.lucio.demo.bean.CookbookInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CookbookInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CookbookInfo record);

    int insertSelective(CookbookInfo record);

    CookbookInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CookbookInfo record);

    int updateByPrimaryKey(CookbookInfo record);

    List<CookbookInfo> queryList(CookbookInfo record);
}