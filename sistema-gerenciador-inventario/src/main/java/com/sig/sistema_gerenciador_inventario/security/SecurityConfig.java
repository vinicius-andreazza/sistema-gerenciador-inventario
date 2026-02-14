package com.sig.sistema_gerenciador_inventario.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sig.sistema_gerenciador_inventario.security.exception.JwtAuthenticationEntryPoint;
import com.sig.sistema_gerenciador_inventario.security.jwt.JwtAuthenticationFilter;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final CustomUserDetailsService customUserDetailsService;
    private final CustomLogoutHandler customLogoutHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .exceptionHandling(e -> e.authenticationEntryPoint(jwtAuthenticationEntryPoint))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .securityMatcher("/**")
                .logout((logout) -> 
                    logout.addLogoutHandler(customLogoutHandler)
                    .logoutSuccessHandler((request, response, authentication) ->
                        response.setStatus(HttpServletResponse.SC_OK)
                    )
                )
                .authorizeHttpRequests(
                    auth -> auth
                    .requestMatchers("/").permitAll()
                    .requestMatchers("/userDashboard").hasRole("ADMIN")
                    .requestMatchers("/supplierDashboard").permitAll()
                    .requestMatchers("/productDashboard").permitAll()
                    .requestMatchers("/rawMaterialDashboard").permitAll()

                    .requestMatchers("/users").hasRole("ADMIN")
                    .requestMatchers("/users/*").hasRole("ADMIN")
                    
                    .requestMatchers("/locals").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/locals/**").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/suppliers").permitAll()
                    .requestMatchers("/suppliers/**").permitAll()
                    .requestMatchers("/products").permitAll()
                    .requestMatchers("/products/**").permitAll()
                    .requestMatchers("/rawMaterials").hasAnyRole("ADMIN", "USER")
                    .requestMatchers("/rawMaterials/**").hasAnyRole("ADMIN", "USER")

                    .requestMatchers("/refreshToken").permitAll()
                    .requestMatchers("/login").permitAll()
                    
                    .anyRequest().permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(customUserDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
