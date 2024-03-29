package com.ls.adcommon.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author lijiayin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> implements Serializable {
    
    private Integer code;
    
    private String message;
    
    private T data;
    
    public CommonResponse(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    
}
