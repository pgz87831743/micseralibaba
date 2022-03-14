package aiedu.nacos.security;

import aiedu.nacos.security.auth.UserDetailsImpl;
import aiedu.nacos.security.util.JwtUtils;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengmf
 * @since 2022/1/4
 */
public class BasicTest {


    @Test
    void testPasswordEncoding() {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken("user", "doesnotmatter", AuthorityUtils.createAuthorityList("ROLE_USER"));
        context.setAuthentication(authentication);

        SimpleAsyncTaskExecutor delegateExecutor =
                new SimpleAsyncTaskExecutor();
        DelegatingSecurityContextExecutor executor =
                new DelegatingSecurityContextExecutor(delegateExecutor, context);

        Runnable originalRunnable = new Runnable() {
            public void run() {
                System.out.println("// invoke secured service");
                System.out.println(context.getAuthentication().getName());
            }
        };


        executor.execute(originalRunnable);
        System.out.println("21321");
    }


    @Test
    void testJwt() throws Exception{


    }
}
