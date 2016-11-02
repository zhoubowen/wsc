package com.common.service.goods;

import com.common.dto.goods.GoodsSearchDto;
import com.common.entity.goods.Goods;
import com.common.utils.page.PageInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by bowen on 2016-10-28 22:08
 */
public interface IGoodsService {

    /**
     * <pre>插入商品</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:34
     *
     */
    int insert(Goods goods);

    /**
     * <pre>删除上,根据主键</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:35
     *
     */
    int delete(Integer goodsId);

    /**
     * <pre>修改对象</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:35
     *
     */
    int update(Goods goods);

    /**
     * <pre>根据主键查找商品对象</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:32
     *
     */
    Goods findById(Integer goodsId);

    /**
     * <pre>分页列表</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午10:02
     *
     */
    PageInfo<Goods> findPageList(GoodsSearchDto goodSearchDto);

}
