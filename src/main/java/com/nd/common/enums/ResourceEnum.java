package com.nd.common.enums;

/**
 * Created by ${zhiqiangXu}
 * on 2016/9/16 0016.
 */
public enum ResourceEnum {
    Three(3);//资源推荐的数量
    private Integer resourceNum;

    ResourceEnum(Integer resourceNum) {
        this.resourceNum = resourceNum;
    }

    public Integer getResourceNum() {
        return resourceNum;
    }
}
