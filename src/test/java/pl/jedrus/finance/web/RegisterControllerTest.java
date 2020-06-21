package pl.jedrus.finance.web;

import org.apache.commons.lang3.StringUtils;
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
import pl.jedrus.finance.service.user.UserService;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RegisterControllerTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserService userService;

    private MockMvc mvc;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }


//    GET method

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

//    POST method

//   Validation

    @Test
    public void should_ReturnErrorEmailValidation_WhenWrongEmailFormat() throws Exception {
        String email = "email";
        mvc.perform(post("/registration")
                .with(csrf())
                .param("email", email))
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(model().attribute("user", hasProperty("email", is(email))))
                .andExpect(view().name("register"));
    }

    @Test
    public void should_ReturnErrorEmailValidation_WhenEmailIsNull() throws Exception {
        String email = null;

        mvc.perform(post("/registration")
                .with(csrf())
                .param("email", email))
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(model().attribute("user", hasProperty("email", is(email))))
                .andExpect(view().name("register"));
    }

    @Test
    public void should_ReturnErrorEmailValidation_WhenEmailHasOnlyWhiteSigns() throws Exception {
        String email = " ";

        mvc.perform(post("/registration")
                .with(csrf())
                .param("email", email))
                .andExpect(model().attributeHasFieldErrors("user", "email"))
                .andExpect(model().attribute("user", hasProperty("email", is(email))))
                .andExpect(view().name("register"));
    }

    @Test
    public void should_ReturnErrorUserNameValidation_WhenUserNameToLong() throws Exception {
        String toLongName = StringUtils.repeat("a", 61);

        mvc.perform(post("/registration")
                .with(csrf())
                .param("username", toLongName))
                .andExpect(model().attributeHasFieldErrors("user", "username"))
                .andExpect(model().attribute("user", hasProperty("username", is(toLongName))))
                .andExpect(view().name("register"));
    }

    @Test
    public void should_ReturnErrorUserNameValidation_WhenUserNameIsNull() throws Exception {
        String username = null;

        mvc.perform(post("/registration")
                .with(csrf())
                .param("username", username))
                .andExpect(model().attributeHasFieldErrors("user", "username"))
                .andExpect(model().attribute("user", hasProperty("username", is(username))))
                .andExpect(view().name("register"));
    }

    @Test
    public void should_ReturnErrorUserNameValidation_WhenUsernameHasOnlyWhiteSigns() throws Exception {
        String username = "    ";

        mvc.perform(post("/registration")
                .with(csrf())
                .param("username", username))
                .andExpect(model().attributeHasFieldErrors("user", "username"))
                .andExpect(model().attribute("user", hasProperty("username", is(username))))
                .andExpect(view().name("register"));
    }

    @Test
    public void should_ReturnErrorPasswordValidation_WhenPasswordHasOnlyWhiteSigns() throws Exception {
        String pass = "    ";

        mvc.perform(post("/registration")
                .with(csrf())
                .param("password", pass))
                .andExpect(model().attributeHasFieldErrors("user", "password"))
                .andExpect(model().attribute("user", hasProperty("password", is(pass))))
                .andExpect(view().name("register"));
    }


    @Test
    public void should_ReturnErrorPasswordValidation_WhenPasswordIsNull() throws Exception {
        String pass = null;

        mvc.perform(post("/registration")
                .with(csrf())
                .param("password", pass))
                .andExpect(model().attributeHasFieldErrors("user", "password"))
                .andExpect(model().attribute("user", hasProperty("password", is(pass))))
                .andExpect(view().name("register"));

    }


    @Test
    public void should_PassValidationAndSaveDataToDB_WhenDataOk() throws Exception {
        String username = "abdmasdojefvmjdjsahDASX138ndaskjhwqdcsubbaASdajsjdasSd";

        mvc.perform(post("/registration")
                .with(csrf())
                .param("username", username)
                .param("password", "dan123")
                .param("email", "dan@dan.pl"))
                .andExpect(model().hasNoErrors());

        User userToRemove = userService.findByUserName(username);
        userService.deleteUser(userToRemove);
    }

}

