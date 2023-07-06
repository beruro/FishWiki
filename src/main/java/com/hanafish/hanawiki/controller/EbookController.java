package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.req.EbookQueryReq;
import com.hanafish.hanawiki.req.EbookSaveReq;
import com.hanafish.hanawiki.resp.CommonResp;
import com.hanafish.hanawiki.resp.EbookQueryResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list1")
    public CommonResp list(EbookQueryReq req) {
        CommonResp<PageResq<EbookQueryResp>> resp = new CommonResp<>();
        PageResq<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req) {
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
}
