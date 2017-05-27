package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.CustomConfigMapper;
import com.mk.coffee.model.CustomConfig;
import com.mk.coffee.model.CustomConfigExample;
import com.mk.coffee.service.CustomConfigService;
import com.mk.coffee.utils.EmptyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
@Service
public class CustomConfigServiceImpl implements CustomConfigService {
    @Autowired
    private CustomConfigMapper customConfigMapper;

    @Override
    public List<CustomConfig> getCustomConfigListByRecommend() {
        CustomConfigExample example = new CustomConfigExample();
        example.createCriteria().andIsRecommendEqualTo(true);
        List<CustomConfig> list = customConfigMapper.selectByExample(example);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return list;
    }

    @Override
    public boolean addCustomConfig(CustomConfig customConfig) {
        return customConfigMapper.insert(customConfig) > 0;
    }

    @Override
    public boolean deleteCustomConfigById(int customConfigId) {
        CustomConfigExample example = new CustomConfigExample();
        example.createCriteria().andIdEqualTo(customConfigId);
        return customConfigMapper.deleteByExample(example) > 0;
    }

    @Override
    public boolean updateCustomConfig(CustomConfig customConfig) {
        CustomConfigExample example = new CustomConfigExample();
        example.createCriteria().andIdEqualTo(customConfig.getId());
        return customConfigMapper.updateByExample(customConfig, example) > 0;
    }

    @Override
    public CustomConfig getProductConfigByProductId(int productId) {

        CustomConfig customConfig = customConfigMapper.getProductConfigByProductId(productId);
        if (customConfig == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return customConfig;

    }


    @Override
    public List<CustomConfig> getList() {
        return customConfigMapper.selectByExample(null);
    }

    @Override
    public CustomConfig getItem(int id) {
        return customConfigMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(CustomConfig customConfig) {
        return customConfigMapper.updateByPrimaryKey(customConfig) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return customConfigMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(CustomConfig customConfig) {
        return customConfigMapper.insert(customConfig) > 0;
    }
}
