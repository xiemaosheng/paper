package com.nd.util;

import com.nd.common.exception.NiceErrorCodes;
import com.nd.common.exception.NiceException;

/**
 * 类名称：
 * 类描述：
 * 创建人：tq
 * 创建日期：2016/9/22 0022
 */
public class PageReq {
    private Integer page_num = 0;
    private Integer page_size = 8;

    public Integer getPage_num() {
        return page_num;
    }

    public void setPage_num(Integer page_num) {
        this.page_num = page_num;
    }

    public Integer getPage_size() {
        return page_size;
    }

    public void setPage_size(Integer page_size) {
        this.page_size = page_size;
    }

    public void check(){
        if(page_num<0|| page_size<0){
            throw new NiceException(NiceErrorCodes.BAD_REQUEST);
        }
    }
}
