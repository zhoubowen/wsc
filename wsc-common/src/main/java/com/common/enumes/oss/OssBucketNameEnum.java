package com.common.enumes.oss;

/**
 * OSS BucketName枚举
 * Created by bowen on 2016-11-01 14:34
 */
public enum OssBucketNameEnum {

    GOODS_IMG(0, "wsc-goods-img", "商品图");

    private Integer code;
    private String bucketName;
    private String desc;

    OssBucketNameEnum(Integer code, String bucketName, String desc){
        this.bucketName = bucketName;
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
