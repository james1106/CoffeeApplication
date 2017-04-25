package com.mk.coffee.model;

import com.mk.coffee.common.BaseParameter;

/**
 * 制作咖啡的参数类
 * Created by Administrator on 2017/3/13 0013.
 */
public class MakeCoffeesParam extends BaseParameter {
    public String ID;
    public String PTYPE;//订单类型
    public String VMC;//机器ID
    public String PID;//产品类型
    public String FASTCODE;//快捷码，参照fastcode生成规则:口味控制由一个整数表示 ，百位表示咖啡或者茶的浓度(0-5)，十位表示奶的浓度(0-5)，个位表示糖的浓度(0-5)，如果没有则为0
    public String USERNAME;//用户名
    public String MAC;//校验，标准32位MD5

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPTYPE() {
        return PTYPE;
    }

    public void setPTYPE(String PTYPE) {
        this.PTYPE = PTYPE;
    }

    public String getVMC() {
        return VMC;
    }

    public void setVMC(String VMC) {
        this.VMC = VMC;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getFASTCODE() {
        return FASTCODE;
    }

    public void setFASTCODE(String FASTCODE) {
        this.FASTCODE = FASTCODE;
    }

    public String getUSERNAME() {
        return USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public String getMAC() {
        return MAC;
    }

    public void setMAC(String MAC) {
        this.MAC = MAC;
    }


    @Override
    public String toString() {
        return "MakeCoffeesParam{" +
                "ID='" + ID + '\'' +
                ", PTYPE='" + PTYPE + '\'' +
                ", VMC='" + VMC + '\'' +
                ", PID='" + PID + '\'' +
                ", FASTCODE='" + FASTCODE + '\'' +
                ", USERNAME='" + USERNAME + '\'' +
                ", MAC='" + MAC + '\'' +
                '}';
    }
}
