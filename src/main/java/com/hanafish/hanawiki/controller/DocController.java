package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.req.DocQueryReq;
import com.hanafish.hanawiki.req.DocSaveReq;
import com.hanafish.hanawiki.resp.DocQueryResp;
import com.hanafish.hanawiki.resp.CommonResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.service.DocService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/doc")
public class DocController {

    @Resource
    private DocService docService;

    @GetMapping("/all")
    public CommonResp all() {
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list = docService.all();
        resp.setContent(list);
        return resp;
    }

    @GetMapping("/list1")
    public CommonResp list(@Valid DocQueryReq req) {
        CommonResp<PageResq<DocQueryResp>> resp = new CommonResp<>();
        PageResq<DocQueryResp> list = docService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req) {
        CommonResp resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        docService.delete(id);
        return resp;
    }
}
