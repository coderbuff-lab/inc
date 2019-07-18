package com.coderbuff.domain;

import com.coderbuff.util.MarketSourceEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 公司Domain
 * Created by OKevin on 2019-07-17 23:58
 **/
@Setter
@Getter
@ToString
public class Company {
    /**
     * 名称
     */
    private String name;

    /**
     * 代码
     */
    private String code;

    /**
     * 来源
     * {@link MarketSourceEnum}
     */
    private Integer source;

    /**
     * 市值
     */
    private Double money;

    /**
     * 排名
     */
    private Integer rank;

    /**
     * 更新时间
     */
    private Long update;
}
