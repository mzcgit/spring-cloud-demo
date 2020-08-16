package com.demo.userprovider;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mzc
 */
@RestController
public class UserController {

    @GetMapping("/alive")
    public String alive() {
        return "alive";
    }
}
