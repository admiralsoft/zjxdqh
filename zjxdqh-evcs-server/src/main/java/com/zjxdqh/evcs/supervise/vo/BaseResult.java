package com.zjxdqh.evcs.supervise.vo;

import lombok.Data;

/**
 * @author Yorking
 * @date 2019/05/07
 */
@Data
public class BaseResult<T> {

    private Object Data;
    private String Sig;

}
