package aiedu.nacos.security.service;

import aiedu.nacos.security.auth.UserDetailsImpl;
import aiedu.nacos.security.domain.UsernamePasswordDTO;

/**
 * @author pengmf
 * @since 2022/1/14
 */
public interface AuthServer {
    UserDetailsImpl login(UsernamePasswordDTO usernamePasswordDTO);

}
