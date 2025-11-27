package com.qst.crop.security.filter;

import com.qst.crop.security.util.JwtTokenUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author QST
 * @Description 请求拦截器，根据传过来的token，进行认证
 * @Date 2021-08-24
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Resource
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = extractToken(request);
        
        if (!StringUtils.isEmpty(token)) {
            String username = jwtTokenUtil.getUsernameFromToken(token);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        // 1. 优先从请求头获取
        String token = request.getHeader(JwtTokenUtil.getHeader());

        // 2. 头中没有则从参数"token"获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("token");
        }

        // 3. 仍没有则从参数"Authorization"获取
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter("Authorization");
        }

        // 4. 处理"Bearer "前缀（用新变量接收，避免修改原token）
        if (!StringUtils.isEmpty(token) && token.startsWith("Bearer ")) {
            return token.substring(7); // 直接返回处理后的值
        }

        return token; // 无需修改时直接返回
    }
}
