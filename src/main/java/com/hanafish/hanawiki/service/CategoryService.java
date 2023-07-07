package com.hanafish.hanawiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hanafish.hanawiki.domain.Category;
import com.hanafish.hanawiki.domain.CategoryExample;
import com.hanafish.hanawiki.mapper.CategoryMapper;
import com.hanafish.hanawiki.req.CategoryQueryReq;
import com.hanafish.hanawiki.req.CategorySaveReq;
import com.hanafish.hanawiki.resp.CategoryQueryResp;
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
public class CategoryService {

    private static final Logger LOG = LoggerFactory.getLogger(CategoryService.class);

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private SnowFlake snowFlake;

    public List<CategoryQueryResp> all() {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        // 列表复制
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        return list;
    }

    public PageResq<CategoryQueryResp> list(CategoryQueryReq req) {
        CategoryExample categoryExample = new CategoryExample();
        categoryExample.setOrderByClause("sort asc");
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);

        PageInfo<Category> pageInfo = new PageInfo<>(categoryList);
        LOG.info("总行数：{}", pageInfo.getTotal());
        LOG.info("总页数：{}", pageInfo.getPages());
//        List<CategoryResp> respList = new ArrayList<>();
//        for (Category category : categoryList) {
//            CategoryResp categoryResp = new CategoryResp();
//            BeanUtils.copyProperties(category, categoryResp);
//            respList.add(categoryResp);
//        }
        List<CategoryQueryResp> list = CopyUtil.copyList(categoryList, CategoryQueryResp.class);

        PageResq<CategoryQueryResp> pageResq = new PageResq();
        pageResq.setTotal(pageInfo.getTotal());
        pageResq.setList(list);

        return pageResq; //对象列表的复制
    }

    /**
     * 保存
     */
    public void save(CategorySaveReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 新增
            category.setId(snowFlake.nextId());
            categoryMapper.insert(category);
        } else {
            // 更新
            categoryMapper.updateByPrimaryKey(category);
        }
    }

    /**
     * 删除
     */
    public void delete (Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }
}
