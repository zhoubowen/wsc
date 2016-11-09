package com.common.entity.goods;

import com.common.entity.picfile.PicFile;

import java.util.Date;

/**
 * 商品主表实体类
 * @Version V1.0.0
 * @Author  bowen
 * @CreateDate  2016/10/28下午9:08
 *
*/
public class Goods {
    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 主标题
     */
    private String goodsTitle;

    /**
     * 副标题
     */
    private String goodsSubTitle;

    /**
     * 主图
     */
    private PicFile goodsPicFile;

    /**
     * 图文详情
     */
    private String goodsDetailHtml;

    /**
     * 商品详情
     */
    private String goodsDetailHtmlUrl;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 最后更新时间
     */
    private Date lastUpdateDate;

    /**
     * 是否上线
     */
    private Boolean goodsOnline;

    /**
     * 季节ID
     */
    private Integer goodsSeasonId;

    /**
     * 一级类目
     */
    private Integer goodsCategoryFirst;

    /**
     * 二级类目
     */
    private Integer goodsCategorySecond;

    /**
     * 三级类目
     */
    private Integer goodsCategoryThird;
    private GoodsListPrice goodsListPrice; //列表价格实体,仅作用列表


    public Integer getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }
    public String getGoodsTitle() {
        return goodsTitle;
    }
    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
    }
    public String getGoodsSubTitle() {
        return goodsSubTitle;
    }
    public void setGoodsSubTitle(String goodsSubTitle) {
        this.goodsSubTitle = goodsSubTitle == null ? null : goodsSubTitle.trim();
    }
    public PicFile getGoodsPicFile() {
        return goodsPicFile;
    }
    public void setGoodsPicFile(PicFile goodsPicFile) {
        this.goodsPicFile = goodsPicFile;
    }
    public String getGoodsDetailHtml() {
        return goodsDetailHtml;
    }
    public void setGoodsDetailHtml(String goodsDetailHtml) {
        this.goodsDetailHtml = goodsDetailHtml == null ? null : goodsDetailHtml.trim();
    }
    public String getGoodsDetailHtmlUrl() {
        return goodsDetailHtmlUrl;
    }
    public void setGoodsDetailHtmlUrl(String goodsDetailHtmlUrl) {
        this.goodsDetailHtmlUrl = goodsDetailHtmlUrl == null ? null : goodsDetailHtmlUrl.trim();
    }
    public Date getCreateDate() {
        return createDate;
    }
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }
    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
    public Boolean getGoodsOnline() {
        return goodsOnline;
    }
    public void setGoodsOnline(Boolean goodsOnline) {
        this.goodsOnline = goodsOnline;
    }
    public Integer getGoodsSeasonId() {
        return goodsSeasonId;
    }
    public void setGoodsSeasonId(Integer goodsSeasonId) {
        this.goodsSeasonId = goodsSeasonId;
    }
    public Integer getGoodsCategoryFirst() {
        return goodsCategoryFirst;
    }
    public void setGoodsCategoryFirst(Integer goodsCategoryFirst) {
        this.goodsCategoryFirst = goodsCategoryFirst;
    }
    public Integer getGoodsCategorySecond() {
        return goodsCategorySecond;
    }
    public void setGoodsCategorySecond(Integer goodsCategorySecond) {
        this.goodsCategorySecond = goodsCategorySecond;
    }
    public Integer getGoodsCategoryThird() {
        return goodsCategoryThird;
    }
    public void setGoodsCategoryThird(Integer goodsCategoryThird) {
        this.goodsCategoryThird = goodsCategoryThird;
    }

    public GoodsListPrice getGoodsListPrice() {
        return goodsListPrice;
    }

    public void setGoodsListPrice(GoodsListPrice goodsListPrice) {
        this.goodsListPrice = goodsListPrice;
    }
}