package com.coderbuff.dao;

import com.alibaba.fastjson.JSONObject;
import com.coderbuff.domain.Stock;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.List;

/**
 * 加载json格式的公司股票数据
 * Created by OKevin on 2019-07-22 22:18
 */
@Component("companyData")
public class CompanyStockData implements InitializingBean {

    private static List<Stock> stockList = Lists.newArrayList();

    @Value("classpath:company.json")
    private Resource resource;


    /**
     * 初始化加载json格式的公司股票数据
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        String stockDataStr =  IOUtils.toString(resource.getInputStream(), Charset.forName("UTF-8"));
        stockList = JSONObject.parseArray(stockDataStr, Stock.class);
    }

    public static List<Stock> getStockData() {

        return stockList;
    }
}
