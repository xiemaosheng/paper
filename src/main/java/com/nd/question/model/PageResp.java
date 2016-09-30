package com.nd.question.model;

import java.util.List;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/13 0013.
 */
public class PageResp<T> {
    private Long total;
    private List<T> items;


    public PageResp(Long total, List<T> items) {
        this.total = total;
        this.items = items;
    }

    public PageResp() {

    }

    public Long getTotal() {
        return total;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
