package com.mk.coffee.requestbody;

import java.util.List;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
public class RequestCardIdList {
    public int offset;
    public int count;
    public List<String> status_list;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<String> getStatus_list() {
        return status_list;
    }

    public void setStatus_list(List<String> status_list) {
        this.status_list = status_list;
    }
}
