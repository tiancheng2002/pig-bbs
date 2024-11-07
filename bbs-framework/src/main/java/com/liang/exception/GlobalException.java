package com.liang.exception;

import com.liang.result.RespBean;
import com.liang.result.RespBeanEnum;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler({SQLIntegrityConstraintViolationException.class})
    public RespBean uniqueData(SQLIntegrityConstraintViolationException e){
        return RespBean.error(RespBeanEnum.EMAIL_EXIST);
    }

}
