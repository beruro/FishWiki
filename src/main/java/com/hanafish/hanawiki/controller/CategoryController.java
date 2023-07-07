package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.req.CategoryQueryReq;
import com.hanafish.hanawiki.req.CategorySaveReq;
import com.hanafish.hanawiki.resp.CommonResp;
import com.hanafish.hanawiki.resp.CategoryQueryResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.service.CategoryService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list1")
    public CommonResp list(@Valid CategoryQueryReq req) {
        CommonResp<PageResq<CategoryQueryResp>> resp = new CommonResp<>();
        PageResq<CategoryQueryResp> list = categoryService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req) {
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
