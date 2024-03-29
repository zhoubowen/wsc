package com.common.entity.goods;

import java.util.Date;

/**
 * SKU
 * @Version V1.0.0
 * @Author  bowen
 * @CreateDate  2016/10/28下午9:09
 *
*/
public class GoodsItem {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.item_id
     *
     * @mbggenerated
     */
    private Integer itemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.item_name
     *
     * @mbggenerated
     */
    private String itemName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.goods_id
     *
     * @mbggenerated
     */
    private Integer goodsId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.item_pic_id
     *
     * @mbggenerated
     */
    private Integer itemPicId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.item_color_id
     *
     * @mbggenerated
     */
    private Integer itemColorId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.item_size_id
     *
     * @mbggenerated
     */
    private Integer itemSizeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.create_date
     *
     * @mbggenerated
     */
    private Date createDate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_goods_item.laset_update_date
     *
     * @mbggenerated
     */
    private Date lasetUpdateDate;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.item_id
     *
     * @return the value of t_goods_item.item_id
     *
     * @mbggenerated
     */
    public Integer getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.item_id
     *
     * @param itemId the value for t_goods_item.item_id
     *
     * @mbggenerated
     */
    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.item_name
     *
     * @return the value of t_goods_item.item_name
     *
     * @mbggenerated
     */
    public String getItemName() {
        return itemName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.item_name
     *
     * @param itemName the value for t_goods_item.item_name
     *
     * @mbggenerated
     */
    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.goods_id
     *
     * @return the value of t_goods_item.goods_id
     *
     * @mbggenerated
     */
    public Integer getGoodsId() {
        return goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.goods_id
     *
     * @param goodsId the value for t_goods_item.goods_id
     *
     * @mbggenerated
     */
    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.item_pic_id
     *
     * @return the value of t_goods_item.item_pic_id
     *
     * @mbggenerated
     */
    public Integer getItemPicId() {
        return itemPicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.item_pic_id
     *
     * @param itemPicId the value for t_goods_item.item_pic_id
     *
     * @mbggenerated
     */
    public void setItemPicId(Integer itemPicId) {
        this.itemPicId = itemPicId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.item_color_id
     *
     * @return the value of t_goods_item.item_color_id
     *
     * @mbggenerated
     */
    public Integer getItemColorId() {
        return itemColorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.item_color_id
     *
     * @param itemColorId the value for t_goods_item.item_color_id
     *
     * @mbggenerated
     */
    public void setItemColorId(Integer itemColorId) {
        this.itemColorId = itemColorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.item_size_id
     *
     * @return the value of t_goods_item.item_size_id
     *
     * @mbggenerated
     */
    public Integer getItemSizeId() {
        return itemSizeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.item_size_id
     *
     * @param itemSizeId the value for t_goods_item.item_size_id
     *
     * @mbggenerated
     */
    public void setItemSizeId(Integer itemSizeId) {
        this.itemSizeId = itemSizeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.create_date
     *
     * @return the value of t_goods_item.create_date
     *
     * @mbggenerated
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.create_date
     *
     * @param createDate the value for t_goods_item.create_date
     *
     * @mbggenerated
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_goods_item.laset_update_date
     *
     * @return the value of t_goods_item.laset_update_date
     *
     * @mbggenerated
     */
    public Date getLasetUpdateDate() {
        return lasetUpdateDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_goods_item.laset_update_date
     *
     * @param lasetUpdateDate the value for t_goods_item.laset_update_date
     *
     * @mbggenerated
     */
    public void setLasetUpdateDate(Date lasetUpdateDate) {
        this.lasetUpdateDate = lasetUpdateDate;
    }
}