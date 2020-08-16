package com.demo.userapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author mzc
 */
//@RequestMapping("/user")
public interface UserApi {

    /**
     * 获取用户状态
     *
     * @return
     */
    @GetMapping("/alive2")
    String alive2();

    /**
     * 通过id获取
     *
     * @return
     */
    @GetMapping("/getById")
    String getById(@RequestParam Integer id);

    @GetMapping("/getUserList")
    List<Person> getUserList(@RequestParam String ids);

}
