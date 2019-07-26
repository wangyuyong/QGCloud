package com.wyy.qgcloud.base;

public interface BasePresent<T> {
    void bindView(T view);

    void unbindView();
}
