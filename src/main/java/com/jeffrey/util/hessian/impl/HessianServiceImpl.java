package com.jeffrey.util.hessian.impl;

import com.jeffrey.util.hessian.HessianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class HessianServiceImpl implements HessianService {

    private String param;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void doSomething(String param) {
        if (this.param == null) {
            this.param = param;
        }
        logger.info(LocalDateTime.now() + " : " + this.param);
    }
}
