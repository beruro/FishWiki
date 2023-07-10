package com.hanafish.hanawiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanafish.hanawiki.domain.Doc;
import com.hanafish.hanawiki.domain.DocExample;
import com.hanafish.hanawiki.mapper.DocMapper;
import com.hanafish.hanawiki.req.DocQueryReq;
import com.hanafish.hanawiki.req.DocSaveReq;
import com.hanafish.hanawiki.resp.DocQueryResp;
import com.hanafish.hanawiki.resp.PageResq;
import com.hanafish.hanawiki.util.CopyUtil;
import com.hanafish.hanawiki.util.SnowFlake;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class DocService {

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private DocMapper docMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all() {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        // 列表复制
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);

        return list;
    }

    public PageResq<DocQueryResp> list(DocQueryReq req) {
        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> docList = docMapper.selectByExample(docExample);

        PageInfo<Doc> pageInfo = new PageInfo<>(docList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
//        List<DocResp> respList = new ArrayList<>();
//        for (Doc doc : docList) {
//            DocResp docResp = new DocResp();
//            BeanUtils.copyProperties(doc, docResp);
//            respList.add(docResp);
//        }
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);

        PageResq<DocQueryResp> pageResq = new PageResq();
        pageResq.setTotal(pageInfo.getTotal());
        pageResq.setList(list);

        return pageResq; //对象列表的复制
    }

    /**
     * 保存
     */
    public void save(DocSaveReq req) {
        Doc doc = CopyUtil.copy(req, Doc.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);
        } else {
            // 更新
            docMapper.updateByPrimaryKey(doc);
        }
    }

    /**
     * 删除
     */
    public void delete (Long id) {
        docMapper.deleteByPrimaryKey(id);
    }
    public void delete (List<String> ids) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();
        criteria.andIdIn(ids);
        docMapper.deleteByExample(docExample);
    }
}
