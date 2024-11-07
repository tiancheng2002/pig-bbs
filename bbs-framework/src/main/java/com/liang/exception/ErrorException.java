package com.liang.exception;

import com.liang.result.RespBeanEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorException extends RuntimeException {

    RespBeanEnum respBeanEnum;

}