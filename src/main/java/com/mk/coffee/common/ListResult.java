package com.mk.coffee.common;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24 0024.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResult<T> implements Serializable {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<T> rows;
    private long total;//纪录总数量

    public ListResult() {
    }

    public ListResult(List<T> rows, long total) {
        this.rows = rows;
        this.total = total;
    }

    public ListResult(List<T> rows) {
        this.rows = rows;
        this.total = rows.size();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "ListResult{" +
                "total=" + total +
                ", rows=" + rows +
                '}';
    }
}
