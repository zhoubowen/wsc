package com.controller.goods;

import com.common.dto.goods.GoodsSearchDto;
import com.common.entity.goods.Goods;
import com.common.service.goods.IGoodsService;
import com.common.utils.page.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by bowen on 2016-10-29 10:55
 */
@Controller
@RequestMapping("/goods/")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;

    @RequestMapping("list")
    public String findPageList(Model model){
        PageInfo<Goods> pageInfo = goodsService.findPageList(new GoodsSearchDto());
        model.addAttribute("data", pageInfo);
        return "/goods/goods_list";
    }
}
