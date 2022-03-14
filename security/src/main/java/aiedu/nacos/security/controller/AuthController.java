package aiedu.nacos.security.controller;

import aiedu.nacos.common.result.Result;
import aiedu.nacos.security.auth.UserDetailsImpl;
import aiedu.nacos.security.domain.UsernamePasswordDTO;
import aiedu.nacos.security.service.AuthServer;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengmf
 * @since 2022/1/11
 */
@RestController
@RequestMapping("auth")
@Api("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServer authServer;

    @PostMapping("/login")
    public Result<UserDetailsImpl> login(@RequestBody UsernamePasswordDTO usernamePasswordDTO) {
        return Result.success(authServer.login(usernamePasswordDTO));
    }


}
