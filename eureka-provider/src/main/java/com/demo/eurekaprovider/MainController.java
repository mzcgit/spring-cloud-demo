package com.demo.eurekaprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mzc
 */
@RestController
public class MainController {

    @GetMapping("/getHi")
    public String getHi() {
        System.out.println("1111112222");
        return "hi";
    }

    @GetMapping("/getMap")
    public Map getMap() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("name", "mzc");
        map.put("sex", "男");
        return map;
    }

    @GetMapping("/getPerson")
    public Person getPerson() {
        Person person = new Person();
        person.setId(1);
        person.setName("mzc");
        person.setSex("男");
        return person;
    }

    @GetMapping("/getPerson2")
    public Person getPerson2(String name, String sex) {
        Person person = new Person();
        person.setId(1);
        person.setName(name);
        person.setSex(sex);
        return person;
    }

    @GetMapping("/getPerson3")
    public Person getPerson3(String name, String sex) {
        Person person = new Person();
        person.setId(1);
        person.setName(name);
        person.setSex(sex);
        return person;
    }


    @PostMapping("/postParam")
    public Person postParam(@RequestBody Map map) {
        System.out.println("map:" + map);
        Person person = new Person();
        person.setId(1);
        person.setName((String) map.get("name"));
        person.setSex((String) map.get("sex"));

        return person;
    }

    @PostMapping("/postLocation")
    public URI postLocation(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("http://www.baidu.com?name=" + person.getName() + "sex=" + person.getSex());
        response.addHeader("Location",uri.toString());
        return uri;
    }
}
