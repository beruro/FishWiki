package com.hanafish.hanawiki.resp;

import java.util.List;

public class PageResq<T> {
    private long total;

    private List<T> list;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageResq{" +
                "total=" + total +
                ", list=" + list +
                '}';
    }
}
