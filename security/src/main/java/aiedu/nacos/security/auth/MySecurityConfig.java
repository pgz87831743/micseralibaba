package aiedu.nacos.security.auth;

import aiedu.nacos.security.filters.JwtFilter;
import aiedu.nacos.security.filters.RestUsernamePasswordFilter;
import aiedu.nacos.security.properties.ConfigProperties;
import aiedu.nacos.security.util.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author pengmf
 * @since 2022/1/4
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class MySecurityConfig extends WebSecurityConfigurerAdapter {


    private final ObjectMapper objectMapper;
    private final UserDetailServerImpl userDetailServer;
    private final JwtUtils jwtUtils;
    private final JwtFilter jwtFilter;
    private final ConfigProperties configProperties;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(req -> req
                        .antMatchers(
                                configProperties.getIgnoreUri().toArray(new String[]{})).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterAt(restUsernamePasswordFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtFilter, RestUsernamePasswordFilter.class)
                .csrf(AbstractHttpConfigurer::disable);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailServer)
                .passwordEncoder(passwordEncoder);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private RestUsernamePasswordFilter restUsernamePasswordFilter() throws Exception {
        RestUsernamePasswordFilter restUsernamePasswordFilter = new RestUsernamePasswordFilter(objectMapper);
        restUsernamePasswordFilter.setAuthenticationSuccessHandler(getAuthenticationSuccessHandler());
        restUsernamePasswordFilter.setAuthenticationFailureHandler(getAuthenticationFailureHandler());
        restUsernamePasswordFilter.setAuthenticationManager(authenticationManager());
        restUsernamePasswordFilter.setFilterProcessesUrl("/login");
        return restUsernamePasswordFilter;
    }


    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return (request, response, exception) -> {


            log.error(exception.toString());
            response.getWriter().write(exception.toString());
        };
    }

    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            userDetails.setAccessToken(jwtUtils.createAccessToken(userDetails));
            objectMapper.writeValue(response.getOutputStream(), userDetails);
        };
    }


}
