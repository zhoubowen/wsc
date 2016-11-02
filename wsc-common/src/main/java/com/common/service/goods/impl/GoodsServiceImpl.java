package com.common.service.goods.impl;

import com.common.dao.goods.IGoodsDao;
import com.common.dto.goods.GoodsSearchDto;
import com.common.entity.goods.Goods;
import com.common.service.goods.IGoodsService;
import com.common.utils.page.PageInfo;
import com.common.utils.page.PaginationContext;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by bowen on 2016-10-28 22:11
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    private IGoodsDao goodsDao;

    @Override
    public int insert(Goods goods) {
        return goodsDao.insert(goods);
    }

    @Override
    public int delete(Integer goodsId) {
        return goodsDao.delete(goodsId);
    }

    @Override
    public int update(Goods goods) {
        return goodsDao.update(goods);
    }

    @Override
    public Goods findById(Integer goodsId) {
        return goodsDao.findById(goodsId);
    }

    @Override
    public PageInfo<Goods> findPageList(GoodsSearchDto goodSearchDto) {
        PageHelper.startPage(PaginationContext.getPageNum(), PaginationContext.getPageSize());
        List<Goods> list = goodsDao.findPageList(goodSearchDto);
        return  new PageInfo<Goods>(list);
    }
}
