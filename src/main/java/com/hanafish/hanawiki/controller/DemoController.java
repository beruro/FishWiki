package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.domain.Demo;
import com.hanafish.hanawiki.service.DemoService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private DemoService demoService;

    @GetMapping("/list1")
    public List<Demo> list() {
        return demoService.list();
    }
}
