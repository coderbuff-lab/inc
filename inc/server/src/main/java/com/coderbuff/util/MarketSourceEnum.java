package com.coderbuff.util;

/**
 * 公司Domain
 * Created by OKevin on 2019-07-18 00:10
 **/
public enum MarketSourceEnum {
    CN(1, "沪深股市"),
    HK(2, "香港股市"),
    USA(3, "美国股市");

    private Integer source;
    private String desc;

    MarketSourceEnum(Integer source, String desc) {
        this.source = source;
        this.desc = desc;
    }

    public Integer getSource() {
        return this.source;
    }

    public String getDesc() {
        return this.desc;
    }

    public static MarketSourceEnum getSource(Integer source) {
        switch (source) {
            case 1:
                return CN;
            case 2:
                return HK;
            case 3:
                return USA;
        }
        return null;
    }
}
