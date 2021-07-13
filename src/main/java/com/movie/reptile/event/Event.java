package com.movie.reptile.event;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.movie.reptile.config.ReptileConfig;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : 765819328@qq.com
 * @version :
 * @date : Created in 2021/7/13 19:07
 * @description: 定时器
 * @modified By:
 */
@Component
@EnableScheduling
public class Event {

    @Autowired
    private RestTemplate restTemplate;

    private HttpEntity<String> httpEntity;

    @PostConstruct
    private void init() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0");
        headers.add("Host", "piaofang.maoyan.com");
        headers.add("Upgrade-Insecure-Requests", "1");


        //Accept: application/json, text/plain, */*
        headers.add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q = 0.8");
        headers.add("Accept-Language", "zh-cn");
        headers.add("Connection", "keep-alive");

        httpEntity = new HttpEntity<>(headers);
    }

    @Scheduled(cron = "*/1 * * * * ?")
    public void deal() {
        String res = getData();
        JSONObject json = JSONObject.parseObject(res);
        System.out.println(LocalDateTime.now().getSecond());

        if (!json.getBoolean("status")) {
            throw new IllegalArgumentException("请求错误=>" + res);
        }
        JSONArray boxOfficeList = ((json.getJSONObject("boxOffice")).getJSONObject("data")).getJSONArray("list");
        boxOfficeList.forEach(boxOfficeOne -> System.out.println(boxOfficeOne));
        System.out.println(getData());
    }

    private String getData() {


        ResponseEntity<String> responseEntity = restTemplate.exchange(ReptileConfig.url, HttpMethod.GET, httpEntity, String.class);

        if (!HttpStatus.OK.equals(responseEntity.getStatusCode())) {
            throw new IllegalArgumentException("请求失败=>" + responseEntity.getBody());
        }
        return responseEntity.getBody();
    }
}
