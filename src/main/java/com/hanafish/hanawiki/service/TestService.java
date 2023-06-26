package com.hanafish.hanawiki.service;

import com.hanafish.hanawiki.domain.Test;
import com.hanafish.hanawiki.mapper.TestMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    @Resource
    private TestMapper testMapper;

    public List<Test> list() {
        return testMapper.list();
    }
}
