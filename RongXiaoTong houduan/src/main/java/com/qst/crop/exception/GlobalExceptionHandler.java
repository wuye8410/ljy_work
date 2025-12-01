package com.qst.crop.exception;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理表单验证错误
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("flag", false);

        // 提取字段错误信息
        StringBuilder errorMsg = new StringBuilder();
        BindingResult bindingResult = ex.getBindingResult();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMsg.append(error.getField()).append("：").append(error.getDefaultMessage()).append("；");
        }

        response.put("message", errorMsg.toString());
        return response;
    }
}