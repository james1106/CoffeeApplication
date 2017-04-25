package com.mk.coffee.utils;

import com.google.gson.Gson;
import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.conf.qiniu.QiNiuProperties;
import com.mk.coffee.exception.AppException;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.BatchStatus;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 七牛工具类
 * Created by Administrator on 2017/3/3 0003.
 */
@Component
public class QiNiuUtils {
    @Autowired
    QiNiuProperties qiNiuProperties;

    public String updateLoadFile(byte[] bytes) {
        Configuration cfg = new Configuration(Zone.zone0());//配置
        UploadManager uploadManager = new UploadManager(cfg);
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
        String upToken = auth.uploadToken(qiNiuProperties.getBucketName());
        try {
            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
            return qiNiuProperties.getImageUrl() + putRet.key;
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            AppException.getException(ErrorCode.Upload_File_Failure.getCode());
        }
        return null;
    }

    /**
     * 批量删除图片
     *
     * @param keyList
     * @return
     * @throws AppException
     */
    public boolean delectFiles(List<String> keyList) throws AppException {
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiNiuProperties.getAccessKey(), qiNiuProperties.getSecretKey());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            BucketManager.BatchOperations batchOperations = new BucketManager.BatchOperations();
            batchOperations.addDeleteOp(qiNiuProperties.getBucketName(), (String[]) keyList.toArray());
            Response response = bucketManager.batch(batchOperations);
            BatchStatus[] batchStatusList = response.jsonToObject(BatchStatus[].class);
            for (int i = 0; i < keyList.size(); i++) {
                BatchStatus status = batchStatusList[i];
                String key = keyList.get(i);
                System.out.print(key + "\t");
                if (status.code == 200) {
                    System.out.println("delete success");
                } else {
                    System.out.println(status.code);
                }
            }
        } catch (QiniuException ex) {
            System.err.println(ex.response.toString());
        }
        return true;
    }
}
