package com.demo.eurekaconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


/**
 * @author mzc
 */
@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取返回的一个String
     * @return
     */
    @GetMapping("/getString")
    public String getString() {
        String url = "http://localhost:81/getHi";
        String forObject = restTemplate.getForObject(url, String.class);
        return forObject;
    }

    /**
     * 获取返回的一个Map
     * @return
     */
    @GetMapping("/getEntity")
    public String getEntity() {
        String url = "http://provider/getHi";
        ResponseEntity<String> forEntity = restTemplate.getForEntity(url, String.class);
        return forEntity.toString();
    }

    /**
     * 获取返回的是一个对象
     * @return
     */
    @GetMapping("/getPerson")
    public String getPerson() {
        String url = "http://provider/getPerson";
        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url, Person.class);
        return forEntity.toString();
    }


    /**
     * 使用占位符传参
     * @return
     */
    @GetMapping("/getPerson2")
    public String getPerson2() {
        String url = "http://provider/getPerson2?name={1}&sex={2}";
        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url,Person.class,"meng","女");
        return forEntity.toString();
    }

    /**
     * 使用map传参
     * @return
     */
    @GetMapping("/getPerson3")
    public String getPerson3() {
        String url = "http://provider/getPerson3?name={name}&sex={sex}";

        Map<String, String> map = new HashMap<>();
        map.put("name", "nameMpa");
        map.put("sex", "女");

        ResponseEntity<Person> forEntity = restTemplate.getForEntity(url,Person.class,map);
        return forEntity.toString();
    }

    /**
     * 使用map传参  调用provider中的postParam方法
     * @return
     */
    @GetMapping("/getPerson4")
    public String getPerson4() {
        String url = "http://provider/postParam";

        Map<String, String> map = new HashMap<>();
        map.put("name", "nameMpa");
        map.put("sex", "女");

        ResponseEntity<Person> personResponseEntity = restTemplate.postForEntity(url, map, Person.class);
        return personResponseEntity.toString();
    }

    /**
     * 使用map传参  调用provider中的postParam方法
     * @return
     */
    @GetMapping("/getPerson5")
    public String getPerson5() {
        String url = "http://provider/postParam";

        Person person = new Person();
        person.setName("haha");
        person.setSex("男");


        ResponseEntity<Person> personResponseEntity = restTemplate.postForEntity(url, person, Person.class);
        return personResponseEntity.toString();
    }

    /**
     * postForLocation调用provider中的postLocation方法
     * @return
     */
    @GetMapping("/postLocation")
    public void postLocation(HttpServletResponse response) {
        String url = "http://provider/postLocation";

        Person person = new Person();
        person.setName("haha");
        person.setSex("男");

        URI uri = restTemplate.postForLocation(url, person, URI.class);
        try {
            response.sendRedirect(uri.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
