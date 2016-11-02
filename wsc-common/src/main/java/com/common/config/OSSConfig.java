package com.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * OSS 配置
 * Created by bowen on 2016-11-01 10:24
 */
@ConfigurationProperties(prefix = "oss",locations = "classpath:config/oss.properties")
public class OSSConfig {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }
}
