package com.demo.userconsumer;

import com.demo.userapi.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author mzc
 */
@RestController
@RefreshScope
public class UserController {
    @Value("${server.port}")
    String port;
//    @Autowired
//    UserApi userApi;

    @Autowired
    UserImplApi userImplApi;

    @Autowired
    RestService rest;

    @Value("${myconfig}")
    String myconfig;

    @GetMapping
    public String getMyconfig() {
        return myconfig;
    }

    @GetMapping("/alive")
    public String alive() {
//        return userApi.alive();
        return null;
    }

    @GetMapping("/alive2")
    public String alive2() {
        return "consumer:"+port+" "+userImplApi.alive2();
    }

    @GetMapping("/alive3")
    public String alive3() {
        return rest.alive();
    }

    @GetMapping("/getById")
    public String getById(@RequestParam Integer id) {
        System.out.println("idsfdsdfsfsfs:"+id);
        return userImplApi.getById(id);
    }

    @GetMapping("/map")
    public Map<Integer,String> map(@RequestParam Integer id, HttpServletResponse response) {
        System.out.println(id);
        Map<Integer, String> map = userImplApi.getMap(id);

        Person person = new Person();
        person.setId(id);
        person.setName(map.get(id));
        return map;
    }


    @GetMapping("/map2")
    public Map<Integer, String> map2(Integer id,String name) {
        System.out.println(id);
        System.out.println(name);
        Map<Integer, String> map2 = userImplApi.getMap2(id, name);
        System.out.println(map2);
        return map2;
    }


    @GetMapping("/map3")
    public Map<Integer, String> map3(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return userImplApi.getMap3(map);
    }


    @GetMapping("/map4")
    public Map<Integer, String> map4(@RequestParam Map<String, Object> map) {
//		System.out.println(id);
//		HashMap<String, Object> map = new HashMap<>(2);
//
//		map.put("id", id);
//		map.put("name", name);
//		syso
        System.out.println(map);
        return userImplApi.postMap(map);
    }

    @GetMapping("/getUserList")
    public List<Person> getUserList(@RequestParam String ids) {
        List<Person> userList = userImplApi.getUserList(ids);
        return userList;
    }
}
