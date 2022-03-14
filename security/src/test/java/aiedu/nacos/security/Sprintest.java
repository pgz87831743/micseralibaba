package aiedu.nacos.security;

import aiedu.nacos.security.auth.UserDetailsImpl;
import aiedu.nacos.security.util.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengmf
 * @since 2022/1/13
 */
@SpringBootTest(classes = ApplicationSecurity.class)
public class Sprintest {

    private UserDetailsImpl details;

    @BeforeEach
    void setUp() {
        details = new UserDetailsImpl("pmf",
                new BCryptPasswordEncoder().encode("123456"),
                Stream.of("admin", "role").map(s -> (GrantedAuthority) () -> s).collect(Collectors.toList()));
    }

    @Resource
    private JwtUtils jwtUtils;

    @Test
    void testJwt() {
        System.out.println(jwtUtils.createAccessToken(details));
        System.out.println(jwtUtils.createRefreshToken(details));
    }
}
