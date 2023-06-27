package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.domain.Ebook;
import com.hanafish.hanawiki.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list1")
    public List<Ebook> list() {
        return ebookService.list();
    }
}
