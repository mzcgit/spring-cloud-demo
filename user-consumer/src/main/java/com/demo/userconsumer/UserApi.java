package com.demo.userconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author mzc
 */

/**
 * 第一种方式  直接调provider提供的方法
 *
 * 不结合Eureka,就是自定义一个Client名字，就是url属性指定服务器列表，url=http://ip:port
 * name 是provider服务名
 */
//@FeignClient(name="xxoo",url="http://localhost:2000")
//@FeignClient(name="user-provider")
public interface UserApi {

    @GetMapping("/alive")
    public String alive();
}
