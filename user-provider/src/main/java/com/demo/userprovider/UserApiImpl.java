package com.demo.userprovider;

import com.demo.userapi.Person;
import com.demo.userapi.UserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现 user-api
 *
 * @author mzc
 */
@RestController
public class UserApiImpl implements UserApi {

    @Value("${server.port}")
    String port;

    AtomicInteger count = new AtomicInteger();

    @Override
    public String alive2(){
//        try {
//            System.out.println("开始睡眠。。。。");
//            Thread.sleep(4000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        int i = count.getAndIncrement();
//        System.out.println(port + " 好的 ====第：" + i + "次调用");
//        int i =  1/0;

        return "alive2:port:"+port;
    }

    @Override
    public String getById(Integer id) {
        return "getByid:"+id;
    }

    @GetMapping("/getMap")
    public Map<Integer, String> getMap(@RequestParam("id") Integer id) {
        // TODO Auto-generated method stub
        System.out.println(id);
        Map<Integer, String> map = new HashMap<>();
        map.put(id, "sxxxxxxx");
//        return Collections.singletonMap(id, "mmeme");
        return map;
    }
    @GetMapping("/getMap2")
    public Map<Integer, String> getMap2(Integer id,String name) {
        // TODO Auto-generated method stub
        System.out.println(id);
        return Collections.singletonMap(id, name);
    }

    @GetMapping("/getMap3")
    public Map<Integer, String> getMap3(@RequestParam Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }


    @PostMapping("/postMap")
    public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
        // TODO Auto-generated method stub
        System.out.println(map);
        return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
    }

    @Override
    public List<Person> getUserList(String ids) {
        String[] idarr = ids.split(",");

        ArrayList<Person> arrayList = new ArrayList<>();
        for (int i = 0; i < idarr.length; i++) {
            Person person = new Person();
            person.setId(i);
            person.setName("name"+i);

            arrayList.add(person);
        }
        return arrayList;
    }
}
