package gr.aueb.cf.finalproject.security;

import gr.aueb.cf.finalproject.security.filter.AuthenticationFilter;
import gr.aueb.cf.finalproject.security.filter.ExceptionFilter;
import gr.aueb.cf.finalproject.security.filter.JWTAuthorizationFilter;
import gr.aueb.cf.finalproject.security.manager.CustomAuthenticationManager;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private CustomAuthenticationManager authenticationManager;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "userRest/register").permitAll()
                        .requestMatchers(HttpMethod.GET, "/", "/movies/**", "/formUpdateMovie/**", "/about-us", "/delete/**", "/signOut", "/like/**", "/hate/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/registerUser", "/registerUser", "/addMovie", "/loginUser", "/update/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "").permitAll()
                        .requestMatchers("/styles/**").permitAll()
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/images/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(new ExceptionFilter(), AuthenticationFilter.class)
                .addFilter(authenticationFilter)
                .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

}
