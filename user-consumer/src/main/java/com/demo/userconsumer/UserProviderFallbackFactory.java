package com.demo.userconsumer;

import com.demo.userapi.Person;
import feign.FeignException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author mzc
 */
@Component
public class UserProviderFallbackFactory implements FallbackFactory<UserImplApi> {
    @Override
    public UserImplApi create(Throwable throwable) {
        return new UserImplApi() {
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
                System.out.println(throwable);
                if (throwable instanceof FeignException.InternalServerError) {
                    String message = throwable.getMessage();
                    System.out.println(111111111);
                    return message;
                } else {
                    return "hehe";
                }

            }

            @Override
            public String getById(Integer id) {
                return null;
            }

            @Override
            public List<Person> getUserList(String ids) {
                return null;
            }
        };
    }
}
