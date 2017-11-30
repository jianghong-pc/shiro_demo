package com.qianmi.demo.dao.mapper;

import com.qianmi.demo.pojo.brand.Brand;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BrandMapper {

    int count(Map<String, Object> paramMap);

    List<Brand> query(Map<String, Object> paramMap);

}
