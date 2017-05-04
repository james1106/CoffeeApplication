package com.mk.coffee.service;

import com.mk.coffee.model.CustomConfig;

import java.util.List;

/**
 * 自定义配方
 * Created by Administrator on 2017/5/4 0004.
 */
public interface CustomConfigService {
    //得到系统推荐配制
    List<CustomConfig> getCustomConfigListByRecommend();

    //添加配制
    boolean addCustomConfig(CustomConfig customConfig);

    //删除配制
    boolean deleteCustomConfigById(int customConfigId);


    //更新配制
    boolean updateCustomConfig(CustomConfig customConfig);
}
