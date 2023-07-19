package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.req.UserQueryReq;
import com.hanafish.hanawiki.req.UserSaveReq;
import com.hanafish.hanawiki.resp.CommonResp;
import com.hanafish.hanawiki.resp.UserQueryResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/list1")
    public CommonResp list(@Valid UserQueryReq req) {
        CommonResp<PageResq<UserQueryResp>> resp = new CommonResp<>();
        PageResq<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }
}
