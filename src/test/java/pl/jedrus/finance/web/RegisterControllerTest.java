package pl.jedrus.finance.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import pl.jedrus.finance.domain.User;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegisterControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


    @Test
    public void should_ReturnStatusOk_WhenGettingRequest() throws Exception {
        mvc.perform(get("/registration"))
                .andExpect(status().isOk());
    }

    @Test
    public void should_ReturnRegisterView_WhenGettingRequest() throws Exception {
        mvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    @Test
    public void should_PassEmptyUserToModelAttribute_WhenGettingRequest() throws Exception {
        User user = new User();
        mvc.perform(get("/registration"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("user", Matchers.instanceOf(User.class)))
                .andExpect(model().attribute("user", hasProperty("id", nullValue())))
                .andExpect(model().attribute("user", hasProperty("username", nullValue())))
                .andExpect(model().attribute("user", hasProperty("password", nullValue())))
                .andExpect(model().attribute("user", hasProperty("email", nullValue())))
                .andExpect(model().attribute("user", hasProperty("enabled", is(0))))
                .andExpect(model().attribute("user", hasProperty("roles", nullValue())))
                .andExpect(model().attribute("user", hasProperty("loans", nullValue())))
                .andExpect(model().attribute("user", hasProperty("assets", nullValue())));
    }
}