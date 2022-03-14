package aiedu.nacos.security.service.impl;

import aiedu.nacos.security.auth.UserDetailsImpl;
import aiedu.nacos.security.domain.UsernamePasswordDTO;
import aiedu.nacos.security.service.AuthServer;
import aiedu.nacos.security.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author pengmf
 * @since 2022/1/14
 */
@Service
@RequiredArgsConstructor
public class AuthServerImpl implements AuthServer {


    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    @Override
    public UserDetailsImpl login(UsernamePasswordDTO usernamePasswordDTO) {
        UserDetailsImpl details = new UserDetailsImpl(usernamePasswordDTO.getUsername(),
                passwordEncoder.encode(usernamePasswordDTO.getPassword()),
                Stream.of("admin", "role").map(s -> (GrantedAuthority) () -> s).collect(Collectors.toList()));

        details.setAccessToken(jwtUtils.createAccessToken(details));
        details.setRefreshToken(jwtUtils.createRefreshToken(details));
        return details;
    }
}
