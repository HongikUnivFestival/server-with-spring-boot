package com.hiufestainfo.global.config.security;

import com.hiufestainfo.global.config.security.filter.CustomAccessDeniedHandler;
import com.hiufestainfo.global.config.security.filter.FilterConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;


@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {
    private final FilterConfig filterConfig;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic().disable().cors();// cors 적용
        http.csrf().disable();
        http.formLogin().disable();
        http.sessionManagement( ).sessionCreationPolicy(SessionCreationPolicy.STATELESS); // JWT이용으로 세션 이용 x
        http.authorizeRequests().expressionHandler(expressionHandler());
        http.apply(filterConfig);

        http.authorizeRequests()
                .antMatchers(swaggerUrlPatterns)
                .permitAll()
                .mvcMatchers("/v1/auth/**")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/booths")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/foodtrucks/**")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/api/promotions/**")
                .permitAll()
                .mvcMatchers(HttpMethod.GET, "/pubs/**")
                .permitAll()
                .anyRequest().hasRole("ADMIN");
        return http.build();
    }


    @Bean
    public RoleHierarchyImpl roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER > ROLE_GUEST");
        return roleHierarchy;
    }

    @Bean
    public DefaultWebSecurityExpressionHandler expressionHandler() {
        DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
        expressionHandler.setRoleHierarchy(roleHierarchy());
        return expressionHandler;
    }
    private String[] swaggerUrlPatterns = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**"
    };
}
