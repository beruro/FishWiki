package com.hanafish.hanawiki.controller;

import com.hanafish.hanawiki.req.UserLoginReq;
import com.hanafish.hanawiki.req.UserQueryReq;
import com.hanafish.hanawiki.req.UserResetPasswordReq;
import com.hanafish.hanawiki.req.UserSaveReq;
import com.hanafish.hanawiki.resp.CommonResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.resp.UserLoginResp;
import com.hanafish.hanawiki.resp.UserQueryResp;
import com.hanafish.hanawiki.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.util.DigestUtils;
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
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
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

    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@Valid @RequestBody UserLoginReq req) {
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp userLoginResp = userService.login(req);
//        Long token = snowFlake.nextId();
//        LOG.info("生成单点登录token：{}，并放入redis中", token);
//        userLoginResp.setToken(token.toString());
//        redisTemplate.opsForValue().set(token.toString(), JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);
        resp.setContent(userLoginResp);
        return resp;
    }
}
