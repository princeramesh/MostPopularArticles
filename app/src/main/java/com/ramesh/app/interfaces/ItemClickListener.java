package com.ramesh.app.interfaces;

public interface ItemClickListener<T> {
    void onItemClick(int position, T model);
}
