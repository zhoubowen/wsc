package com.service.goods;

import com.BaseTest;
import com.common.dto.goods.GoodsSearchDto;
import com.common.entity.goods.Goods;
import com.common.entity.picfile.PicFile;
import com.common.service.goods.IGoodsService;
import com.common.utils.page.PageInfo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by bowen on 2016-10-28 22:42
 */
public class GoodsServiceTest extends BaseTest{

    @Autowired
    private IGoodsService goodsService;

    @Test
    public void testFindById(){
        Goods goods = goodsService.findById(1);
        Assert.assertNotNull(goods);
        System.out.println("ok");
    }

    @Test
    public void testInsert(){
        for(int i = 21 ; i< 25; i++){
        Goods goods = new Goods();
        goods.setGoodsDetailHtml("测试商品详情页" + i);
        goods.setGoodsTitle("测试商品"+ i);
        goods.setGoodsSubTitle("测试副标题" + i);
        goods.setGoodsPicFile(new PicFile(1));
        int row = goodsService.insert(goods);
        Assert.assertTrue(row > 0);
        }
        System.out.println("ok");
    }

    @Test
    public void findPageList(){
       PageInfo <Goods> pageInfo = goodsService.findPageList(new GoodsSearchDto());
        Assert.assertNotNull(pageInfo);

    }
}
