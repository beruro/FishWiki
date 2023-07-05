package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.req.EbookReq;
import com.hanafish.hanawiki.resp.CommonResp;
import com.hanafish.hanawiki.resp.EbookResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.service.EbookService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list1")
    public CommonResp list(EbookReq req) {
        CommonResp<PageResq<EbookResp>> resp = new CommonResp<>();
        PageResq<EbookResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
