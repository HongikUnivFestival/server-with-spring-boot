package com.hiufestainfo.global.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiufestainfo.global.annotation.Processor;
import com.hiufestainfo.global.exception.AccessDeniedRequestException;
import com.hiufestainfo.global.exception.base.BaseException;
import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Processor
@RequiredArgsConstructor
public class FilterExceptionProcessor {

    private final ObjectMapper objectMapper;
    public void excute(HttpServletResponse response, BaseException e) throws IOException {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("isSuccess", false);
        errorResponse.put("message", e.getErrorCode().getMessage());
        errorResponse.put("code", e.getErrorCode().getCode());
        errorResponse.put("httpStatus", e.getErrorCode().getHttpStatus());

        response.setCharacterEncoding("UTF-8");
        response.setStatus(e.getErrorCode().getHttpStatus());
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
    }
}
