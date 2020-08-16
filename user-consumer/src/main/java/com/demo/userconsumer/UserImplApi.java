package com.demo.userconsumer;

import com.demo.userapi.UserApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author mzc
 */

/**
 * 第二种方式  继承user-api的接口
 *
 */
//@FeignClient(name="user-provider",fallback=UserProviderFallback.class)
@FeignClient(name="user-provider",fallbackFactory=UserProviderFallbackFactory.class) //具体错误
public interface UserImplApi extends UserApi {


    @GetMapping("/getMap")
    Map<Integer, String> getMap(@RequestParam("id") Integer id);

    @GetMapping("/getMap2")
    Map<Integer, String> getMap2(@RequestParam("id") Integer id,@RequestParam("name") String name);

    @GetMapping("/getMap3")
    Map<Integer, String> getMap3(@RequestParam Map<String, Object> map);

    @PostMapping("/postMap")
    Map<Integer, String> postMap(Map<String, Object> map);

}
