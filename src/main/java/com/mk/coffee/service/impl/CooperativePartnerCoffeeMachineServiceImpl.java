package com.mk.coffee.service.impl;

import com.mk.coffee.mapper.CooperativePartnerCoffeeMachineMapper;
import com.mk.coffee.mapper.CooperativePartnerProductMapper;
import com.mk.coffee.model.CooperativePartnerCoffeeMachine;
import com.mk.coffee.model.CooperativePartnerProduct;
import com.mk.coffee.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/5/7 0007.
 */
@Service
public class CooperativePartnerCoffeeMachineServiceImpl implements IBaseService<CooperativePartnerCoffeeMachine> {
    private CooperativePartnerCoffeeMachineMapper cooperativePartnerCoffeeMachineMapper;

    @Override
    public List<CooperativePartnerCoffeeMachine> getList() {
        return cooperativePartnerCoffeeMachineMapper.selectByExample(null);
    }

    @Override
    public CooperativePartnerCoffeeMachine getItem(int id) {
        return cooperativePartnerCoffeeMachineMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(CooperativePartnerCoffeeMachine cooperativePartnerCoffeeMachine) {
        return cooperativePartnerCoffeeMachineMapper.updateByPrimaryKey(cooperativePartnerCoffeeMachine) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return cooperativePartnerCoffeeMachineMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(CooperativePartnerCoffeeMachine cooperativePartnerCoffeeMachine) {
        return cooperativePartnerCoffeeMachineMapper.insert(cooperativePartnerCoffeeMachine) > 0;
    }
}
