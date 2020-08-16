package com.demo.userconsumer;

import com.demo.userapi.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author mzc
 */
@Component
public class UserProviderFallback implements UserImplApi {
    @Override
    public Map<Integer, String> getMap(Integer id) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap2(Integer id, String name) {
        return null;
    }

    @Override
    public Map<Integer, String> getMap3(Map<String, Object> map) {
        return null;
    }

    @Override
    public Map<Integer, String> postMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public String alive2() {
        return "降级了";
    }

    @Override
    public String getById(Integer id) {
        return null;
    }

    @Override
    public List<Person> getUserList(String ids) {
        return null;
    }
}
