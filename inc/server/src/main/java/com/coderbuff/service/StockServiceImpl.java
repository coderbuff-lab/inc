package com.coderbuff.service;

import com.coderbuff.util.MarketSourceEnum;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * 股市数据API
 * Created by OKevin on 2019-07-18 00:20
 **/
public class StockServiceImpl implements StockService{

    /**
     * 沪深股市
     */
    private static String HS = "http://web.juhe.cn:8080/finance/stock/hs?gid={}&key={}&type={}";

    /**
     * 香港股市
     */
    private static String HK = "http://web.juhe.cn:8080/finance/stock/hk?num={}&key={}";

    private static String USA = "http://web.juhe.cn:8080/finance/stock/usa?gid={}&key={}";

    @Override
    public Double getStockMoney(Integer source, String code) {
        Double money;


        MarketSourceEnum marketSourceEnum = MarketSourceEnum.getSource(source);
        switch (marketSourceEnum) {
            case HS:
                money = hsStock(code);
                break;
            case HK:
                money = hkStock(code);
                break;
            case USA:
                money = usaStock(code);
                break;
            default:
        }
        return null;
    }

    private Double hsStock(String code) {
        return null;
    }

    private Double hkStock(String code) {
        return null;
    }

    public Double usaStock(String code) {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(USA, code, "appkey")).build().encode().toUri();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);
        System.out.println(data);
        return null;
    }
}
