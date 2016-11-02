package com.common.dao.goods;

import com.common.entity.goods.GoodsPrice;

import java.util.List;

/**
 * 商品价格实体类
 * Created by bowen on 2016-11-01 23:10
 */
public interface IGoodsPriceDao {
    /**
     * <pre>单个插入</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/11/1下午11:12
     *
    */
    int insert(GoodsPrice goodsPrice);
    /**
     * <pre>批量插入</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/11/1下午11:12
     *
    */
    int batchInsert(List<GoodsPrice> list);
}
