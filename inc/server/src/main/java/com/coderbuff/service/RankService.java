package com.coderbuff.service;

import com.coderbuff.domain.Company;

import java.text.ParseException;
import java.util.List;

/**
 * 排名服务
 * Created by OKevin on 2019-07-18 22:45
 */
public interface RankService {

    /**
     * 获取公司排名
     * @return 公司排名
     */
    List<Company> fetchRankedCompany() throws ParseException;
}
