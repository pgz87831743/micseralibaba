package aiedu.nacos.security.auth;

import aiedu.nacos.security.auth.UserDetailsImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengmf
 * @since 2022/1/11
 */
@Service
public class UserDetailServerImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UserDetailsImpl(username,
                new BCryptPasswordEncoder().encode("123456"),
                Stream.of("admin", "role").map(s -> (GrantedAuthority) () -> s).collect(Collectors.toList()));
    }
}
