package com.hxyk.mijia.utils;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by Rico on 2017/6/29.
 */
@Service
public class QiniuUploadServiceImpl implements QiniuUploadService {


    private static  final String ACCESS_KEY = "RT2r0qxRFiBCR25TlIkLLcWfrKEZuQAD9V0XHsO7";
    private static final String SECRET_KEY = "RYaqcpeWVyT2B-2TQnlXXFmKz34M7NUwzuMsjDVO";
    private static final String BUCKET_NAME = "mijia";
    private Auth auth=null;

    public QiniuUploadServiceImpl() {
        this.auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    }


    @Override
    public String getUploadToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    public void removeFile(String bucket,String key) {
        Configuration cfg = new Configuration(Zone.zone0());
        //实例化一个BucketManager对象
        BucketManager bucketManager = new BucketManager(auth,cfg);
        //要测试的空间和key，并且这个key在你空间中存在
        //String bucket    你的七牛用户创建的空间名
      //  String key       七牛空间中文件名称
        try {
            //调用delete方法移动文件
            bucketManager.delete(bucket,key);
        } catch (QiniuException e) {

        }
    }
}
