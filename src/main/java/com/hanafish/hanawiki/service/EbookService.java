package com.hanafish.hanawiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanafish.hanawiki.domain.Ebook;
import com.hanafish.hanawiki.domain.EbookExample;
import com.hanafish.hanawiki.mapper.EbookMapper;
import com.hanafish.hanawiki.req.EbookReq;
import com.hanafish.hanawiki.resp.EbookResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.util.CopyUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class EbookService {

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    @Resource
    private EbookMapper ebookMapper;

    public PageResq<EbookResp> list(EbookReq req) {
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%" + req.getName() + "%");
        }
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> pageInfo = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
//        List<EbookResp> respList = new ArrayList<>();
//        for (Ebook ebook : ebookList) {
//            EbookResp ebookResp = new EbookResp();
//            BeanUtils.copyProperties(ebook, ebookResp);
//            respList.add(ebookResp);
//        }
        List<EbookResp> list = CopyUtil.copyList(ebookList, EbookResp.class);

        PageResq<EbookResp> pageResq = new PageResq<>();
        pageResq.setTotal(pageInfo.getTotal());
        pageResq.setList(list);

        return pageResq; //对象列表的复制
    }
}
