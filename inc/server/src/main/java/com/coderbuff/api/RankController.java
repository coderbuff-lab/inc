package com.coderbuff.api;

import com.coderbuff.domain.Company;
import com.coderbuff.domain.Message;
import com.coderbuff.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

/**
 * 股市数据API
 * Created by OKevin on 2019-07-18 00:40
 **/
@RestController
public class RankController {

    @Autowired
    private RankService rankService;

    @RequestMapping(value = "/rank")
    public Message fetchCompany() {
        List<Company> companyList = null;
        try {
            companyList = rankService.fetchRankedCompany();
        } catch (ParseException e) {
            return Message.failure();
        }
        return Message.message(companyList);
    }
}
