package com.common.dao.goods;

import com.common.dto.goods.GoodsSearchDto;
import com.common.entity.goods.Goods;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * Created by bowen on 2016-10-28 21:31
 */
public interface IGoodsDao {

    /**
     * <pre>插入商品</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:34
     *
    */
    int insert(@Param("goods") Goods goods);

    /**
     * <pre>删除上,根据主键</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:35
     *
    */
    int delete(@Param("goodsId") Integer goodsId);

    /**
     * <pre>修改对象</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:35
     *
     */
    int update(@Param("goods") Goods goods);

    /**
     * <pre>根据主键查找商品对象</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午9:32
     *
    */
    Goods findById(@Param("goodsId") Integer goodsId);

    /**
     * <pre>分页列表</pre>
     * @Version V1.0.0
     * @param   
     * @Retrun  
     * @Author  bowen
     * @CreateDate  2016/10/28下午10:02
     * 
    */
    List<Goods> findPageList(@Param("goodsSearchDto") GoodsSearchDto goodSearchDto);

    /**
     * <pre>总行数</pre>
     * @Version V1.0.0
     * @param
     * @Retrun
     * @Author  bowen
     * @CreateDate  2016/10/28下午10:04
     *
    */
    int findCount(@Param("goodsSearchDto") GoodsSearchDto goodsSearchDto);
}
