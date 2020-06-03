package com.hotel.core.utils;

public class PageVO {

    private int limit = 10; // 每页显示记录数
    private int page = 1; // 当前页

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public PageVO() {
    }

    public PageVO(int page, int limit) {
        if (page > 0) {
            this.page = page;
        }
        if (limit > 0) {
            this.limit = limit;
        }
    }

    public int getBeginNum() {
        int begin = (page - 1) * limit;
        return begin;
    }

    public int getEndNum() {
        int begin = page * limit;
        return begin;
    }

}
