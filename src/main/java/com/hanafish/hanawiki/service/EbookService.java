package com.hanafish.hanawiki.service;

import com.hanafish.hanawiki.domain.Ebook;
import com.hanafish.hanawiki.domain.EbookExample;
import com.hanafish.hanawiki.mapper.EbookMapper;
import com.hanafish.hanawiki.req.EbookReq;
import com.hanafish.hanawiki.resp.EbookResp;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%" + req.getName() + "%");
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            respList.add(ebookResp);
        }
        return respList; //对象的复制
    }
}
