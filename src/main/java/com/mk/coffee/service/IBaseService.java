package com.mk.coffee.service;

import java.util.List;

/**
 * 基本的增删查改
 * Created by Administrator on 2017/5/4 0004.
 */
public interface IBaseService<T> {
    List<T> getList();

    T getItem(int id);

    boolean updateItem(T t);

    boolean deleteItem(int id);

    boolean addItem(T t);
}
