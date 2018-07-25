package com.jeffrey.util.exception;

import org.springframework.orm.ObjectRetrievalFailureException;

/**
 * @author Jeffrey.Liu
 * @date 2018-07-25 10:37
 **/
public class NoResourceException  extends ObjectRetrievalFailureException {
    public NoResourceException(Class arg0, Object arg1){
        super(arg0, arg1);
    }
}
