package com.mk.coffee.service.impl;

import com.mk.coffee.common.ErrorCode;
import com.mk.coffee.common.ListResult;
import com.mk.coffee.common.RestResult;
import com.mk.coffee.common.RestResultGenerator;
import com.mk.coffee.exception.AppException;
import com.mk.coffee.mapper.CoffeeMachineMapper;
import com.mk.coffee.mapper.LocalAuthMapper;
import com.mk.coffee.model.CoffeeMachine;
import com.mk.coffee.model.CoffeeMachineExample;
import com.mk.coffee.service.CoffeeMachineService;
import com.mk.coffee.utils.DistanceUtils;
import com.mk.coffee.utils.EmptyUtils;
import com.mk.coffee.utils.VerifyUtils;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class CoffeeMachineServiceImpl implements CoffeeMachineService {
    @Autowired
    private CoffeeMachineMapper coffeeMachineMapper;


    /**
     * 根据经纬度和半径得到附近的咖啡机列表
     *
     * @param longitude
     * @param latitude
     * @param radius
     * @return
     */
    public ListResult<CoffeeMachine> getCoffeeMachinesByLonLat(double longitude, double latitude, float radius) throws ApiException {
        double[] doubles = DistanceUtils.getSquarePoint(longitude, latitude, radius);
        List<CoffeeMachine> coffeeMachines = coffeeMachineMapper.getNearbyCoffeesMachine(doubles[0], doubles[1], doubles[2], doubles[3]);
        if (coffeeMachines == null || coffeeMachines.size() == 0) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return new ListResult<>(coffeeMachines);

    }

    @Override
    public CoffeeMachine getCoffeeMachine(int id) {
        CoffeeMachine coffeeMachine = coffeeMachineMapper.selectByPrimaryKey(id);
        if (coffeeMachine == null) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA.getCode());
        }
        return coffeeMachine;
    }

    @Override
    public List<CoffeeMachine> searchCoffeeMachine(String keyword) {
        CoffeeMachineExample example = null;
        if (keyword != null && !keyword.equals("")) {
            example = new CoffeeMachineExample();
            example.or().andCodeLike("%" + keyword + "%");
            example.or().andAddressLike("%" + keyword + "%");
        }
        List<CoffeeMachine> list = coffeeMachineMapper.selectByExample(example);
        if (EmptyUtils.isEmpty(list)) {
            throw AppException.getException(ErrorCode.NOT_FOUND_DATA);
        }
        return list;
    }

    @Override
    public List<CoffeeMachine> getList() {
        return coffeeMachineMapper.getAllCoffeesMachine();
    }

    @Override
    public CoffeeMachine getItem(int id) {
        return coffeeMachineMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean updateItem(CoffeeMachine coffeeMachine) {
        return coffeeMachineMapper.updateByPrimaryKeySelective(coffeeMachine) > 0;
    }

    @Override
    public boolean deleteItem(int id) {
        return coffeeMachineMapper.deleteByPrimaryKey(id) > 0;
    }

    @Override
    public boolean addItem(CoffeeMachine coffeeMachine) {
        CoffeeMachineExample example = new CoffeeMachineExample();
        example.createCriteria().andCodeEqualTo(coffeeMachine.getCode());
        if (!EmptyUtils.isEmpty(coffeeMachineMapper.selectByExample(example))) {
            throw AppException.getException(ErrorCode.CoffeesMachine_Code_Already_Exist);
        }
        coffeeMachine.setCreateDate(new Date());
        return coffeeMachineMapper.insertSelective(coffeeMachine) > 0;
    }
}
