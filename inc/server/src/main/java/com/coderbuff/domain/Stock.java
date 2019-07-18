package com.coderbuff.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 公司股票数据
 * Created by OKevin on 2019-07-18 23:23
 */
@Setter
@Getter
@ToString
public class Stock {

    /**
     * 公司名称
     */
    private String name;

    /**
     * 公司股票代码
     */
    private String code;

    /**
     * 总股本（单位：亿）
     */
    private String allStock;

    /**
     * 股票市场来源
     * {@link com.coderbuff.util.MarketSourceEnum}
     */
    private Integer source;
}
