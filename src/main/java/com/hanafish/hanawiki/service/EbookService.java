package com.hanafish.hanawiki.service;

import com.hanafish.hanawiki.domain.Ebook;
import com.hanafish.hanawiki.mapper.EbookMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<Ebook> list() {
        return ebookMapper.selectByExample(null);
    }
}
