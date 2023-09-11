package com.hiufestainfo.global.config.security.filter;

import com.hiufestainfo.global.exception.AccessDeniedRequestException;
import com.hiufestainfo.global.utils.FilterExceptionProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private final FilterExceptionProcessor filterExceptionProcessor;

    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException accessDeniedException) throws IOException, ServletException {
        /* 예외처리를 커스터마이징 하는 용도 */
        filterExceptionProcessor.excute(response, new AccessDeniedRequestException());
    }
}