package aiedu.nacos.security.filters;

import aiedu.nacos.security.auth.UserDetailsImpl;
import aiedu.nacos.security.properties.ConfigProperties;
import aiedu.nacos.security.util.JwtUtils;
import aiedu.nacos.security.util.ResponseUtils;
import io.jsonwebtoken.impl.DefaultClaims;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengmf
 * @since 2022/1/12
 */
@RequiredArgsConstructor
@Component
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION = "Authorization";
    private final JwtUtils jwtUtils;
    private final ConfigProperties configProperties;
    private final AntPathMatcher matcher = new AntPathMatcher();

    @Value("${server.servlet.context-path}")
    private String contextPath;


    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        check(httpServletRequest, httpServletResponse);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


    private void check(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        String requestURI = httpServletRequest.getRequestURI();
        for (String uri : configProperties.getIgnoreUri()) {
            if (matcher.match(contextPath + uri, requestURI)) {
                return;
            }
        }
        String authorization = httpServletRequest.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(authorization)) {
            try {
                DefaultClaims claims = (DefaultClaims) jwtUtils.parseToken(authorization);
                String username = claims.get("username", String.class);

                List<GrantedAuthority> authorities = Stream.of("admin", "role").map(s -> (GrantedAuthority) () -> s).collect(Collectors.toList());
                UserDetailsImpl details = new UserDetailsImpl(username,
                        null,
                        authorities);
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(details, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } catch (Exception e) {
                SecurityContextHolder.getContext().setAuthentication(null);
                ResponseUtils.fail(httpServletResponse, HttpStatus.INTERNAL_SERVER_ERROR, "token不合法");
            }
        }

    }
}
