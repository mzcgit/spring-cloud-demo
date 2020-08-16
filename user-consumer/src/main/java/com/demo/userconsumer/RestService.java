package com.demo.userconsumer;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

/**
 * @author mzc
 */
@Service
public class RestService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(defaultFallback = "back")
    public String alive() {
        String url = "http://user-provider/alive";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    public String back() {
        return "hehe";
    }

}
