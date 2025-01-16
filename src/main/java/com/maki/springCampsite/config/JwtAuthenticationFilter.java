package com.maki.springCampsite.config;

import com.maki.springCampsite.usecase.UserService;
import com.maki.springCampsite.utils.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @NonNull
    private JwtTokenUtil jwtTokenUtil;

    @NonNull
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        if (StringUtils.isEmpty(requestTokenHeader) || !StringUtils.startsWith(requestTokenHeader, "Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        try {
            String jwtToken = requestTokenHeader.substring(7);
            Claims claims = jwtTokenUtil.validateToken(jwtToken);
            String userId = claims.getSubject();

            // Once we get the token validate it.
            if (userId != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails user = userService.loadUserByUsername(userId);
                // Set the security context
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userId, null, user.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            }
        } catch (Exception e) {
            logger.error("Invalid token: {}", e);
            throw new SecurityException("Invalid token: " + e.getMessage());
        }

        chain.doFilter(request, response);
    }
}

