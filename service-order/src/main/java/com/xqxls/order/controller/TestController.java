package com.xqxls.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2023/9/13 11:07
 */
@RestController
public class TestController {

    @GetMapping("")
    public String test(){

        return "order service";
    }
}
