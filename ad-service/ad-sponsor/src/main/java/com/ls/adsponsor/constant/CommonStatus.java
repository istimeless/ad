package com.ls.adsponsor.constant;

import lombok.Getter;

/**
 * @author lijiayin
 */
@Getter
public enum CommonStatus {

    /**
     * 状态
     */
    VALID(1, "有效状态"),
    INVALID(0, "无效状态"),
    ;
    
    private Integer status;
    
    private String desc;
    
    CommonStatus(Integer status, String desc){
        this.status = status;
        this.desc = desc;
    }
}
