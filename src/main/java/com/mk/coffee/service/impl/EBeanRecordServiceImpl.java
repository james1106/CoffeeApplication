package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.EbeanRecordMapper;
import com.mk.coffee.model.EbeanRecord;
import com.mk.coffee.service.EBeanRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
@Service
public class EBeanRecordServiceImpl implements EBeanRecordService {
    @Autowired
    private EbeanRecordMapper mapper;

    @Override
    public List<EbeanRecord> getList() {
        return mapper.selectByExample(null);
    }

    @Override
    public EbeanRecord getItem(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(EbeanRecord ebeanRecord) {
        return mapper.updateByPrimaryKey(ebeanRecord) > 0;
    }

    @Override
    public boolean deleteItem(long id) {
        return mapper.deleteByPrimaryKey(id)>0;
    }

    @Override
    public boolean addItem(EbeanRecord ebeanRecord) {
        return mapper.insert(ebeanRecord) > 0;
    }
}
