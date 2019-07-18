package com.coderbuff.service;

/**
 * 股市数据API
 * Created by OKevin on 2019-07-18 00:15
 **/
public interface StockService {

    /**
     * 获取股票市值
     * @param source 市场来源
     * @param code 股票代码
     * @return 市值结果
     */
    Double getStockMoney(Integer source, String code);
}
