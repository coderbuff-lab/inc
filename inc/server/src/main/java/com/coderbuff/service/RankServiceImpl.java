package com.coderbuff.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderbuff.domain.Company;
import com.coderbuff.util.MarketSourceEnum;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by OKevin on 2019-07-18 22:46
 */
@Service
public class RankServiceImpl implements RankService{

    /**
     * 沪深股市
     */
    private static String CN = "http://web.juhe.cn:8080/finance/stock/hs?gid={}&key={}&type={}";

    /**
     * 香港股市
     */
    private static String HK = "http://web.juhe.cn:8080/finance/stock/hk?num={}&key={}";

    /**
     * 美国股市
     */
    private static String USA = "http://web.juhe.cn:8080/finance/stock/usa?gid=%s&key=%s";

    @Override
    public List<Company> fetchRankedCompany() throws ParseException {

        return Lists.newArrayList(usa("jd"));
    }


    /**
     * 美国股市公司市值
     * @param code 股票代码
     * @return 公司市值数据
     */
    private Company usa(String code) throws ParseException {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(USA, "jd", "71e065a2cdf2753a5d6261b5002498b7")).build().encode().toUri();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);
        JSONObject resultObject = JSON.parseObject(data);
        JSONArray array = JSON.parseArray(resultObject.getString("result"));
        JSONObject stockObject = array.getJSONObject(0).getJSONObject("data");
        String name = stockObject.getString("name");
        String bjTime = stockObject.getString("chtime");
        Company company = new Company();
        company.setName(name);
        company.setCode(code);
        company.setSource(MarketSourceEnum.USA.getSource());
        //company.setUpdate(new SimpleDateFormat().parse(bjTime).getTime());
        return company;
    }

}
