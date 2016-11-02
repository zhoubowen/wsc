package com.common.entity.goods;

import java.util.Date;

public class GoodsPrice {
    /**
     * 开始时间
     */
    private Date begianDay;

    /**
     * 商品ID
     */
    private Integer goodsId;

    /**
     * 商品规格ID
     */
    private Integer goodsItemId;
    /**
     * 结束日期
     */
    private Date endDay;

    /**
     * 价格,单位分
     */
    private Integer price;

    /**
     * 开始时间
     */
    private Date createDate;
    /*
    * 是否删除 0否,1是 默认0
    * */
    private Boolean status;
    private Integer serial; //自增序号,无业务含义

    public Integer getSerial() {
        return serial;
    }

    public void setSerial(Integer serial) {
        this.serial = serial;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.end_day
     *
     * @return the value of t_goods_price.end_day
     *
     * @mbggenerated
     */

    public Date getEndDay() {
        return endDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.end_day
     *
     * @param endDay the value for t_goods_price.end_day
     *
     * @mbggenerated
     */
    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.price
     *
     * @return the value of t_goods_price.price
     *
     * @mbggenerated
     */
    public Integer getPrice() {
        return price;
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.begian_day
     *
     * @return the value of t_goods_price.begian_day
     *
     * @mbggenerated
     */
    public Date getBegianDay() {
        return begianDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.begian_day
     *
     * @param begianDay the value for t_goods_price.begian_day
     *
     * @mbggenerated
     */
    public void setBegianDay(Date begianDay) {
        this.begianDay = begianDay;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.goods_id
     *
     * @return the value of t_goods_price.goods_id
     *
     * @mbggenerated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.goods_id
     *
     * @param goodsId the value for t_goods_price.goods_id
     *
     * @mbggenerated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.goods_item_id
     *
     * @return the value of t_goods_price.goods_item_id
     *
     * @mbggenerated
     */
    public Integer getGoodsItemId() {
        return goodsItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.goods_item_id
     *
     * @param goodsItemId the value for t_goods_price.goods_item_id
     *
     * @mbggenerated
     */
    public void setGoodsItemId(Integer goodsItemId) {
        this.goodsItemId = goodsItemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.price
     *
     * @param price the value for t_goods_price.price
     *
     * @mbggenerated
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_price.create_date
     *
     * @return the value of t_goods_price.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_price.create_date
     *
     * @param createDate the value for t_goods_price.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}