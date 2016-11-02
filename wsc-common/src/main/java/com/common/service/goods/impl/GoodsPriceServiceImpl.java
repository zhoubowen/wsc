package com.common.service.goods.impl;

import com.common.dao.goods.IGoodsPriceDao;
import com.common.entity.goods.GoodsPrice;
import com.common.service.goods.IGoodsPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 价格
 * Created by bowen on 2016-11-02 22:05
 */
@Service
public class GoodsPriceServiceImpl implements IGoodsPriceService {

    @Autowired
    private IGoodsPriceDao goodsPriceDao;

    @Override
    public int insert(GoodsPrice goodsPrice) {
        return goodsPriceDao.insert(goodsPrice);
    }

    @Override
    public int batchInsert(List<GoodsPrice> list) {
        return goodsPriceDao.batchInsert(list);
    }
}
