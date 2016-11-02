package com.common.service.picfile.impl;

import com.common.dao.picfile.IPicFileDao;
import com.common.entity.picfile.PicFile;
import com.common.service.picfile.IPicFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bowen on 2016-11-01 15:26
 */
@Service
public class PicFileServiceImpl implements IPicFileService {

    @Autowired
    private IPicFileDao picFileDao;

    @Override
    public int insert(PicFile picFile) {
        return picFileDao.insert(picFile);
    }
}
