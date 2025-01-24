package com.maki.springCampsite.config;

import com.maki.springCampsite.usecase.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter, CustomOAuth2UserService customOAuth2UserService, OAuth2AuthenticationSuccessHandler oauth2AuthenticationSuccessHandler) throws Exception {
        http
                .cors(cors -> {
                    cors.configurationSource(request -> {
                        var corsConfiguration = new org.springframework.web.cors.CorsConfiguration();
                        corsConfiguration.addAllowedOriginPattern("*");
                        corsConfiguration.addAllowedMethod("*");
                        corsConfiguration.addAllowedHeader("*");
                        return corsConfiguration;
                    });
                })
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth.
                                requestMatchers(HttpMethod.POST, "/login").permitAll()
                                .requestMatchers(HttpMethod.POST, "/sign-up").permitAll()
                                .requestMatchers(HttpMethod.GET, "/user").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/user/**").hasRole("USER")
                                .requestMatchers(HttpMethod.POST, "/campsite").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.PUT, "/campsite").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/campsite").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/campsite").permitAll()
                                .requestMatchers(HttpMethod.GET, "/campsite/criteria").permitAll()
                                .anyRequest().authenticated()
                )

                .exceptionHandling(exceptionHandling ->
                        exceptionHandling.accessDeniedHandler(customAccessDeniedHandler())
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/oauth2/authorization/google") // 自訂登入頁面
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri("/callback") // 指定自定義的處理路徑
                        )
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // 使用自訂的 OAuth2 UserService
                        )
                        .successHandler(oauth2AuthenticationSuccessHandler)
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AccessDeniedHandler customAccessDeniedHandler() {
        return (request, response, accessDeniedException) -> {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\": \"Access Denied\"}");
        };
    }

}
