package com.common.service.goods;

import com.common.entity.goods.GoodsPrice;

import java.util.List;

/**
 * Created by bowen on 2016-11-02 22:04
 */
public interface IGoodsPriceService {

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
