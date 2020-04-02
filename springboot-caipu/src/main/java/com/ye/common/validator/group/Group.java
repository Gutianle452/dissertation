package com.ye.common.validator.group;

import javax.validation.GroupSequence;

/**
 * 定义校验顺序，如果AddGroup组失败，则UpdateGroup组不会再校验
 * @author ye
 *  
 * @date 2017-03-15 23:15
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}