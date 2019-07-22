package com.coderbuff.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.coderbuff.dao.CompanyStockData;
import com.coderbuff.domain.Company;
import com.coderbuff.domain.Stock;
import com.coderbuff.util.MarketSourceEnum;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private static String HK = "http://web.juhe.cn:8080/finance/stock/hk?num=%s&key=%s";

    /**
     * 美国股市
     */
    private static String USA = "http://web.juhe.cn:8080/finance/stock/usa?gid=%s&key=%s";

    /**
     * 汇率
     */
    private static String EXCHANGE = "http://op.juhe.cn/onebox/exchange/currency?key=%s&from=%s&to=%s";

    @Override
    public List<Company> fetchRankedCompany() throws ParseException {
        List<Stock> stockData = CompanyStockData.getStockData();
        List<Company> companyList = Lists.newArrayList();
        for (Stock stock : stockData) {
            if (stock.getSource().equals(MarketSourceEnum.USA.getSource())) {
                companyList.add(usa(stock));
            } else if (stock.getSource().equals(MarketSourceEnum.HK.getSource())) {
                companyList.add(hk(stock));
            } else if (stock.getSource().equals(MarketSourceEnum.CN.getSource())) {

            }
        }

        companyList.sort(Comparator.comparingDouble(Company::getMoney)
                .reversed());

        return companyList;
    }

    private Company hk(Stock stock) throws ParseException {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(HK, stock.getCode(), "71e065a2cdf2753a5d6261b5002498b7")).build().encode().toUri();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);
        JSONObject resultObject = JSON.parseObject(data);
        JSONArray array = JSON.parseArray(resultObject.getString("result"));
        JSONObject stockObject = array.getJSONObject(0).getJSONObject("data");
        String price = stockObject.getString("lastestpri");
        String updateDate = stockObject.getString("date").replace("/", "-");
        String updateTime = stockObject.getString("time");
        String update = updateDate + " " + updateTime;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(update);
        Company company = new Company();
        company.setName(stock.getName());
        company.setCode(stock.getCode());
        company.setSource(MarketSourceEnum.HK.getSource());
        String marketValue = String.valueOf(Double.parseDouble(stock.getAllStock()) * Double.parseDouble(price));
        company.setMoney(exchange("HKD", "USD", marketValue));
        company.setUpdate(date.getTime());
        return company;
    }


    /**
     * 美国股市公司市值
     * @param stock 公司股票基础数据
     * @return 公司市值数据
     */
    private Company usa(Stock stock) throws ParseException {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(USA, stock.getCode(), "71e065a2cdf2753a5d6261b5002498b7")).build().encode().toUri();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);
        JSONObject resultObject = JSON.parseObject(data);
        JSONArray array = JSON.parseArray(resultObject.getString("result"));
        JSONObject stockObject = array.getJSONObject(0).getJSONObject("data");
        String update = stockObject.getString("chtime");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = sdf.parse(update);

        String marketValueStr = stockObject.getString("markValue");
        double marketValueOriginal = Double.parseDouble(marketValueStr);

        double multiple = 100000000;
        DecimalFormat format = new DecimalFormat("0.00");



        Company company = new Company();
        company.setName(stock.getName());
        company.setCode(stock.getCode());
        company.setSource(MarketSourceEnum.USA.getSource());
        company.setMoney(format.format(marketValueOriginal / multiple));
        company.setUpdate(date.getTime());
        return company;
    }

    private String exchange(String from, String to, String money) {
        URI uri = UriComponentsBuilder.fromHttpUrl(String.format(EXCHANGE, "038d60957dddf4beba16ba7bcf98a6d4", from, to)).build().encode().toUri();
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);
        JSONObject resultObject = JSON.parseObject(data);
        JSONArray array = JSON.parseArray(resultObject.getString("result"));
        JSONObject stockObject = array.getJSONObject(0);
        String exchange = stockObject.getString("exchange");
        DecimalFormat format = new DecimalFormat("0.00");
        return format.format(Double.parseDouble(exchange) * Double.parseDouble(money));

    }
}
