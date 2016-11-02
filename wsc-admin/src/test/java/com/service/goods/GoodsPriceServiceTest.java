package com.service.goods;

import com.BaseTest;
import com.common.entity.goods.GoodsPrice;
import com.common.service.goods.IGoodsPriceService;
import com.common.utils.date.DateUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by bowen on 2016-11-02 22:02
 */
public class GoodsPriceServiceTest extends BaseTest {

    @Autowired
    private IGoodsPriceService goodsPriceService;

    @Test
    public void inserGoodsPriceTest(){
        Date now = new Date();
        GoodsPrice goodsPrice = new GoodsPrice();
        goodsPrice.setBegianDay(now);
        goodsPrice.setEndDay(DateUtils.addDay(now, 10));
        goodsPrice.setGoodsId(1);
        goodsPrice.setGoodsItemId(1);
        goodsPrice.setPrice(50);
        goodsPrice.setStatus(false);
        goodsPriceService.insert(goodsPrice);
    }
}
