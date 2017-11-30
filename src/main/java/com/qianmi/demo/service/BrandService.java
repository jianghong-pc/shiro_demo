package com.qianmi.demo.service;

import com.qianmi.demo.common.exception.ShiroDemoException;
import com.qianmi.demo.common.util.Pagination;
import com.qianmi.demo.common.util.PaginationSupport;
import com.qianmi.demo.dao.mapper.BrandMapper;
import com.qianmi.demo.pojo.brand.Brand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户相关操作服务
 */
@Service
public class BrandService {

    @Resource
    private BrandMapper brandMapper;

    public PaginationSupport<Brand> query(Brand user, Pagination page) throws ShiroDemoException {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("page", page);
        paramMap.put("user", user);

        int totalCount = brandMapper.count(paramMap);

        List<Brand> list;
        if (totalCount > 0) {
            list = brandMapper.query(paramMap);
        } else {
            list = Collections.emptyList();
        }

        return new PaginationSupport<>(list, totalCount, page.getiDisplayLength(), page.getiDisplayStart());
    }

}
