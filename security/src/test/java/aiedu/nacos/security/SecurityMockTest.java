package aiedu.nacos.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author pengmf
 * @since 2022/1/11
 */
@SpringBootTest(classes = {ApplicationSecurity.class})
public class SecurityMockTest {

    @Resource
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;


    @BeforeEach
    void beforeAll() {

        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();
    }


    @Test
    @WithMockUser(username = "user",password = "123456")
    void testSuccess() throws Exception {


        mockMvc.perform(get("/api/gen"))
                .andExpect(status().isOk());

    }
}
