package com.hiufestainfo.global.config.security.filter;

import com.hiufestainfo.global.exception.base.BaseException;
import com.hiufestainfo.global.jwt.JwtProvider;
import com.hiufestainfo.global.jwt.dto.DecodedJwtToken;
import com.hiufestainfo.global.utils.FilterExceptionProcessor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.hiufestainfo.global.constant.StaticValue.ACCESS_TOKEN;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    private final FilterExceptionProcessor filterExceptionProcessor;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            String token = resolveToken(request);
            if (token != null) {
                Authentication authentication = getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication); //SecurityContextHolder에 담기
            }
            chain.doFilter(request, response);
        }catch (BaseException e){
            filterExceptionProcessor.excute(response, e);
        }
    }
    private Authentication getAuthentication(String token) {
        DecodedJwtToken decodedJwtToken = jwtProvider.decodeToken(token,ACCESS_TOKEN);

        Long userId = decodedJwtToken.getUserId();
        String role = decodedJwtToken.getRole();

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role));

        return new UsernamePasswordAuthenticationToken(userId, null, authorities);
    }
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
