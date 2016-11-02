package com.utils;

import com.BaseTest;
import com.common.entity.picfile.PicFile;
import com.common.enumes.oss.OssBucketNameEnum;
import com.common.service.picfile.IPicFileService;
import com.common.utils.oss.OssUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.util.UUID;

/**
 * Created by bowen on 2016-11-01 14:43
 */
public class OssUtilTest extends BaseTest {

    @Autowired
    private IPicFileService picFileService;

    @Test
    public void uploadFileTest(){
        try {
            String fileName = UUID.randomUUID() + ".jpg";
            OssUtil.putObject(OssBucketNameEnum.GOODS_IMG.getBucketName(), fileName, "/Users/zbw/Downloads/h1.jpg");

            picFileService.insert(new PicFile(fileName, OssBucketNameEnum.GOODS_IMG.getBucketName()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
